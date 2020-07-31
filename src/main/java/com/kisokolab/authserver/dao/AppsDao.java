package com.kisokolab.authserver.dao;

import com.kisokolab.authserver.entity.AppsEntity;
import com.kisokolab.authserver.model.req.ClientRegistrationReq;
import com.kisokolab.authserver.model.res.ApiResModel;

public interface AppsDao {
    ApiResModel createClient(ClientRegistrationReq clientRegistrationReq);
    boolean existsByClientId(String clientId);
}
