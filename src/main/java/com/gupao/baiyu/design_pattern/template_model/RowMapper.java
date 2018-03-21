package com.gupao.baiyu.design_pattern.template_model;

import java.sql.ResultSet;

public interface RowMapper<T> {

    public T mapRow(ResultSet rs, int rowNum) throws Exception;

}
