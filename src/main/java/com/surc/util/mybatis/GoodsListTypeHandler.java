package com.surc.util.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.surc.entity.Goods;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodsListTypeHandler extends BaseTypeHandler<List<Goods>> {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Goods> parameter, JdbcType jdbcType) throws SQLException {
        final String parameterString = listToJson(parameter);
        StringReader reader = new StringReader(parameterString);
        ps.setCharacterStream(i, reader, parameterString.length());
    }


    @Override
    public List<Goods> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        List<Goods> list = null;
        try {
            list = objectMapper.readValue(resultSet.getNString(s),new TypeReference<List<Goods>>() {});
        } catch (JsonProcessingException e) {
        }
        return list;
    }

    @Override
    public List<Goods> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        List<Goods> list = null;
        try {
            list = objectMapper.readValue(resultSet.getNString(i),new TypeReference<List<Goods>>() {});
        } catch (JsonProcessingException e) {
        }
        return list;
    }

    @Override
    public List<Goods> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        List<Goods> list = null;
        try {
            list = objectMapper.readValue(callableStatement.getNString(i),new TypeReference<List<Goods>>() {});
        } catch (JsonProcessingException e) {
        }
        return list;
    }

    private String listToJson(List<Goods> from) {
        try {
            return objectMapper.writeValueAsString(from);
        } catch (IOException e) {
            throw new Error();
        }
    }
}
