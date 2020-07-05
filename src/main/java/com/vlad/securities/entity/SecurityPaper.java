package com.vlad.securities.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "securities")
public class SecurityPaper {

    @Id
    private int id;

    @NotNull
    @Column(length = 36)
    private String secId;

    @NotNull
    @Column(length = 189)
    private String shortName;

    @NotNull
    @Column(length = 189)
    private String regNumber;

    @NotNull
    @Column(length = 765)
    private String name;

    @NotNull
    @Column(length = 765)
    private String isin;

    @NotNull
    private int isTraded;

    @NotNull
    private int emitentId;

    @NotNull
    @Column(length = 765)
    private String emitentTitle;

    @NotNull
    @Column(length = 30)
    private String emitentInn;

    @NotNull
    @Column(length = 24)
    private String emitentOkpo;

    @NotNull
    @Column(length = 189)
    private String gosReg;

    @NotNull
    @Column(length = 93)
    private String type;

    @NotNull
    @Column(length = 93)
    private String group;

    @NotNull
    @Column(length = 12)
    private String primaryBoardId;

    @NotNull
    @Column(length = 12)
    private String marketPriceBoardId;

}
