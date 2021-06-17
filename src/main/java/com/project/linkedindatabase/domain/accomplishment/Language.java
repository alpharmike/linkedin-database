package com.project.linkedindatabase.domain.accomplishment;

import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "language")
public class Language extends BaseEntity {

    private Long profileId;

    private String language;

    private Long languageLevel; // foreign key to languageLevel ==>enum


}
