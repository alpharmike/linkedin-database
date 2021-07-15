package com.project.linkedindatabase.controller;


import com.project.linkedindatabase.domain.Connect;
import com.project.linkedindatabase.domain.Profile;
import com.project.linkedindatabase.domain.Type.ConnectType;
import com.project.linkedindatabase.jsonToPojo.ConnectJson;
import com.project.linkedindatabase.service.jwt.JwtUserDetailsService;
import com.project.linkedindatabase.service.model.ConnectService;
import com.project.linkedindatabase.service.model.ProfileService;
import com.project.linkedindatabase.service.types.ConnectTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class ConnectController {

    private final ConnectService connectService;
    private final ProfileService profileService ;
    private final ConnectTypeService connectTypeService;


    public ConnectController(ConnectService connectService, ProfileService profileService,
                             ConnectTypeService connectTypeService) {
        this.connectService = connectService;
        this.profileService = profileService;
        this.connectTypeService = connectTypeService;
    }

    @GetMapping("/number-connection")
    public Long getNumberOfConnection(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {

            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);


            return connectService.getNumberOfConnection(profile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }
    }

    @GetMapping("/number-connection/{id}")
    public Long getProfileNumberOfConnection(@RequestHeader Map<String, Object> jsonHeader, @PathVariable(name = "id") Long profileId) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Profile profile = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader);
            return connectService.getNumberOfConnection(profileId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/get-receiver-connection")
    public List<Map<String,Object>> getReceiverConnection(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Long profileId = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader).getId();

            List<Connect> connects = connectService.getReceiverRequests(profileId);
            List<ConnectType> types = connectTypeService.findAll();

            List<Map<String, Object>> map = convertToApiData(connects,types);

            return map;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/get-pending")
    public List<ConnectJson> getPending(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Long profileId = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader).getId();

            List<ConnectJson> connectJsons = connectService.getAllPending(profileId);

            return connectJsons;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @GetMapping("/get-sender-connection")
    public List<Map<String,Object>> getRequestConnection(@RequestHeader Map<String, Object> jsonHeader) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Long profileId = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader).getId();


            List<Connect> connects = connectService.getSenderRequests(profileId);
            List<ConnectType> types = connectTypeService.findAll();

            List<Map<String, Object>> map = convertToApiData(connects,types);


            return map;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @PostMapping("/send-pending-connection")
    public void sendPendingConnectionRequest(@RequestHeader Map<String, Object> jsonHeader,@RequestBody Connect connect) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);

        try {
            Long profileIdSender = new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader).getId();

            Long ProfileIdReceiver = connect.getProfileIdReceive();

            if (ProfileIdReceiver == profileIdSender)
                throw new Exception("they share same id");
            connectService.sendRequestPending(profileIdSender,ProfileIdReceiver);



        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }


    }

    @PostMapping("/accept-reject-connection")
    public void AcceptOrRejectConnection(@RequestHeader Map<String, Object> jsonHeader,@RequestBody Connect connect) {
        String token = JwtUserDetailsService.getTokenByHeader(jsonHeader);
        Long profileIdReceiver;
        try {
            profileIdReceiver= new JwtUserDetailsService(profileService).getProfileByHeader(jsonHeader).getId();




        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with token ", e);
        }

        try {
            Long profileIdSender = connect.getProfileIdRequest();

            if (profileIdReceiver == profileIdSender)
                throw new Exception("they share same id");

            Long accept = connectTypeService.findByName("accept").getId();
            Long block = connectTypeService.findByName("block").getId();
            if (accept == connect.getConnectType()){
            connectService.sendRequestAccept(profileIdSender,profileIdReceiver);}
            else if (block == connect.getConnectType()){
                connectService.sendRequestBlock(profileIdSender,profileIdReceiver);}
            else
            {
                throw new Exception("it is not a valid type");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "There is a problem with data ", e);
        }


    }

    private List<Map<String, Object>> convertToApiData( List<Connect> connects,List<ConnectType> types) throws Exception {

        List<Map<String,Object>> map = new ArrayList<>();


        for (Connect connect : connects)
        {
            HashMap<String,Object> item = new HashMap<>();
            item.put("type",connect.getConnectType());
            item.put("id",connect.getId());
            item.put("profileIdReceive",connect.getProfileIdReceive());
            item.put("profileIdRequest",connect.getProfileIdRequest());

            for (ConnectType i : types)
            {
                if( i.getId().equals(connect.getConnectType()))
                {
                    item.put("typeName",i.getName());
                    break;
                }
            }

            map.add(item);

        }
        return map;
    }
}
