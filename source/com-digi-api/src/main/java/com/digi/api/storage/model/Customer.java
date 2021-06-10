package com.digi.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"customer")
public class Customer extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    @MapsId
    private Account account;
    private Date birthday;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "province_id")
    private Province province;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "district_id")
    private Province district;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ward_id")
    private Province ward;
    private String address;
    private Integer sex;
}
