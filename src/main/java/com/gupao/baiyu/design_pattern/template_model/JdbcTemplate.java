package com.gupao.baiyu.design_pattern.template_model;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private DataSource dataSource;

    JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    private PreparedStatement getPreparedStatement(Connection conn, String sql) throws Exception {
        return conn.prepareStatement(sql);
    }

    private ResultSet executeQuery(PreparedStatement ps, Object[] values) throws Exception {
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i, values[i]);
        }
        return ps.executeQuery();
    }

    private void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }

    private void closePreparedStatement(PreparedStatement ps) throws Exception {
        ps.close();
    }

    private void closeConnection(Connection conn) throws Exception {
        conn.close();
    }

    public List<Object> query(String sql, Object[] values, RowMapper<Object> rowMapper) {
        List<Object> result = new ArrayList<Object>();
        try {
            // 1.获得数据库连接
            Connection conn = getConnection();
            // 2.创建语句集
            PreparedStatement ps = getPreparedStatement(conn, sql);
            // 3.执行查询，获得结果集
            ResultSet rs = executeQuery(ps, values);
            // 4.解析结果集
            result = parseResultSet(rowMapper, rs);
            // 5.关闭结果集
            closeResultSet(rs);
            // 6.关闭语句集
            closePreparedStatement(ps);
            // 7.关闭连接
            closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Object> parseResultSet(RowMapper<Object> rowMapper, ResultSet rs) throws Exception {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum));
            rowNum++;
        }
        return result;
    }

    ;

//    protected abstract Object processResult(ResultSet rs) throws Exception;

}
