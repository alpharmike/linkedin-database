package com.project.linkedindatabase.domain.post;


import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "post")
public class Post extends BaseEntity {

    private Long profileId;

    private Long sharedId;// foreign key to post table ==> for sharing a post

    private Long showPostType;// foreign key to showPostType table ==>enum

    private String body;

    private String title;

    private Calendar createdDate;

    private byte[] file;


}
