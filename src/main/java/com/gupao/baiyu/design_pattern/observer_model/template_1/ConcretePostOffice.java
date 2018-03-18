package com.gupao.baiyu.design_pattern.observer_model.template_1;

/**
 * 具体邮局
 */
public class ConcretePostOffice extends PostOffice {

    // 报纸是否到了状态
    private String status;

    // 报纸内容
    private String content;

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
        //报纸到了，通知所有订阅者
        this.notifyPersion();
    }


}
