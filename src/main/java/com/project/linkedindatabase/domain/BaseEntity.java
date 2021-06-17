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

    public String getTableName() {
        if (this.getClass().isAnnotationPresent(Table.class)) {
            Table table = this.getClass().getAnnotation(Table.class);
            return table.tableName();
        }
        return "";
    }
}
