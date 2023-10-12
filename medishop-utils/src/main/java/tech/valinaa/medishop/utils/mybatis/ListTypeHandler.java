package tech.valinaa.medishop.utils.mybatis;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @param <T> List中的数据类型
 * @author Valinaa
 * @Date 2023/10/3 13:31
 * @Description 通用List类型处理器, list转为 T,T,T...
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
        return this.getListByContent(resultSet.getString(s));
    }
    
    @Override
    public List<T> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return this.getListByContent(resultSet.getString(i));
    }
    
    @Override
    public List<T> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return this.getListByContent(callableStatement.getString(i));
    }
    
    /**
     * 根据内容获取List, 子类实现
     *
     * @param content 数据库内容
     * @return 对应java类型(List)
     */
    
    protected abstract List<T> getListByContent(String content);
    
    /**
     * 具体类型，由子类提供
     *
     * @return 具体类型
     */
    protected abstract TypeReference<List<T>> specificType();
}
