package com.kisokolab.authserver.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name="oauth_client_details")
public class OauthClientDetailsEntity implements Serializable {

    @Id
    @Column(name = "client_id")
    String clientId;
    @Column(name = "resource_ids")
    String resourceIds;
    @Column(name = "client_secret")
    String clientSecret;
    String scope;
    @Column(name = "authorized_grant_types")
    String authorizedGrantTypes;
    @Column(name = "web_server_redirect_uri")
    String webServerRedirectUri;
    String authorities;
    @Column(name = "access_token_validity")
    int accessTokenValidity;
    @Column(name = "refresh_token_validity")
    int refreshTokenValidity;
    @Column(name = "additional_information")
    String additionalInformation;
    String autoapprove;

    public OauthClientDetailsEntity() {
      this.accessTokenValidity = 1000000;
      this.refreshTokenValidity = 1000000;
    }
}
