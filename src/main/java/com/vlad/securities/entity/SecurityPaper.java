package com.vlad.securities.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "securities")
public class SecurityPaper {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "security")
    private List<History> histories;

    @NotNull
    @Column(length = 36)
    private String secId;

    @NotNull
    @Column(length = 189)
    private String shortName;

    @Column(length = 189)
    private String regNumber;

    @NotNull
    @Column(length = 765)
    private String name;

    @Column(length = 765)
    private String isin;

    @NotNull
    private int isTraded;

    private int emitentId;

    @Column(length = 765)
    private String emitentTitle;

    @Column(length = 30)
    private String emitentInn;

    @Column(length = 24)
    private String emitentOkpo;

    @Column(length = 189)
    private String gosReg;

    @Column(length = 93)
    private String type;

    @Column(length = 93)
    private String secGroup;

    @Column(length = 12)
    private String primaryBoardId;

    @Column(length = 12)
    private String marketPriceBoardId;

}
