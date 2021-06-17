package com.project.linkedindatabase.utils;

import com.project.linkedindatabase.annotations.Table;

public class AnnotationValueGetter {
    static public String getTableName(Class<?> reflectClass) {
        if (reflectClass.isAnnotationPresent(Table.class)) {
            Table table = reflectClass.getAnnotation(Table.class);
            return table.tableName();
        }
        return "";
    }
}