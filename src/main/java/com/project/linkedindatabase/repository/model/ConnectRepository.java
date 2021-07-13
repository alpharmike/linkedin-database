package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.repository.model.chat.ChatRepository;
import com.project.linkedindatabase.repository.types.ConnectTypeRepository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

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
        ps.execute();
    }

    public void update(Connect connect) throws SQLException {
        PreparedStatement updatePs = this.conn.prepareStatement("UPDATE "+this.tableName+" SET profileIdRequest=?," +
                " profileIdReceive=?, connectType=? WHERE id=?");
        updatePs.setLong(1, connect.getProfileIdRequest());
        updatePs.setLong(2, connect.getProfileIdReceive());
        updatePs.setLong(3, connect.getConnectType());
        updatePs.setLong(4, connect.getId());
        updatePs.executeUpdate();
    }

    @Override
    public void createTable() throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("create table if not exists " + this.tableName + "(" +
                "id bigint primary key not null auto_increment," +
                "profileIdRequest bigint not null," +
                "profileIdReceive bigint not null," +
                "connectType bigint not null," +
                "foreign key (profileIdRequest) references " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "foreign key (profileIdReceive) references " +  BaseEntity.getTableName(Profile.class) + "(id),"+
                "foreign key (connectType) references " +  BaseEntity.getTableName(ConnectType.class) + "(id)"+
            ")"
        );

        ps.execute();
    }



    @Override
    public Connect convertSql(ResultSet resultSet) throws SQLException {

        if(!resultSet.next()){
            return null;
        }
        Connect connect = new Connect();
        try {
            connect.setId(resultSet.getLong("id"));
            connect.setProfileIdRequest(resultSet.getLong("profileIdRequest"));
            connect.setProfileIdReceive(resultSet.getLong("profileIdReceive"));
            connect.setConnectType(resultSet.getLong("connectType"));
            return connect;
        } catch (SQLException s){
            System.out.println(s.getMessage());
            return null;
        }

    }

    public ArrayList<Connect> convertAllSql(ResultSet resultSet) throws SQLException {
        ArrayList<Connect> result = new ArrayList<>();
        while (resultSet.next()){
            Connect connect = new Connect();
            connect.setId(resultSet.getLong("id"));
            connect.setProfileIdRequest(resultSet.getLong("profileIdRequest"));
            connect.setProfileIdReceive(resultSet.getLong("profileIdReceive"));
            connect.setConnectType(resultSet.getLong("connectType"));
            result.add(connect);
        }
        return result;
    }

    public Connect getRequest(long profileIdRequest, long profileIdReceive) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "profileIdRequest=? AND profileIdReceive=?"
        );
        getRequestsPs.setLong(1, profileIdRequest);
        getRequestsPs.setLong(2, profileIdReceive);
        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertSql(resultSet);
    }

    public Connect getRequest(long profileIdRequest, long profileIdReceive, String status) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                        "profileIdRequest=? AND profileIdReceive=? AND connectType IN (SELECT id FROM "+
                        BaseEntity.getTableName(ConnectType.class)+" WHERE name=? )"
                );
        getRequestsPs.setLong(1, profileIdRequest);
        getRequestsPs.setLong(2, profileIdReceive);
        getRequestsPs.setString(3, status);
        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertSql(resultSet);
    }

    public ArrayList<Connect> getSenderRequests(long profileIdRequest, String status) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "profileIdRequest=? AND connectType IN (SELECT id FROM "+
                BaseEntity.getTableName(ConnectType.class)+" WHERE name=?)"
        );
        getRequestsPs.setLong(1, profileIdRequest);
        getRequestsPs.setString(2, status);
        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertAllSql(resultSet);
    }

    public ArrayList<Connect> getReceiverRequests(long profileIdReceive, String status) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "profileIdReceive=? AND connectType IN (SELECT id FROM "+
                BaseEntity.getTableName(ConnectType.class)+" WHERE name=?)"
        );
        getRequestsPs.setLong(1, profileIdReceive);
        getRequestsPs.setString(2, status);
        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertAllSql(resultSet);
    }

    public void sendRequest(long profileIdRequest, long profileIdReceive, String status) throws SQLException {
        if(!this.exists(profileIdRequest, profileIdReceive)){
            Connect connect = new Connect();
            connect.setProfileIdRequest(profileIdRequest);
            connect.setProfileIdReceive(profileIdReceive);
            connect.setConnectType(new ConnectTypeRepository().getByType(status).getId());
            this.save(connect);

            //don't forget to add chat creation
            ChatRepository chatRepository = new ChatRepository();
            if(!chatRepository.exists(profileIdRequest, profileIdReceive)){
                Chat chat = new Chat();
                chat.setProfileId1(profileIdRequest);
                chat.setProfileId2(profileIdReceive);
                chat.setIsArchive(false);
                chat.setMarkUnread(false);
                chatRepository.save(chat);
            }
        }
        else{
            Connect connect = this.getRequest(profileIdRequest, profileIdReceive);
            ConnectType rejected = new ConnectTypeRepository().getByType("block");
            ConnectType accepted = new ConnectTypeRepository().getByType("accept");
            ConnectType pending = new ConnectTypeRepository().getByType("pending");
            if(connect.getConnectType() != rejected.getId()){
                if(connect.getConnectType() == accepted.getId() && status.equals("block")){ // rejecting a request
                    connect.setConnectType(rejected.getId());
                    update(connect);
                }
                else if(connect.getConnectType() == pending.getId()){ // accepting a request
                    switch (status){
                        case "accept":
                            connect.setConnectType(accepted.getId());
                            update(connect);
                            break;
                        case "block":
                            connect.setConnectType(rejected.getId());
                            update(connect);
                            break;
                    }
                }
            }
            else{
                throw new SQLDataException("This Request has already been rejected");
            }
        }
    }

    public boolean exists(long profileIdRequest, long profileIdReceive) throws SQLException {
        return getRequest(profileIdRequest, profileIdReceive) != null;
    }
}
