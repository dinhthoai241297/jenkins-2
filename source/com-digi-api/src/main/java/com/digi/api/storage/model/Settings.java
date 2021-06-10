/*
 * File Created: Friday, 29th January 2021 10:49:48 pm
 * Author: Bui Si Quan (18110041@student.hcmute.edu.vn)
 * -----
 */
package com.digi.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"settings")
public class Settings extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "settings_key", unique = true)
    private String key;

    @Column(name = "settings_value")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "settings_group")
    private String group;

    @Column(name = "editable")
    private boolean editable;
}
