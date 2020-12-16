package com.kubrick.sbt.web.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ToolsTest
 * @description: TODO
 * @date 2020/12/15 下午6:22
 */
public class ToolsTest {


    /**
     *
     */
    @Test
    public void bulong() {
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01);

    }
}
