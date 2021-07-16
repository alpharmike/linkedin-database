package com.project.linkedindatabase.domain.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "comment")
public class Comment extends BaseEntity {

    private Long profileId;

    private Long postId;

    private Long reCommentId;// foreign key to comment table ==> for re-comment a comment

    private String body;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar createdDate;
}
