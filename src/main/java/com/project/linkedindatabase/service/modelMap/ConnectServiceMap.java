package com.project.linkedindatabase.service.modelMap;

import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.jsonToPojo.ConnectJson;
import com.project.linkedindatabase.repository.model.ConnectRepository;
import com.project.linkedindatabase.service.model.ConnectService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class ConnectServiceMap implements ConnectService {

    private final ConnectRepository connectRepository;

    public ConnectServiceMap(ConnectRepository connectRepository) throws SQLException {

        this.connectRepository = connectRepository;
    }

    @Override
    public Connect findById(Long aLong) throws SQLException {
        return connectRepository.findById(aLong);
    }

    @Override
    public void save(Connect object) throws SQLException {
        connectRepository.save(object);
    }

    @Override
    public List<Connect> findAll() throws SQLException {
        return connectRepository.findAll();
    }

    @Override
    public void deleteByObject(Connect object) throws SQLException {
        connectRepository.deleteByObject(object);
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        connectRepository.deleteById(aLong);
    }

    @Override
    public void createTable() throws SQLException {
        connectRepository.createTable();
    }

    @Override
    public List<Connect> getReceiverRequestsBaseOnType(long profileIdReceive, String status) throws Exception {
        return connectRepository.getReceiverRequestsBaseOnType(profileIdReceive,status);
    }

    @Override
    public List<Connect> getReceiverRequests(long profileIdReceive) throws Exception {
        return connectRepository.getReceiverRequests(profileIdReceive);
    }

    @Override
    public void sendRequestPending(long profileIdRequest, long profileIdReceive) throws Exception {
        connectRepository.sendRequestPending(profileIdRequest,profileIdReceive);
    }

    @Override
    public void sendRequestAccept(long profileIdRequest, long profileIdReceive) throws Exception {
        connectRepository.sendRequestAccept(profileIdRequest,profileIdReceive);
    }

    @Override
    public void sendRequestBlock(long profileIdRequest, long profileIdReceive) throws Exception {
        connectRepository.sendRequestBlock(profileIdRequest,profileIdReceive);

    }

    @Override
    public void sendRequest(long profileIdRequest, long profileIdReceive, String status) throws Exception {
        connectRepository.sendRequest(profileIdRequest,profileIdReceive,status);
    }

    @Override
    public List<Connect> getSenderRequestsBaseOnType(long profileIdRequest, String status) throws Exception {
        return connectRepository.getSenderRequestsBaseOnType(profileIdRequest,status);
    }

    @Override
    public List<Connect> getSenderRequests(long profileIdRequest) throws Exception {
        return connectRepository.getSenderRequests(profileIdRequest);
    }

    @Override
    public Long getNumberOfConnection(Long profileId) throws Exception {
        return connectRepository.getNumberOfConnection(profileId);
    }

    @Override
    public List<ConnectJson> getAllPending(Long profileIdReceiver) throws SQLException {
        return connectRepository.getAllPending(profileIdReceiver);
    }

    @Override
    public List<Map<String,Object>> profileYouMightKnow(Long id) throws Exception
    {
        return connectRepository.profileYouMightKnow(id);
    }

    @Override
    public  List<Map<String,Object>> searchBaseOfConnection(Long id, String name) throws Exception {
        return connectRepository.searchBaseOfConnection(id,name);
    }

    @Override
    public List<Profile> getAllPeopleInConnection(Long profileId) throws SQLException
    {
        return connectRepository.getAllPeopleInConnection(profileId);
    }
}
