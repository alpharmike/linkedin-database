package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.LikePost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeJson {

    private  Long profileId;

    private  Long id;


    private Long post_comment_id;

    private boolean isForPost ;

    private boolean isForComment;


    public static LikeJson convertToJsonPost(LikePost likePost)
    {
        LikeJson likeJson = new LikeJson();
        likeJson.setForComment(false);
        likeJson.setForPost(true);


        likeJson.setId(likePost.getId());
        likeJson.setPost_comment_id(likePost.getPostId());
        likeJson.setProfileId(likePost.getProfileId());
        return likeJson;
    }


    public static LikeJson convertToJsonComment(LikeComment likeComment)
    {
        LikeJson likeJson = new LikeJson();
        likeJson.setForComment(true);
        likeJson.setForPost(false);


        likeJson.setId(likeComment.getId());
        likeJson.setPost_comment_id(likeComment.getCommentId());
        likeJson.setProfileId(likeComment.getProfileId());
        return likeJson;
    }

}
