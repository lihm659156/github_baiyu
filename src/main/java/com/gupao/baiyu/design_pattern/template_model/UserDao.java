package com.gupao.baiyu.design_pattern.template_model;

import com.gupao.baiyu.design_pattern.template_model.JdbcTemplate;
import com.gupao.baiyu.design_pattern.template_model.entity.User;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

//    UserDao(DataSource dataSource) {
//        super(dataSource);
//    }

    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

    /**
     * 使用接口，来加入回调函数，跟代理模式很像
     * @param sql
     * @param objects
     * @return
     */
    public List<Object> query(String sql, Object[] objects) {
        List<Object> list = this.jdbcTemplate.query(sql, objects, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws Exception {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setAge(rs.getInt("age"));
                user.setAddr(rs.getString("addr"));
                return user;
            }
        });
        return list;
    }

//    @Override
//    protected Object processResult(ResultSet rs) throws Exception {
//        User user = new User();
//        user.setUsername(rs.getString("username"));
//        user.setAge(rs.getInt("age"));
//        user.setAddr(rs.getString("addr"));
//        return user;
//    }

}
