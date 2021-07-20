package com.project.linkedindatabase.repository.model;

import com.project.linkedindatabase.domain.BaseEntity;
import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.domain.chat.Chat;
import com.project.linkedindatabase.jsonToPojo.ConnectJson;
import com.project.linkedindatabase.jsonToPojo.ProfileJson;
import com.project.linkedindatabase.repository.BaseRepository;
import com.project.linkedindatabase.repository.types.ConnectTypeRepository;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.model.chat.ChatService;
import com.project.linkedindatabase.service.types.ConnectTypeService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConnectRepository extends BaseRepository<Connect,Long> {

    private final ConnectTypeService connectTypeService;
    private final ProfileService profileService;
    private final ChatService chatService;

    public ConnectRepository(ConnectTypeService connectTypeService, ProfileService profileService, ChatService chatService) throws SQLException {
        super(Connect.class);
        this.connectTypeService = connectTypeService;
        this.profileService = profileService;
        this.chatService = chatService;
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
        // TODO: CHECK FOR CORRECTION
        /*if(!resultSet.next()){
            return null;
        }*/
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

    public List<Connect> convertAllSql(ResultSet resultSet) throws SQLException {
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
                "profileIdRequest = ? AND profileIdReceive = ?"
        );
        getRequestsPs.setLong(1, profileIdRequest);
        getRequestsPs.setLong(2, profileIdReceive);
        ResultSet resultSet = getRequestsPs.executeQuery();
        // TODO: ADDED FOR get-pending API ERROR
        resultSet.next();
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

    public List<Connect> getSenderRequestsBaseOnType(long profileIdRequest, String status) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "profileIdRequest=? AND connectType IN (SELECT id FROM "+
                BaseEntity.getTableName(ConnectType.class)+" WHERE name=?)"
        );
        getRequestsPs.setLong(1, profileIdRequest);
        getRequestsPs.setString(2, status);
        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertAllSql(resultSet);
    }

    public List<Connect> getSenderRequests(long profileIdRequest) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "profileIdRequest=? AND connectType IN (SELECT id FROM "+
                BaseEntity.getTableName(ConnectType.class)+" )"
        );
        getRequestsPs.setLong(1, profileIdRequest);


        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertAllSql(resultSet);
    }


    public List<Connect> getReceiverRequestsBaseOnType(long profileIdReceive, String status) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "profileIdReceive=? AND connectType IN (SELECT id FROM "+
                BaseEntity.getTableName(ConnectType.class)+" WHERE name=?)"
        );
        getRequestsPs.setLong(1, profileIdReceive);
        getRequestsPs.setString(2, status);
        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertAllSql(resultSet);
    }

    public List<Connect> getReceiverRequests(long profileIdReceive) throws SQLException {
        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT * FROM "+this.tableName+" WHERE " +
                "profileIdReceive=? AND connectType IN (SELECT id FROM "+
                BaseEntity.getTableName(ConnectType.class)+" )"
        );
        getRequestsPs.setLong(1, profileIdReceive);
        ResultSet resultSet = getRequestsPs.executeQuery();

        return this.convertAllSql(resultSet);
    }

    public void sendRequestPending(long profileIdRequest, long profileIdReceive) throws SQLException
    {
        String pending = connectTypeService.findByName("pending").getName();
        sendRequest(profileIdRequest,profileIdReceive,pending);
    }

    public void sendRequestBlock(long profileIdRequest, long profileIdReceive) throws SQLException
    {
        String block = connectTypeService.findByName("block").getName();
        sendRequest(profileIdRequest,profileIdReceive,block);
    }

    public void sendRequestAccept(long profileIdRequest, long profileIdReceive) throws SQLException
    {
        String accept = connectTypeService.findByName("accept").getName();
        sendRequest(profileIdRequest,profileIdReceive,accept);
        //todo must bring chat repository to chat

        /*if(!chatService.exists(profileIdRequest, profileIdReceive)){
            Chat chat = new Chat();
            chat.setProfileId1(profileIdRequest);
            chat.setProfileId2(profileIdReceive);
            chat.setIsArchive(false);
            chat.setMarkUnread(false);
            chatService.save(chat);
        }*/
    }
    public void sendRequest(long profileIdRequest, long profileIdReceive, String status) throws SQLException {

        if(!this.exists(profileIdRequest, profileIdReceive)){
            Connect connect = new Connect();
            connect.setProfileIdRequest(profileIdRequest);
            connect.setProfileIdReceive(profileIdReceive);
            connect.setConnectType(new ConnectTypeRepository().getByType(status).getId());


            this.save(connect);


        }
        else{
            Connect connect = this.getRequest(profileIdRequest, profileIdReceive);
            ConnectType rejected = connectTypeService.findByName("block");
            ConnectType accepted = connectTypeService.findByName("accept");
            ConnectType pending = connectTypeService.findByName("pending");
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

    public Long getNumberOfConnection(Long profileId) throws SQLException {

        PreparedStatement getRequestsPs = this.conn.prepareStatement("SELECT count(*) as number FROM "+this.tableName+" WHERE " +
                "(profileIdRequest = ? or profileIdReceive = ?)  and connectType in " +
                "( select "+ BaseEntity.getTableName(ConnectType.class)+".id from " + BaseEntity.getTableName(ConnectType.class)+
                " where " + BaseEntity.getTableName(ConnectType.class)+".name = 'accept')"
        );
        getRequestsPs.setLong(1, profileId);
        getRequestsPs.setLong(2, profileId);

        ResultSet resultSet = getRequestsPs.executeQuery();

        resultSet.next();
        return resultSet.getLong("number");
    }

    private ConnectJson convertToJson(Connect connect) throws SQLException {
        ConnectJson connectJson = ConnectJson.convertToJson(connect);
        System.out.println(connect.getProfileIdReceive());
        System.out.println(connect.getProfileIdRequest());
        System.out.println(connect.getConnectType());
        Profile profileReceiver = profileService.findById(connectJson.getProfileIdReceive());
        Profile profileRequest = profileService.findById(connectJson.getProfileIdRequest());
        String name = connectTypeService.findById(connectJson.getConnectType()).getName();

        connectJson.setConnectTypeName(name);
        connectJson.setProfileReceiver(ProfileJson.convertToJson(profileReceiver));
        connectJson.setProfileRequest(ProfileJson.convertToJson(profileRequest));

        return connectJson;

    }


    public List<ConnectJson> getAllPending(Long profileIdReceiver) throws SQLException
    {
        List<Connect> connects = findAllPending(profileIdReceiver);
        List<ConnectJson> connectJsons = new ArrayList<>();
        for (Connect i : connects)
        {
            ConnectJson connectJson = convertToJson(i);
            connectJsons.add(connectJson);
        }
        return connectJsons;
    }

    public List<Map<String,Object> >profileYouMightKnow(Long id) throws Exception {
        PreparedStatement ps = this.conn.prepareStatement(
                "select p.* ,(lf.num  + rt.num) as num from profile as p , \n" +
                        "((select search.profileIdReceive as ser , count(goal.id) as num from connect as search,connect_type,connect as goal\n" +
                        "where \n" +
                        "((search.profileIdRequest = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                        "or (search.profileIdRequest = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                        "and search.id != goal.id\n" +
                        " and search.connectType = connect_type.id\n" +
                        "and goal.connectType = connect_type.id and connect_type.name = 'accept' group by search.profileIdReceive)\n" +
                        "union\n" +
                        "(select pf.id as ser ,  0 as num from profile as pf where\n" +
                        "pf.id not in\n" +
                        "(select search.profileIdReceive as ser  from connect as search,connect_type,connect as goal\n" +
                        "where \n" +
                        "((search.profileIdRequest = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                        "or (search.profileIdRequest = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                        "and search.id != goal.id\n" +
                        " and search.connectType = connect_type.id\n" +
                        "and goal.connectType = connect_type.id and connect_type.name = 'accept')\n" +
                        "))\n" +
                        " as lf\n" +
                        ",\n" +
                        "((select search.profileIdRequest as ser , count(goal.id) as num from connect as search,connect_type,connect as goal\n" +
                        "where \n" +
                        "((search.profileIdReceive = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                        "or (search.profileIdReceive = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                        "and search.id != goal.id\n" +
                        " and search.connectType = connect_type.id\n" +
                        "and goal.connectType = connect_type.id and connect_type.name = 'accept' group by search.profileIdRequest)\n" +
                        "union \n" +
                        "select pf.id as ser ,  0 as num from profile as pf where\n" +
                        "pf.id not in\n" +
                        "(select search.profileIdRequest as ser from connect as search,connect_type,connect as goal\n" +
                        "where \n" +
                        "((search.profileIdReceive = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                        "or (search.profileIdReceive = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                        "and search.id != goal.id\n" +
                        " and search.connectType = connect_type.id\n" +
                        "and goal.connectType = connect_type.id and connect_type.name = 'accept' )\n" +
                        ") \n" +
                        " as rt\n" +
                        "where p.id = rt.ser and lf.ser = rt.ser and \n" +
                        " (lf.num  + rt.num) != 0 and " +
                        "p.id not in " +
                        "(select cn.profileIdRequest as pfid from connect as cn where cn.profileIdReceive = ? and connectType in (select cn_t.id from connect_type as cn_t where cn_t.name = 'accept')\n" +
                        "union\n" +
                        " select cn.profileIdReceive as pfid from connect as cn where cn.profileIdRequest = ? and connectType in  (select cn_t.id from connect_type as cn_t where cn_t.name = 'accept')\n" +
                        " union\n" +
                        " select pf.id as pfid from profile as pf where pf.id = ?  )" +
                        "ORDER BY num DESC"
        );

        ps.setLong(1,id);
        ps.setLong(2,id);
        ps.setLong(3,id);
        ps.setLong(4,id);
        ps.setLong(5,id);
        ps.setLong(6,id);
        ps.setLong(7,id);
        ps.setLong(8,id);
        ps.setLong(9,id);
        ps.setLong(10,id);
        ps.setLong(11,id);

        System.out.println(ps.toString());
        ResultSet resultSet = ps.executeQuery();
        List<Map<String,Object> >allObject = new ArrayList<>();
        while (resultSet.next()) {
            Map<String,Object>  objectMap = new HashMap<>();
            objectMap.put("person",profileService.convertSql(resultSet));
            Long mutualConnectionsCount = resultSet.getLong("num");
            objectMap.put("mutualConnectionsCount",mutualConnectionsCount);
            allObject.add(objectMap);
        }
        return allObject;


    }

    public List<Map<String,Object>> searchBaseOfConnection(Long id, String name) throws Exception {
        PreparedStatement ps = this.conn.prepareStatement("select p.* ,(lf.num  + rt.num) as num from profile as p , \n" +
                "((select search.profileIdReceive as ser , count(goal.id) as num from connect as search,connect_type,connect as goal\n" +
                "where \n" +
                "((search.profileIdRequest = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                "or (search.profileIdRequest = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                "and search.id != goal.id\n" +
                " and search.connectType = connect_type.id\n" +
                "and goal.connectType = connect_type.id and connect_type.name = 'accept' group by search.profileIdReceive)\n" +
                "union\n" +
                "(select pf.id as ser ,  0 as num from profile as pf where\n" +
                "pf.id not in\n" +
                "(select search.profileIdReceive as ser  from connect as search,connect_type,connect as goal\n" +
                "where \n" +
                "((search.profileIdRequest = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                "or (search.profileIdRequest = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                "and search.id != goal.id\n" +
                " and search.connectType = connect_type.id\n" +
                "and goal.connectType = connect_type.id and connect_type.name = 'accept')\n" +
                "))\n" +
                " as lf\n" +
                ",\n" +
                "((select search.profileIdRequest as ser , count(goal.id) as num from connect as search,connect_type,connect as goal\n" +
                "where \n" +
                "((search.profileIdReceive = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                "or (search.profileIdReceive = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                "and search.id != goal.id\n" +
                " and search.connectType = connect_type.id\n" +
                "and goal.connectType = connect_type.id and connect_type.name = 'accept' group by search.profileIdRequest)\n" +
                "union \n" +
                "select pf.id as ser ,  0 as num from profile as pf where\n" +
                "pf.id not in\n" +
                "(select search.profileIdRequest as ser from connect as search,connect_type,connect as goal\n" +
                "where \n" +
                "((search.profileIdReceive = goal.profileIdReceive and ? = goal.profileIdRequest )\n" +
                "or (search.profileIdReceive = goal.profileIdRequest and ? = goal.profileIdReceive ))\n" +
                "and search.id != goal.id\n" +
                " and search.connectType = connect_type.id\n" +
                "and goal.connectType = connect_type.id and connect_type.name = 'accept' )\n" +
                ") \n" +
                " as rt\n" +
                "where p.id = rt.ser and lf.ser = rt.ser and \n" +
                "(p.firstName like ? or p.lastName like ? or CONCAT( p.firstName,' ', p.lastName) like ? or p.username like ?)" +
                "ORDER BY num DESC"
        );
        ps.setLong(1,id);
        ps.setLong(2,id);
        ps.setLong(3,id);
        ps.setLong(4,id);
        ps.setLong(5,id);
        ps.setLong(6,id);
        ps.setLong(7,id);
        ps.setLong(8,id);
        ps.setString(9,name+"%");
        ps.setString(10,name+"%");
        ps.setString(11,name+"%");
        ps.setString(12,name+"%");

        System.out.println(ps.toString());
        ResultSet resultSet = ps.executeQuery();
        List<Map<String,Object> >allObject = new ArrayList<>();
        while (resultSet.next()) {
            Map<String,Object>  objectMap = new HashMap<>();
            objectMap.put("person",profileService.convertSql(resultSet));
            Long mutualConnectionsCount = resultSet.getLong("num");
            objectMap.put("mutualConnectionsCount",mutualConnectionsCount);
            allObject.add(objectMap);
        }
        return allObject;


    }

    public List<Connect> findAllPending(Long profileIdReceiver) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement("select * from " + this.tableName +  " as cn where " +
                "cn.profileIdReceive = ? and cn.connectType in " +
                "(select cn_t.id from connect_type as cn_t where cn_t.name = 'pending')"
        );

        ps.setLong(1,profileIdReceiver);

        ResultSet resultSet = ps.executeQuery();

        List<Connect> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(convertSql(resultSet));
        }
        return allObject;

    }

    @SneakyThrows
    public List<Profile> getAllPeopleInConnection(Long profileId) throws SQLException {

        PreparedStatement ps = this.conn.prepareStatement(
                "select * from profile as p where p.id in " +
                        "(select cn.profileIdRequest as pfid from connect as cn where cn.profileIdReceive = ? and connectType in" +
                        " (select cn_t.id from connect_type as cn_t where cn_t.name = 'accept') " +
                        " union " +
                        "select cn.profileIdReceive as pfid from connect as cn where cn.profileIdRequest = ? and connectType in" +
                        "  (select cn_t.id from connect_type as cn_t where cn_t.name = 'accept') );"
        );

        ps.setLong(1,profileId);
        ps.setLong(2,profileId);

        ResultSet resultSet = ps.executeQuery();
        List<Profile> allObject = new ArrayList<>();
        while (resultSet.next()) {
            allObject.add(profileService.convertSql(resultSet));
        }
        return allObject;
    }

    public boolean exists(long profileIdRequest, long profileIdReceive) throws SQLException {
        return getRequest(profileIdRequest, profileIdReceive) != null;
    }



}
