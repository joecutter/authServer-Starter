package com.kisokolab.authserver.dao;

import com.kisokolab.authserver.entity.UsersEntity;
import com.kisokolab.authserver.model.res.ApiResModel;
import com.kisokolab.authserver.model.req.SignupReqModel;


import java.util.List;
import java.util.Optional;

public interface UsersDao {
    ApiResModel createUser(SignupReqModel signupReqModel);
    ApiResModel insetUser(SignupReqModel signupReqModel);
    Optional<UsersEntity> findUserByEmail(String email);
    boolean existsByEmail(String email);
    String disableUser(String email, boolean status);
    List<UsersEntity> findAllUsers();
}
