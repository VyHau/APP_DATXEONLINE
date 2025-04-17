package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnection;

public abstract class AbstractDAO<T> {
    
    protected T executeQuery(String sql, Object[] params, RowMapper<T> rowMapper) {
        T result = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setParameters(stmt, params);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                 result = rowMapper.mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    protected List<T> executeQueryList(String sql, Object[] params, RowMapper<T> rowMapper) {
        List<T> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setParameters(stmt, params);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected int executeUpdate(String sql, Object[] params) {
        int rowsAffected = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setParameters(stmt, params);
            
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    private void setParameters(PreparedStatement stmt, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }
    protected String callFunctionString(String functionName) {
        String sql = "SELECT dbo." + functionName + " AS result";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Interface RowMapper giúp ánh xạ ResultSet thành đối tượng T
    public interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }
}
