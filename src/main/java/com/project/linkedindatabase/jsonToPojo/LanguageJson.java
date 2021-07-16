package com.project.linkedindatabase.jsonToPojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageJson {
    private Long id;
    private Long profileId;

    private String language;

    private Long languageLevel;
    private String languageLevelName;
}
