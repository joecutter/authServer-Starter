package com.kisokolab.authserver.service;

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

import java.util.ArrayList;
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
        Optional<UsersEntity> optionalUsersEntity = usersRepo.findByEmail(email);
        optionalUsersEntity.orElseThrow(()->new UsernameNotFoundException("Username or password wrong"));
        System.out.println("Load User By Username "+optionalUsersEntity.toString());
        List<GrantedAuthority> authorities = getUserAuthority(optionalUsersEntity.get().getRoles());
        return buildUserForAuthentication(optionalUsersEntity.get(), authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<RolesEntity> rolesEntities) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        rolesEntities.forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
            role.getPermissions().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
        });
        return authorities;
    }

    private UserDetails buildUserForAuthentication(UsersEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

