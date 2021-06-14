package com.project.linkedindatabase.domain.profileDetail;


import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Website extends BaseEntity {



    private Long profileId;

    private String url;

    private Long websiteType;// foreign key --> enum
}
