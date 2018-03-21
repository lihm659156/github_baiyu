package com.gupao.baiyu.design_pattern.template_model;


public class Test {

    public static void main(String[] args) {
        UserDao dao = new UserDao(null);
        dao.query("select * from user", null);
    }


}
