package com.gupao.baiyu.design_pattern.adapter_model;

import java.util.HashMap;

public interface IParam {


    public static PcParam cpParam = new PcParam();

     public static class PcParam{

        private String pcParam;

        public String getPcParam() {
            return pcParam;
        }

        public void setPcParam(String pcParam) {
            this.pcParam = pcParam;
        }
    }





}
