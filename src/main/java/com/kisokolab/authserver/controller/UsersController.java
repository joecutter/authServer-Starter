package com.kisokolab.authserver.controller;


import com.kisokolab.authserver.dao.UsersDao;
import com.kisokolab.authserver.model.res.ApiResModel;
import com.kisokolab.authserver.model.req.SignupReqModel;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api/user")
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
        return ResponseEntity.ok().body(usersDao.createUser(signupReqModel));
    }
    
    @PostMapping("/insert")
    public ResponseEntity<ApiResModel> insertUser(@Valid @RequestBody SignupReqModel signupReqModel) {
        logger.info("Sign up");
        if (usersDao.existsByEmail(signupReqModel.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResModel(400,false,"Error: account is already in use!"));
        }
        return ResponseEntity.ok().body(usersDao.insetUser(signupReqModel));
    }

    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(@Valid @RequestParam("username") String username) {
        logger.info("User Details");
        return ResponseEntity.ok().body(usersDao.findUserByEmail(username));
    }
}
