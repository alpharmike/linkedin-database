package com.project.linkedindatabase.domain;


import com.project.linkedindatabase.annotations.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "background")
public class Background extends BaseEntity {

    private Long profileId;

    private Long backgroundType;// foreign key to background ==>enum

    private Date startDate;

    private Date endDate;

    private String title;

    private String description;

}
