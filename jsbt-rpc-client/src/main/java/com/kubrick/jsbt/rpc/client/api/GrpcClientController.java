package com.kubrick.jsbt.rpc.client.api;

/**
 * @author k
 * @version 1.0.0
 * @ClassName GrpcClientController
 * @description: TODO
 * @date 2021/2/22 下午8:17
 */
import com.kubrick.jsbt.rpc.client.service.IGrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ipipman on 2020/12/15.
 *
 * @version V1.0
 * @Package com.ipman.rpc.grpc.springboot.api
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/15 6:34 下午
 */
@RestController
public class GrpcClientController {

    @Autowired
    private IGrpcClientService grpcClientService;

    /**
     * Testing
     *
     * @param name
     * @return
     */
    @RequestMapping("/")
    public String printMessage(@RequestParam(defaultValue = "ipman") String name) {
        return grpcClientService.sendMessage(name);
    }
}
