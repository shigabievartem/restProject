package com.tsp.backbone.db.entities;

import com.tsp.backbone.rest.enums.RoleNames;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TSP_ROLES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID", insertable = false, unique = true, nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "V_ROLE", unique = true, nullable = false)
    @NonNull
    private RoleNames role;

    @ManyToMany(mappedBy = "roles")
    private Set<Role> userList;
}