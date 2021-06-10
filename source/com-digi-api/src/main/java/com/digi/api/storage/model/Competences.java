package com.digi.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"competences")
public class Competences extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(name = "desc_basic", columnDefinition = "TEXT")
    private String descriptionBasic;
    @Column(name = "desc_medium", columnDefinition = "TEXT")
    private String descriptionMedium;
    @Column(name = "desc_advanced", columnDefinition = "TEXT")
    private String descriptionAdvanced;

    @ManyToOne(optional = false)
    @JoinColumn(name = "survey_profile_id")
    private SurveyProfile surveyProfile;

    @ManyToOne(optional = false)
    @JoinColumn(name = "work_field_id")
    private WorkField workField;

}
