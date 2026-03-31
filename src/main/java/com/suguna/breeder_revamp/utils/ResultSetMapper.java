package com.suguna.breeder_revamp.utils;


import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
public class ResultSetMapper {
    public static <T> T mapResultSetToObject(ResultSet rs, Class<T> outputClass) throws SQLException {
        T obj = null;
        try {
            obj = outputClass.getDeclaredConstructor().newInstance();
            Field[] fields = outputClass.getDeclaredFields();
            String columnName = null;
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    columnName = column.name();
                    Class<?> columnType = column.type();
                    Object value = getValueByType(rs, columnName, columnType);

                    if (null == value) continue;

                    field.setAccessible(true);
                    field.set(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    private static Object getValueByType(ResultSet rs, String columnName, Class<?> columnType) throws SQLException {
        try {
            if (columnType == String.class) {
                String value = rs.getString(columnName);
                return null == value || value.isEmpty() ? null : value;
            } else if (columnType == UUID.class) {
                UUID value = (UUID) rs.getObject(columnName);
                return null == value ? null : String.valueOf(value);
            } else if (columnType == int.class || columnType == Integer.class) {
                return rs.getInt(columnName);
            } else if (columnType == long.class || columnType == Long.class) {
                return rs.getLong(columnName);
            } else if (columnType == double.class || columnType == Double.class) {
                return rs.getDouble(columnName);
            } else if (columnType == float.class || columnType == Float.class) {
                return rs.getFloat(columnName);
            } else if (columnType == boolean.class || columnType == Boolean.class) {
                return rs.getBoolean(columnName);
            } else if (columnType == Date.class) {
                return rs.getDate(columnName);
            } else if (columnType == Timestamp.class) {
                return rs.getTimestamp(columnName) != null ? rs.getTimestamp(columnName).toLocalDateTime() : null;
            } else {
                return rs.getObject(columnName);
            }
        } catch (Exception ex) {
            //skip column not found from stored procedure
            System.out.println("error message = " + ex.getMessage());
            System.out.println("columnName not found = " + columnName);
            return null;
        }
    }

}
