package com.gupao.baiyu.design_pattern.adapter_model;

public class ServiceImpl implements Service {

    @Override
    public void order(IParam param) {
        param.cpParam.getPcParam();
        param.cpParam.setPcParam("");
    }
}
