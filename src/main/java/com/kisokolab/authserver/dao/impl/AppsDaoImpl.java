package com.kisokolab.authserver.dao.impl;

import com.kisokolab.authserver.dao.AppsDao;
import com.kisokolab.authserver.entity.AppsEntity;
import com.kisokolab.authserver.model.req.ClientRegistrationReq;
import com.kisokolab.authserver.model.res.ApiResModel;
import com.kisokolab.authserver.repo.AppsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AppsDaoImpl implements AppsDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppsRepo appsRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ApiResModel createClient(ClientRegistrationReq clientRegistrationReq) {
        List<String> scope = Arrays.asList("read","write");
        List<String> authorized_grant_types =  Arrays.asList("authorization_code","check_token","refresh_token","password");
       try{
           AppsEntity appsEntity = new AppsEntity();
           appsEntity.setClientId(clientRegistrationReq.getClientName());
           appsEntity.setClient_secret(bCryptPasswordEncoder.encode(clientRegistrationReq.getPassword()));
           appsEntity.setResourceId(clientRegistrationReq.getClientName());
           appsEntity.setScope(scope);
           appsEntity.setAuthorized_grant_types(authorized_grant_types);
           appsRepo.save(appsEntity);
           return new ApiResModel(200, true, appsEntity);
       }catch (Exception ex){
           logger.error(ex.getMessage(), ex);
       }
        return new ApiResModel(400, false, "Failed to register the client "+clientRegistrationReq.getClientName());
    }

    @Override
    public boolean existsByClientId(String clientId) {
        return appsRepo.existsByClientId(clientId);
    }
}
