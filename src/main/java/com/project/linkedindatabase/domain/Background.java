package com.project.linkedindatabase.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.linkedindatabase.annotations.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "background")
public class Background extends BaseEntity {

    private Long profileId;

    private Long backgroundType;// foreign key to background ==>enum

    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar endDate;

    private String title;

    private String description;

}
