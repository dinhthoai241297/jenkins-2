package com.digi.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"survey_profile")
public class SurveyProfile extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "content", columnDefinition = "TEXT")
    private String description;
    private String imagePath;
}
