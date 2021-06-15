package com.project.linkedindatabase.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Connect extends BaseEntity {

    private Long profileIdRequest; // foreign key to profile

    private Long profileIdReceive; // foreign key to profile

    private Long connectType; // foreign key to connectType ==>enum


}
