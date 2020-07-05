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

    @NotNull
    @Column(length = 12)
    private String boardId;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Calendar tradeDate;

    @NotNull
    @Column(length = 189)
    private String shortName;

    @NotNull
    @Column(length = 36)
    private String secId;

    @NotNull
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
