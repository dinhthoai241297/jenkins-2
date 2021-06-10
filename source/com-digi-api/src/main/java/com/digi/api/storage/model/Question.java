package com.digi.api.storage.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = TablePrefix.PREFIX_TABLE+"question")
public class Question extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    //Json chua cau hoi va cau tra loi
    @Column(name = "question", columnDefinition = "TEXT")
    private String question;
    private String avatarPath;
    /**
     * vi du file excel, file hinh
     * */
    private String attachmentPath;
    private Integer score;
    /**
     * 1 -- Cau hoi -> chon dap an dung
     * 2 - Cau hoi -> dien cau tra loi
     * 3 -- Tai tai lieu -> dien cau tra loi
     * 4 - Tai tai lieu -> chon dap an dung
     * 5 -- Tap hop nhieu cau hoi co cung cau tra loi -> tra loi tat ca cac cau hoi
     * 6 -- Open url -> dien cau tra loi
     * 7 - Open url -> chon dap an dung
     * */
    private Integer kind;// loai cau hoi:

    @ManyToOne(optional = false)
    @JoinColumn(name = "competences_id")
    private Competences competences;
}
