package com.project.linkedindatabase.utils;

import com.project.linkedindatabase.annotations.Table;

public class AnnotationValueGetter {
    static public String getTableName(Class<?> reflectClass) {
        // Utility function to retrieve table name for a given class
        if (reflectClass.isAnnotationPresent(Table.class)) {
            Table table = reflectClass.getAnnotation(Table.class);
            return table.tableName();
        }
        return "";
    }
}