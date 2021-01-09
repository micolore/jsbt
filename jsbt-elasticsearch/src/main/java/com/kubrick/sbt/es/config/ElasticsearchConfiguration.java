package com.kubrick.sbt.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

/**
 * @author k
 * @version 1.0.0
 * @ClassName config
 * @description: TODO
 * @date 2021/1/2 下午5:40
 */
public class ElasticsearchConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(RestHighLevelClient.class)
    public ElasticsearchRestTemplate elasticsearchTemplate(RestHighLevelClient client,
                                                           ElasticsearchConverter converter) {
        try {
            return new ElasticsearchRestTemplate(client, converter);
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

}
