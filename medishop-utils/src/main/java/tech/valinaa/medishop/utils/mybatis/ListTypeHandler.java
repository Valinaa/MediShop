package tech.valinaa.medishop.utils.mybatis;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import tech.valinaa.medishop.utils.JacksonUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> List中的数据类型
 * @author Valinaa
 * @Date 2023/10/3 13:31
 * @Description 通用List类型处理器
 */
@MappedJdbcTypes(JdbcType.VARCHAR)  //数据库类型
@MappedTypes({List.class})          //java数据类型
public abstract class ListTypeHandler<T> extends BaseTypeHandler<List<T>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i,
                                    List<T> tList, JdbcType jdbcType) throws SQLException {
        if (tList.isEmpty()) {
            preparedStatement.setString(i, null);
        }
        var sb = new StringBuilder();
        tList.forEach(item -> sb.append(item).append(","));
        preparedStatement.setString(i, sb.deleteCharAt(sb.length() - 1).toString());
    }
    
    @Override
    public List<T> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return this.getListByJsonArrayString(resultSet.getString(s));
    }
    
    @Override
    public List<T> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return this.getListByJsonArrayString(resultSet.getString(i));
    }
    
    @Override
    public List<T> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return this.getListByJsonArrayString(callableStatement.getString(i));
    }
    
    private List<T> getListByJsonArrayString(String content) {
        return content.isBlank() ? new ArrayList<>() : JacksonUtil.parseObject(content, this.specificType());
    }
    
    /**
     * 具体类型，由子类提供
     *
     * @return 具体类型
     */
    protected abstract TypeReference<List<T>> specificType();
}
