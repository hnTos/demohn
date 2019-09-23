package com.hn.example.demohn.web.circulDepend;

import org.springframework.aop.framework.AopProxy;

/**
 * @Author: liyb
 * @Date: 2019/6/11
 * @description
 */
public interface AopSonProxy extends AopProxy{
    void son();
}
