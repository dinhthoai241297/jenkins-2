package com.digi.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"province")
public class Province extends  Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Province parentProvince;
    @OneToMany(mappedBy = "parentProvince", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Province> provinceList;
    private String kind;
}
