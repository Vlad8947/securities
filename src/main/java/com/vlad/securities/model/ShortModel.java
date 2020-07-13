package com.vlad.securities.model;

import com.vlad.securities.structure.HistoryStructure;
import com.vlad.securities.structure.SecuritiesStructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortModel {

    public static List<String> securitiesCols = Arrays.asList(
            SecuritiesStructure.SEC_ID,
            SecuritiesStructure.REG_NUMBER,
            SecuritiesStructure.NAME,
            SecuritiesStructure.EMITENT_TITLE);

    public static  List<String> historyCols = Arrays.asList(
            HistoryStructure.TRADE_DATE,
            HistoryStructure.NUM_TRADES,
            HistoryStructure.OPEN,
            HistoryStructure.CLOSE);

    private String
            secId,
            regNumber,
            name,
            emitentTitle,
            tradeDate;
    private Double
            numTrades,
            open,
            close;

}
