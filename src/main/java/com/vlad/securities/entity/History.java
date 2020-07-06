package com.vlad.securities.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@Entity
@Table(name = "histories")
public class History {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotNull
    private SecurityPaper security;

    @NotNull
    @Column(length = 12)
    private String boardId;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Calendar tradeDate;

    private double
            numTrades,
            value,
            open,
            low,
            high,
            legalClosePrice,
            waPrice,
            close,
            volume,
            marketPrice2,
            marketPrice3,
            admittedQuote,
            mp2ValTrd,
            marketPrice3TradesValue,
            admittedValue,
            waVal;

}
