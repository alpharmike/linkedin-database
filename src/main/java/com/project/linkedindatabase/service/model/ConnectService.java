package com.project.linkedindatabase.service.model;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.jsonToPojo.ConnectJson;
import com.project.linkedindatabase.service.BaseService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ConnectService extends BaseService<Connect,Long> {

    public List<Connect> getReceiverRequestsBaseOnType(long profileIdReceive, String status) throws Exception;
    public List<Connect> getReceiverRequests(long profileIdReceive) throws Exception;

    public void sendRequestPending(long profileIdRequest, long profileIdReceive) throws Exception;
    public void sendRequestAccept(long profileIdRequest, long profileIdReceive) throws Exception;
    public void sendRequestBlock(long profileIdRequest, long profileIdReceive) throws Exception;

    public void sendRequest(long profileIdRequest, long profileIdReceive, String status) throws Exception;

    public List<Connect> getSenderRequestsBaseOnType(long profileIdRequest, String status) throws Exception;
    public List<Connect> getSenderRequests(long profileIdRequest) throws Exception;
    public Long getNumberOfConnection(Long profileId) throws Exception;

    public List<ConnectJson> getAllPending(Long profileIdReceiver) throws SQLException;
    public List<Map<String,Object>> profileYouMightKnow(Long id) throws Exception;
    public List<Map<String,Object>> searchBaseOfConnection(Long id, String name) throws Exception;

    public List<Profile> getAllPeopleInConnection(Long profileId) throws SQLException;



}
