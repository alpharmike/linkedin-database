package com.project.linkedindatabase.controller;

import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.post.Comment;
import com.project.linkedindatabase.domain.post.LikeComment;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.jsonToPojo.CommentJson;
import com.project.linkedindatabase.jsonToPojo.PostJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.post.CommentService;
import com.project.linkedindatabase.service.model.post.LikeCommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import com.project.linkedindatabase.service.model.post.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class PostController {
    private final ProfileService profileService;
    private final PostService postService;
    private final CommentService commentService;
    private final LikeCommentService likeCommentService;
    private final LikePostService likePostService;

    public PostController(ProfileService profileService, PostService postService, CommentService commentService,
                          LikeCommentService likeCommentService, LikePostService likePostService) {
        this.profileService = profileService;
        this.postService = postService;
        this.commentService = commentService;
        this.likeCommentService = likeCommentService;
        this.likePostService = likePostService;
    }

    @GetMapping("/post")
    public List<PostJson> getPost(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return postService.findByProfileId(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
    }

    @GetMapping("/post/{id}")
    public List<PostJson> getProfilePost(@RequestHeader Map<String, Object> jsonHeader, @PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return postService.findByProfileId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
    }

    @GetMapping("/post/network")
    public List<PostJson> getPostNetwork(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return postService.getPostOfConnection(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/post/network-comment")
    public List<PostJson> getPostNetworkComment(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return postService.getPostOfConnectionComment(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/post/network-like")
    public List<PostJson> getPostNetworkLike(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return postService.getPostOfConnectionLike(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }


    @PostMapping("/post")
    public void createPost(@RequestHeader Map<String, Object> jsonHeader,@RequestBody PostJson postJson) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            postJson.setProfileId(profile.getId());
            Post  post = postJson.convertPost();

            postService.save(post);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
    }

    @PostMapping("/post/{id}")
    public void updatePost(@RequestHeader Map<String, Object> jsonHeader,@RequestBody PostJson post,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            post.setProfileId(profile.getId());
            post.setId(id);
            postService.updateWithProfile(post);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
           Long profileId = profile.getId();
            postService.DeleteByProfileId(profileId,id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @PostMapping("/post/comment")
    public void createComment(@RequestHeader Map<String, Object> jsonHeader,@RequestBody CommentJson commentJson) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            commentJson.setProfileId(profile.getId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
        try {
            commentService.save(commentJson.convertToComment());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @PostMapping("/post/like")
    public void createLikePost(@RequestHeader Map<String, Object> jsonHeader,@RequestBody LikePost likePost) throws SQLException {

        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            likePost.setProfileId(profile.getId());


        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        boolean duplicate = false;
        try {
            if(likePostService.isThereALike(likePost))
            {
                duplicate =true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }
       if (duplicate)
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST, "you cant like it twice ", new Exception("duplicate"));

       try {
           likePostService.save(likePost);
       }catch (Exception e)
       {
           e.printStackTrace();
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
       }



    }

    @DeleteMapping("/post/like/{id}")
    public void deleteLikePost(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Long profileId = profile.getId();
            likePostService.deleteByIdAndProfileId(id,profileId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }


    @PostMapping("/post/comment/like")
    public void createLikeComment(@RequestHeader Map<String, Object> jsonHeader,@RequestBody LikeComment likeComment) {
        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            likeComment.setProfileId(profile.getId());


        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        boolean duplicate = false;
        try {
            if(likeCommentService.isThereALike(likeComment))
            {
                duplicate =true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }
        if (duplicate)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "you cant like it twice ", new Exception("duplicate"));

        try {
            likeCommentService.save(likeComment);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    @DeleteMapping("/post/comment/like/{id}")
    public void deleteLikeComment(@RequestHeader Map<String, Object> jsonHeader,@PathVariable(name = "id") Long id) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            Long profileId = profile.getId();
            likeCommentService.deleteByIdAndProfileId(id,profileId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }





}
