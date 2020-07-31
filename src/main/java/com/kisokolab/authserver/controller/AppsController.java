package com.kisokolab.authserver.controller;

import com.kisokolab.authserver.dao.AppsDao;
import com.kisokolab.authserver.model.req.ClientRegistrationReq;
import com.kisokolab.authserver.model.res.ApiResModel;
import com.kisokolab.authserver.model.req.SignupReqModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api/client")
public class AppsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppsDao appsDao;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ClientRegistrationReq clientRegistrationReq) {
        logger.info("Register Application");
        if (appsDao.existsByClientId(clientRegistrationReq.getClientName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResModel(400,false,"Error: Client ID is already in use!"));
        }
        return ResponseEntity.ok(appsDao.createClient(clientRegistrationReq));
    }
}
