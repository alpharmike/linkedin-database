package com.project.linkedindatabase.domain.post;


import com.project.linkedindatabase.annotations.Table;
import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(tableName = "like_post")
public class LikePost extends BaseEntity {

    private Long postId;// foreign key to post table

    private Long profileId;
}
