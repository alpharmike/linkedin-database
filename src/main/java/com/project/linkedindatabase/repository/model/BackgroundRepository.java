package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.BackgroundType;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.utils.DateConverter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundRepository extends BaseRepository<Background,Long>  {

    public BackgroundRepository() throws SQLException {
        super(Background.class);
    }



    @Override
    public void save(Background object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (profileId, backgroundType, startDate, endDate, title, description) VALUES (?, ?, ?, ?, ?, ?)");
        ps.setLong(1, object.getProfileId());
        ps.setLong(2, object.getBackgroundType());
        ps.setString(3, DateConverter.convertDate(object.getStartDate(), "yyyy-MM-dd HH:mm:ss"));
        if (object.getEndDate() != null)
            ps.setString(4, DateConverter.convertDate(object.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
        else
            ps.setNull(4, Types.NVARCHAR);
        ps.setString(5, object.getTitle());
        ps.setString(6, object.getDescription());
        ps.execute();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "profileId bigint not null," +
                "backgroundType bigint not null," +
                "startDate nvarchar(255) not null," +
                "endDate nvarchar(255)," +
                "title nvarchar(100) not null," +
                "description TEXT not null," +
                "foreign key (profileId) references " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "foreign key (backgroundType) references " +  BaseEntity.getTableName(BackgroundType.class) + "(id)"+
            ")"
        );

        ps.execute();
    }



    @SneakyThrows
    @Override
    public Background convertSql(ResultSet resultSet) throws SQLException {
        Background background = new Background();

        background.setId(resultSet.getLong("id"));
        background.setProfileId(resultSet.getLong("profileId"));
        background.setBackgroundType(resultSet.getLong("backgroundType"));
        background.setStartDate(DateConverter.parse(resultSet.getString("startDate"), "yyyy-MM-dd HH:mm:ss"));
        if (resultSet.getString("endDate") != null && !resultSet.getString("endDate").equals(""))
            background.setEndDate(DateConverter.parse(resultSet.getString("endDate"), "yyyy-MM-dd HH:mm:ss"));
        background.setTitle(resultSet.getString("title"));
        background.setDescription(resultSet.getString("description"));

        return background;
    }

    public List<Background> findByProfileId(Long id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from " + this.getTableName() + " where profileId = ?");
        ps.setLong(1,id);


        ResultSet resultSet = ps.executeQuery();
        List<Background> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;
    }

    public void update(Background object) throws SQLException {



        PreparedStatement ps = this.conn.prepareStatement("UPDATE " + this.tableName + " set backgroundType = ? ," +
                " startDate = ? , endDate = ? , title = ? , description = ? where id = ?");
        ps.setLong(1, object.getBackgroundType());
        ps.setString(2, DateConverter.convertDate(object.getStartDate(), "yyyy-MM-dd HH:mm:ss"));
        if (object.getEndDate() != null)
            ps.setString(3, DateConverter.convertDate(object.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
        else
            ps.setNull(3, Types.NVARCHAR);
        ps.setString(4, object.getTitle());
        ps.setString(5, object.getDescription());
        ps.setLong(6, object.getId());
        System.out.println(ps.toString());

        ps.execute();
    }

    public void updateWithProfileId(Background object) throws SQLException {

        PreparedStatement ps = this.conn.prepareStatement("UPDATE " + this.tableName + "  set backgroundType = ? ," +
                " startDate = ? , endDate = ? , title = ? , description = ? where id = ? and profileId = ?");
        ps.setLong(1, object.getBackgroundType());
        ps.setString(2, DateConverter.convertDate(object.getStartDate(), "yyyy-MM-dd HH:mm:ss"));
        if (object.getEndDate() != null)
            ps.setString(3, DateConverter.convertDate(object.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
        else
            ps.setNull(3, Types.NVARCHAR);
        ps.setString(4, object.getTitle());
        ps.setString(5, object.getDescription());
        ps.setLong(6, object.getId());
        ps.setLong(7, object.getProfileId());
        System.out.println(ps.toString());
        ps.execute();
    }


    public void deleteByIdAndProfileId(Background background) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE  from "+this.getTableName()+" where id = ? and  profileId = ?");

        ps.setLong(1, background.getId());
        ps.setLong(2, background.getProfileId());
        ps.execute();
    }
}
