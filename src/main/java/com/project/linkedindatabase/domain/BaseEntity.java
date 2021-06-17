package com.project.linkedindatabase.domain;


import com.project.linkedindatabase.annotations.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {

    private Long id;

    static public String getTableName(Class<?> reflectClass) {
        if (reflectClass.isAnnotationPresent(Table.class)) {
            Table table = (Table) reflectClass.getAnnotation(Table.class);
            return table.tableName();
        }
        return "";
    }
}
