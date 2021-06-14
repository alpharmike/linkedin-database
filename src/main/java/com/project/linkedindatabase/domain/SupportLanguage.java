package com.project.linkedindatabase.domain;

import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupportLanguage extends BaseEntity {

    private Long profileId;

    private String firstName;

    private String lastName;

    private Long language; // foreign key to supportLanguage ==>enum

    private String headline;
}
