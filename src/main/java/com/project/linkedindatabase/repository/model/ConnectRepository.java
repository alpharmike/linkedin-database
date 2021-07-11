package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.Background;
import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.ProfileService;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ConnectRepository extends BaseRepository<Connect,Long> {

    public ConnectRepository() throws SQLException {
        super(Connect.class);
    }




    @Override
    public void save(Connect object) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("INSERT INTO " + this.tableName + " (profileIdRequest, profileIdReceive, connectType) VALUES (?, ?, ?)");
        ps.setLong(1, object.getProfileIdRequest());
        ps.setLong(2, object.getProfileIdReceive());
        ps.setLong(3, object.getConnectType());
        ps.executeQuery();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "profileIdRequest bigint not null," +
                "profileIdReceive bigint not null," +
                "connectType date not null," +
                "foreign key (profileIdRequest) references profile(id)," +
                "foreign key (profileIdReceive) references profile(id)," +
                "foreign key (connectType) references connect_type(id)" +
            ")"
        );

        ps.execute();
    }



    @Override
    public Connect convertSql(ResultSet resultSet) {
        Connect connect = new Connect();
        try {
            resultSet.first();
            connect.setId(resultSet.getLong("id"));
            connect.setProfileIdRequest(resultSet.getLong("profileIdRequest"));
            connect.setProfileIdReceive(resultSet.getLong("profileIdReceive"));
            connect.setConnectType(resultSet.getLong("connectType"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }
        return background;
    }
}
