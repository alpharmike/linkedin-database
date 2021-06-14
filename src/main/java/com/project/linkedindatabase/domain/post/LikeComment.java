package com.project.linkedindatabase.domain.post;

import com.project.linkedindatabase.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeComment extends BaseEntity {

    private Long commentId;// foreign key to comment table

    private Long profileId;
}
