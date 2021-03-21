package com.kubrick.sbt.web.common.cache;

import com.alibaba.fastjson.JSON;
import com.kubrick.sbt.web.common.annotation.redis.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author k
 * @version 1.0.0
 * @ClassName RedisCacheAspect
 * @description: TODO
 * @date 2020/12/15 上午1:12
 */
@RequiredArgsConstructor
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    private final StringRedisTemplate stringRedisTemplate;

    @Around("@annotation(com.kubrick.sbt.web.common.annotation.redis.RedisCache)")
    public Object cacheInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取到目标方法
        Method method = pjp.getTarget().getClass().getMethod(signature.getName(),
                signature.getParameterTypes());
        // 获取方法注解
        RedisCache redisCache = method.getAnnotation(RedisCache.class);
        String keyEl = redisCache.key();
        // 创建解析器 解析EL表达式
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(keyEl);
        // 设置解析上下文（这些占位符的值 来自）
        EvaluationContext context = new StandardEvaluationContext();
        // 参数值 获取到参数实际的值
        Object[] args = pjp.getArgs();
        // 我们还需要获取实际的参数名，而不是agrs0,agrs1这种形式，通过下面的方式可以获取
        // public DefaultParameterNameDiscoverer() {
        // if (standardReflectionAvailable) {
        // this.addDiscoverer(new StandardReflectionParameterNameDiscoverer());
        // }
        //
        // this.addDiscoverer(new LocalVariableTableParameterNameDiscoverer());
        // }
        // 他会调用LocalVariableTableParameterNameDiscoverer去实现
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        // 解析出key的真实值
        String key = expression.getValue(context).toString();
        log.info("redis cache key:{}", key);
        String object = stringRedisTemplate.opsForValue().get(key);
        if (object == null) {
            // 获取方法执行结果
            Object data = pjp.proceed();
            log.info("from db get data key:{}", key);
            // 缓存时间
            long expireTime = redisCache.expire();
            if (expireTime == -1) {
                stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(data));
            } else {
                stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(data),
                        expireTime, TimeUnit.SECONDS);
            }
            return data;
        } else {
            log.info("from redis get data key:{}", key);
            // 这里object套一层JSON.parse()是因为有时候存入redis的json字符串get出来后会多一个“\”转义符号导致直接parse失败
            return JSON.parseObject(JSON.parse(object).toString(),
                    signature.getReturnType());
        }

    }

}
