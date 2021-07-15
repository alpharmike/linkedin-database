package com.project.linkedindatabase.repository.model.post;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.ShowPostType;
import com.project.linkedindatabase.domain.post.LikePost;
import com.project.linkedindatabase.domain.post.Post;
import com.project.linkedindatabase.jsonToPojo.CommentJson;
import com.project.linkedindatabase.jsonToPojo.LikeJson;
import com.project.linkedindatabase.jsonToPojo.PostJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.post.CommentService;
import com.project.linkedindatabase.service.model.post.LikePostService;
import com.project.linkedindatabase.service.types.ShowPostTypeService;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostRepository extends BaseRepository<Post,Long>  {

    private final LikePostService likePostService;
    private final ShowPostTypeService showPostTypeService;
    private final CommentService commentService;


    public PostRepository(LikePostService likePostService, ShowPostTypeService showPostTypeService, CommentService commentService) throws SQLException {
        super(Post.class);
        this.likePostService = likePostService;
        this.showPostTypeService = showPostTypeService;
        this.commentService = commentService;
    }



    @Override
    public void save(Post object) throws SQLException {

        PreparedStatement savePs = this.conn.prepareStatement("INSERT INTO "+this.tableName+" (profileId, sharedId, showPostType, " +
                "body, createdDate, file) VALUES(?, ?, ?, ?, ?, ?,?)");
        savePs.setLong(1, object.getProfileId());
        savePs.setLong(2, object.getSharedId());
        savePs.setLong(3, object.getShowPostType());
        savePs.setString(4, object.getBody());
        savePs.setString(5, DateConverter.convertDate(object.getCreatedDate(), "yyyy-MM-dd hh:mm:ss"));
        savePs.setBytes(6, object.getFile());
        savePs.setString(7, object.getTitle());
        savePs.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement createTablePs = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.tableName + "(" +
                "id BIGINT NOT NULL AUTO_INCREMENT,"+
                "profileId BIGINT NOT NULL," +
                "FOREIGN KEY (profileId) REFERENCES " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "sharedId BIGINT ," +
                "FOREIGN KEY (sharedId) REFERENCES " +  BaseEntity.getTableName(Post.class) + "(id),"+
                "showPostType BIGINT NOT NULL," +
                "FOREIGN KEY (showPostType) REFERENCES " +  BaseEntity.getTableName(ShowPostType.class) + "(id),"+
                "body TEXT NOT NULL,"+
                "createdDate NVARCHAR(255) NOT NULL,"+
                "title NVARCHAR(255) NOT NULL,"+
                "file MEDIUMBLOB,"+
                "PRIMARY KEY (id)"+
            ")"
        );
        createTablePs.execute();
    }


    @SneakyThrows
    @Override
    public Post convertSql(ResultSet resultSet) throws SQLException{
        Post post = new Post();

        post.setId(resultSet.getLong("id"));
        post.setProfileId(resultSet.getLong("profileId"));
        post.setSharedId(resultSet.getLong("sharedId"));
        post.setShowPostType(resultSet.getLong("showPostType"));
        post.setBody(resultSet.getString("body"));
        post.setCreatedDate(DateConverter.parse(resultSet.getString("createdDate"), "yyyy-MM-dd hh:mm:ss"));
        post.setFile(resultSet.getBytes("file"));
        post.setTitle(resultSet.getString("title"));


        return post;
    }

    public void updateWithProfile(PostJson object) throws SQLException {
        PreparedStatement savePs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET  sharedId = ?, showPostType = ?, " +
                "body = ?, createdDate = ?, file = ? , title = ? where id = ? and profileId = ?");

        savePs.setLong(1, object.getSharedId());
        savePs.setLong(2, object.getShowPostType());
        savePs.setString(3, object.getBody());
        savePs.setString(4, object.getCreatedDate());
        savePs.setBytes(5, object.getFile());
        savePs.setString(6, object.getTitle());

        savePs.setLong(7, object.getId());
        savePs.setLong(8, object.getProfileId());


        savePs.execute();
    }

    public List<PostJson> findByProfileId(Long profileId) throws SQLException {

        List<Post> posts = findAllByProfileId(profileId);

        return getAllDetailOfPost(posts);
    }

    public void DeleteByProfileId(Long profileId,Long id) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement("DELETE  from "+this.getTableName()+" where id = ? and profileId = ?");

        ps.setLong(1, id);
        ps.setLong(2, profileId);
        ps.execute();
    }

    public List<Post> findAllByProfileId(Long profileId) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement("select * from "+this.getTableName() + " where profileId = ?");
        ps.setLong(1,profileId);

        ResultSet resultSet = ps.executeQuery();
        List<Post> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }



    private List<PostJson> getAllDetailOfPost(List<Post> posts ) throws SQLException {
        List<PostJson> postJsons = new ArrayList<>();
        for (Post p : posts)
        {
            postJsons.add(getDetailOfPost(p));
        }
        return postJsons;
    }

    private PostJson getDetailOfPost(Post post) throws SQLException {
        PostJson postJson = PostJson.convertTOJson(post);
        //todo
        postJson.setShowPostTypeName(showPostTypeService.findById(post.getShowPostType()).getName());

        if (post.getSharedId() == null || post.getSharedId() == 0  )
        {
            Post sharePost = findById(post.getSharedId());
            postJson.setSharedIdJson(PostJson.convertTOJson(sharePost));
        }

        List<LikePost> postLike = likePostService.getLikeByPostId(post.getId());

        List<LikeJson> likeJsonPost = new ArrayList<>();

        for (LikePost i : postLike)
        {
            likeJsonPost.add(LikeJson.convertToJsonPost(i));
        }
        postJson.setLikeJsons(likeJsonPost);

        List<CommentJson> commentJsonList = commentService.findByPostIdJson(postJson.getId());
        postJson.setCommentJsons(commentJsonList);

        return postJson;
    }
}
