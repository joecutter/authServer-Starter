package com.kisokolab.authserver.dao.impl;

import com.kisokolab.authserver.dao.OauthClientDetailsDao;
import com.kisokolab.authserver.entity.OauthClientDetailsEntity;
import com.kisokolab.authserver.model.req.ClientRegistrationReq;
import com.kisokolab.authserver.model.res.ApiResModel;
import com.kisokolab.authserver.repo.OauthClientDetailsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OauthClientDetailsDaoImpl implements OauthClientDetailsDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OauthClientDetailsRepo oauthClientDetailsRepo;
    @Autowired
    PasswordEncoder userPasswordEncoder;

    @Override
    public ApiResModel createClient(ClientRegistrationReq clientRegistrationReq) {
        List<String> scope = Arrays.asList("read","write");
        List<String> authorized_grant_types =  Arrays.asList("authorization_code","check_token","refresh_token","password");
       try{
           OauthClientDetailsEntity appsEntity = new OauthClientDetailsEntity();
           appsEntity.setClientId(clientRegistrationReq.getClientName());
           appsEntity.setClientSecret(userPasswordEncoder.encode(clientRegistrationReq.getPassword()));
           appsEntity.setResourceIds(clientRegistrationReq.getClientName());
           appsEntity.setScope(scope.toString());
           appsEntity.setAuthorizedGrantTypes(authorized_grant_types.toString());
           oauthClientDetailsRepo.save(appsEntity);
           return new ApiResModel(200, true, appsEntity);
       }catch (Exception ex){
           logger.error(ex.getMessage(), ex);
       }
        return new ApiResModel(400, false, "Failed to register the client "+clientRegistrationReq.getClientName());
    }

    @Override
    public boolean existsByClientId(String clientId) {
        return oauthClientDetailsRepo.existsByClientId(clientId);
    }
}
