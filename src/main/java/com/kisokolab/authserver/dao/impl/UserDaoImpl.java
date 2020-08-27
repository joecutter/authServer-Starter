package com.kisokolab.authserver.dao.impl;

import com.kisokolab.authserver.dao.UsersDao;
import com.kisokolab.authserver.entity.ERole;
import com.kisokolab.authserver.entity.RolesEntity;
import com.kisokolab.authserver.entity.UsersEntity;
import com.kisokolab.authserver.model.res.ApiResModel;
import com.kisokolab.authserver.model.req.SignupReqModel;
import com.kisokolab.authserver.repo.RolesRepo;
import com.kisokolab.authserver.repo.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDaoImpl implements UsersDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    RolesRepo rolesRepo;

    @Autowired
    PasswordEncoder userPasswordEncoder;

    @Override
    public ApiResModel createUser(SignupReqModel signupReqModel) {
        logger.info("Creating user \n" + signupReqModel.toString());
        String msg = "User Created Successfully";
        try {
            UsersEntity user = new UsersEntity();
            user.setEmail(signupReqModel.getEmail());
            user.setPassword(userPasswordEncoder.encode(signupReqModel.getPassword()));
            user.setUsername(signupReqModel.getUsername());
            user.setPhone(signupReqModel.getPhone());
            user.setEnabled(false);

            //Set  roles
            Set<RolesEntity> roles = setRoles(signupReqModel);

            user.setRoles(roles);
            usersRepo.save(user);
            logger.info("\n====== DONE CREATING USER ======\n" + user.toString());
            return new ApiResModel(200, true, msg);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ApiResModel(400, false, "Fail to Create User");
    }

    @Override
    public ApiResModel insetUser(SignupReqModel signupReqModel) {
        logger.info("Insert user account \n" + signupReqModel.toString());
        String msg = "User Inserted Successfully";
        try {
            UsersEntity user = new UsersEntity();
            user.setEmail(signupReqModel.getEmail());
            user.setPassword(signupReqModel.getPassword());
            user.setUsername(signupReqModel.getUsername());
            user.setPhone(signupReqModel.getPhone());
            user.setEnabled(true);

            //Set  roles
            Set<RolesEntity> roles = setRoles(signupReqModel);

            user.setRoles(roles);
            usersRepo.save(user);
            logger.info("\n====== DONE INSERTING USER ======\n" + user.toString());
            return new ApiResModel(200, true, msg);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ApiResModel(400, false, "Fail to Create User");
    }

    @Override
    public Optional<UsersEntity> findUserByEmail(String email) {
        return usersRepo.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepo.existsByEmail(email);
    }

    @Override
    public String disableUser(String email, boolean status) {
        try {
            usersRepo.findByEmail(email).ifPresent(usersEntity -> {
                usersEntity.setEnabled(status);
                usersRepo.save(usersEntity);
            });
            return "Status Successfully Updated";
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return "Failed to updated status";
    }

    @Override
    public List<UsersEntity> findAllUsers() {
        return usersRepo.findAll();
    }

    private Set<RolesEntity> setRoles(SignupReqModel signupReqModel) {
        Set<String> strRoles = signupReqModel.getRoles();
        Set<RolesEntity> roles = new HashSet<>();

        if (strRoles == null) {
            RolesEntity userRole = rolesRepo.findByName(ERole.ROLE_USER).get();
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        RolesEntity adminRole = rolesRepo.findByName(ERole.ROLE_ADMIN).get();
                        roles.add(adminRole);

                        break;
                    case "MOD":
                        RolesEntity modRole = rolesRepo.findByName(ERole.ROLE_MODERATOR).get();
                        roles.add(modRole);

                        break;
                    case "AGENT":
                        RolesEntity agentRole = rolesRepo.findByName(ERole.ROLE_AGENT).get();
                        roles.add(agentRole);

                        break;
                    case "MERCHANT":
                        RolesEntity merchantRole = rolesRepo.findByName(ERole.ROLE_MERCHANT).get();
                        roles.add(merchantRole);

                        break;
                    case "VENDOR":
                        RolesEntity vendorRole = rolesRepo.findByName(ERole.ROLE_VENDOR).get();
                        roles.add(vendorRole);

                        break;
                    default:
                        RolesEntity userRole = rolesRepo.findByName(ERole.ROLE_USER).get();
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
}
