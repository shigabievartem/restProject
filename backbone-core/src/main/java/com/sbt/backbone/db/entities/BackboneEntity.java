package com.sbt.backbone.db.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Сущность взаимодействия с БД
 */
@Entity
@Table(name = "BB_ROOT_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackboneEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", insertable = false, unique = true)
    private Long id;

    @Column(name = "NAME")
    private String  name;

    @Column(name = "IS_OK")
    private Boolean isOk = false;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_LAST_UPDATE")
    private Calendar lastUpdate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CREATE", updatable = false)
    private Calendar dtCreate;
}
