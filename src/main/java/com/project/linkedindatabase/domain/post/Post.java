package com.project.linkedindatabase.domain.post;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Post {

    private Long profileId;

    private Long shareId;// foreign key to post table ==> for sharing a post

    private Long showPostType;// foreign key to showPostType table ==>enum

    private String text;

    private Date date;

    private Byte[] file;


}
