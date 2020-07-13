package com.vlad.securities.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "histories")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "secId", referencedColumnName = "secId")
    private SecuritiesEntity security;

    @NotNull
    @NotEmpty
    @Column(length = 12)
    private String boardId;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date tradeDate;

    private Double
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
