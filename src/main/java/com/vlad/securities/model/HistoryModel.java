package com.vlad.securities.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.Date;

@Data
public class HistoryModel {

    @JacksonXmlProperty(localName = "BOARDID")
    private String boardId;
    @JacksonXmlProperty(localName = "TRADEDATE")
    private Date tradeDate;
    @JacksonXmlProperty(localName = "SECID")
    private String secId;
    @JacksonXmlProperty(localName = "NUMTRADES")
    private Double numTrades;
    @JacksonXmlProperty(localName = "VALUE")
    private Double value;
    @JacksonXmlProperty(localName = "OPEN")
    private Double open;
    @JacksonXmlProperty(localName = "LOW")
    private Double low;
    @JacksonXmlProperty(localName = "HIGH")
    private Double high;
    @JacksonXmlProperty(localName = "LEGALCLOSEPRICE")
    private Double legalClosePrice;
    @JacksonXmlProperty(localName = "WAPRICE")
    private Double waPrice;
    @JacksonXmlProperty(localName = "CLOSE")
    private Double close;
    @JacksonXmlProperty(localName = "VOLUME")
    private Double volume;
    @JacksonXmlProperty(localName = "MARKETPRICE2")
    private Double marketPrice2;
    @JacksonXmlProperty(localName = "MARKETPRICE3")
    private Double marketPrice3;
    @JacksonXmlProperty(localName = "ADMITTEDQUOTE")
    private Double admittedQuote;
    @JacksonXmlProperty(localName = "MP2VALTRD")
    private Double mp2ValTrd;
    @JacksonXmlProperty(localName = "MARKETPRICE3TRADESVALUE")
    private Double marketPrice3TradesValue;
    @JacksonXmlProperty(localName = "ADMITTEDVALUE")
    private Double admittedValue;
    @JacksonXmlProperty(localName = "WAVAL")
    private Double waVal;

}
