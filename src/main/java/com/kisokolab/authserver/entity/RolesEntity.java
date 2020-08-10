package com.kisokolab.authserver.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="role")
public class RolesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private ERole name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_role",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id")
    })
    private List<PermissionEntity> permissions;

    public RolesEntity(ERole name) {
        this.name = name;
    }
}
