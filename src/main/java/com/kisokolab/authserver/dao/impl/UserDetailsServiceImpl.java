package com.kisokolab.authserver.dao.impl;

import com.kisokolab.authserver.entity.RolesEntity;
import com.kisokolab.authserver.entity.UsersEntity;
import com.kisokolab.authserver.repo.RolesRepo;
import com.kisokolab.authserver.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    RolesRepo rolesRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsersEntity> userEntity = usersRepo.findByEmail(email);
        if(userEntity.isPresent()){
            List<GrantedAuthority> authorities = getUserAuthority(userEntity.get().getRoles());
            return buildUserForAuthentication(userEntity.get(), authorities);
        }else {
            throw  new UsernameNotFoundException("User Not Found with email: " + email);
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<RolesEntity> userRoles) {
        List<GrantedAuthority> authorities = userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return authorities;
    }

    private UserDetails buildUserForAuthentication(UsersEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

