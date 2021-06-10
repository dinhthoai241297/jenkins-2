package com.digi.api.storage.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"work_field")
public class WorkField extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String imagePath;


    @Column(name = "desc_basic", columnDefinition = "TEXT")
    private String descriptionBasic;
    @Column(name = "desc_medium", columnDefinition = "TEXT")
    private String descriptionMedium;
    @Column(name = "desc_advanced", columnDefinition = "TEXT")
    private String descriptionAdvanced;
}
