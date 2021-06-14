package com.project.linkedindatabase.domain.post;

import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class Comment extends BaseEntity {

    private Long profileId;

    private Long postId;

    private Long reCommentId;// foreign key to comment table ==> for re-comment a comment

    private String text;

    private Date date;
}
