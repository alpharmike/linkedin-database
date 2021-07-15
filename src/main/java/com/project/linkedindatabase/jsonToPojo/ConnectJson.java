package com.project.linkedindatabase.jsonToPojo;

import com.project.linkedindatabase.domain.Connect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectJson {
    private Long id;

    private Long profileIdRequest;
    private ProfileJson profileRequest;

    private Long profileIdReceive; // foreign key to profile
    private ProfileJson profileReceiver;

    private Long connectType; // foreign key to connectType ==>enum
    private String connectTypeName;

    public static ConnectJson convertToJson(Connect connect)
    {
        ConnectJson connectJson = new ConnectJson();

        connectJson.setConnectType(connect.getConnectType());
        connectJson.setId(connect.getId());
        connectJson.setProfileIdReceive(connect.getProfileIdReceive());
        connectJson.setProfileIdRequest(connect.getProfileIdRequest());

        return connectJson;
    }

    public  Connect convertToJson( )
    {
        Connect connect = new Connect();

        connect.setConnectType(getConnectType());
        connect.setId(getId());
        connect.setProfileIdReceive(getProfileIdReceive());
        connect.setProfileIdRequest(getProfileIdRequest());

        return connect;
    }
}
