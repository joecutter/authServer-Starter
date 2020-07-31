package com.kisokolab.authserver.controller;


import com.kisokolab.authserver.dao.UsersDao;
import com.kisokolab.authserver.model.res.ApiResModel;
import com.kisokolab.authserver.model.req.SignupReqModel;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api/auth")
public class UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UsersDao usersDao;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupReqModel signupReqModel) {
        logger.info("Sign up");
        if (usersDao.existsByEmail(signupReqModel.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResModel(400,false,"Error: Email is already in use!"));
        }
        return ResponseEntity.ok(usersDao.createUser(signupReqModel));
    }
}
