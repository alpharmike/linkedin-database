package com.project.linkedindatabase.jsonToPojo;


import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostJson {

    private Long id;

    private Long profileId;

    private Long sharedId;// foreign key to post table ==> for sharing a post

    private PostJson sharedIdJson;

    private String title;


    private Long showPostType;// foreign key to showPostType table ==>enum

    private String showPostTypeName;

    private String body;

    private String createdDate;

    private List<CommentJson> commentJsons = new ArrayList<>();

    private List<LikeJson> likeJsons = new ArrayList<>();

    private byte[] file;

    public static PostJson convertTOJson(Post post)
    {
        PostJson postJson = new PostJson();

        postJson.setId(post.getId());
        postJson.setTitle(post.getTitle());
        postJson.setProfileId(post.getProfileId());
        postJson.setSharedId(post.getSharedId());
        postJson.setShowPostType(post.getShowPostType());
        postJson.setBody(post.getBody());
        postJson.setCreatedDate(DateConverter.convertDate(post.getCreatedDate(),"yyyy-MM-dd hh:mm:ss"));
        postJson.setFile(post.getFile());

        return postJson;


    }
}
