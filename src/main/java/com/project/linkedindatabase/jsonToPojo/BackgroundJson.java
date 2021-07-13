
package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Generated;
import java.text.ParseException;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundJson {

    private Long BackgroundType;
    private String Description;
    private Long ProfileId;
    private String StartDate;
    private String endDate;
    private String Title;
    private Long id;


    public Background convertToBackGround() throws ParseException {
        Background background = new Background();
        background.setBackgroundType(getBackgroundType());
        background.setProfileId(getProfileId());
        background.setDescription(getDescription());
        background.setTitle(getTitle());
        background.setId(getId());
        background.setStartDate(DateConverter.parse(getStartDate(),"yyyy-MM-dd"));
        if (endDate != null && !endDate.equals(""))
            background.setStartDate(DateConverter.parse(getStartDate(),"yyyy-MM-dd"));
        return background;
    }


}
