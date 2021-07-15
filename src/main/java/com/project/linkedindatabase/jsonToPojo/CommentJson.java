package com.project.linkedindatabase.jsonToPojo;


import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentJson {

    private Long id;

    private Long profileId;

    private Long postId;

    private List<CommentJson> commentJsonsChild = new ArrayList<>();

    private Long reCommentId;

    private List<LikeJson> likeJsons = new ArrayList<>();

    private String body;

    private String createdDate;

    public static CommentJson convertToJson(Comment comment)
    {
        CommentJson commentJson = new CommentJson();
        commentJson.setId(comment.getId());
        commentJson.setProfileId(comment.getProfileId());
        commentJson.setPostId(comment.getPostId());
        commentJson.setReCommentId(comment.getReCommentId());
        commentJson.setBody(comment.getBody());
        commentJson.setCreatedDate(DateConverter.convertDate(comment.getCreatedDate(), "yyyy-MM-dd HH:mm:ss"));

        return commentJson;
    }

    public  Comment convertToComment() throws ParseException {
        Comment comment = new Comment();
        comment.setId(getId());
        comment.setProfileId(getProfileId());
        comment.setPostId(getPostId());
        comment.setReCommentId(getReCommentId());
        comment.setBody(getBody());
        comment.setCreatedDate(DateConverter.parse(getCreatedDate(), "yyyy-MM-dd HH:mm:ss"));

        return comment;
    }

}
