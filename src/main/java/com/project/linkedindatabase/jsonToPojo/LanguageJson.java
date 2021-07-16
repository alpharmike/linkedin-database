package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.accomplishment.Accomplishment;
import com.project.linkedindatabase.domain.accomplishment.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;

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


    public Language convertToLanguage() throws ParseException {
        Language language = new Language();

        language.setLanguage(getLanguage());
        language.setLanguageLevel(getLanguageLevel());

        language.setProfileId(getProfileId());
        language.setId(getId());
        return language;
    }


    public static LanguageJson convertToJson(Language language)  {
        LanguageJson languageJson = new LanguageJson();

        languageJson.setLanguage(language.getLanguage());
        languageJson.setLanguageLevel(language.getLanguageLevel());

        languageJson.setProfileId(language.getProfileId());
        languageJson.setId(language.getId());
        return languageJson;

    }
}
