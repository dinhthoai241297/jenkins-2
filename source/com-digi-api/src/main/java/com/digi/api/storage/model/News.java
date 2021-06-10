package com.digi.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"new")
public class News extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    private String avatar;
    private String description;
    private Integer kind;
    private Integer ordering;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
}
