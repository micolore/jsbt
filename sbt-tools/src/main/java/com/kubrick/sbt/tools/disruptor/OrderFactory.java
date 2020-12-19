package com.kubrick.sbt.tools.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author k
 * @version 1.0.0
 * @ClassName OrderFactory
 * @description: TODO
 * @date 2020/12/19 下午11:10
 */
public class OrderFactory  implements EventFactory {

    @Override
    public Object newInstance() {

        System.out.println("OrderFactory.newInstance");
        return new Order();
    }

}
