package com.tsp.backbone.db.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "TSP_USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", insertable = false, unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "V_LOGIN", unique = true, nullable = false)
    private String login;

    @Column(name = "V_PASSWORD", nullable = false)
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CREATED", updatable = false)
    private Calendar createDT;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_UPDATED")
    private Calendar updateDT;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLES_MAP",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;
}
