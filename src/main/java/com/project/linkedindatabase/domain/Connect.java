package com.project.linkedindatabase.domain;

import com.project.linkedindatabase.annotations.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "connect")
public class Connect extends BaseEntity {

    private Long profileIdRequest; // foreign key to profile

    private Long profileIdReceive; // foreign key to profile

    private Long connectType; // foreign key to connectType ==>enum


}
