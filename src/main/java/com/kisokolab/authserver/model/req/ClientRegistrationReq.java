package com.kisokolab.authserver.model.req;

import lombok.Data;

@Data
public class ClientRegistrationReq {
    String clientName;
    String password;
}
