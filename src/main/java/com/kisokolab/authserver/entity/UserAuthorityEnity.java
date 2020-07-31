package com.kisokolab.authserver.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="userAuthority")
@Entity
public class UserAuthorityEnity implements Serializable {

    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "authority_id")
    private int authorityId;
}
