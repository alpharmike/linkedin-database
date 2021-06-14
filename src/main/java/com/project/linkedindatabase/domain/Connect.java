package com.project.linkedindatabase.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Connect extends BaseEntity {

    private Long follower; // foreign key to profile

    private Long following; // foreign key to profile

    private Long connectStatus; // foreign key to connectStatus ==>enum


}
