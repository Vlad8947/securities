package com.vlad.securities.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.Calendar;

@Data
public class HistoryModel {

    @JacksonXmlProperty(localName = "BOARDID")
    private String boardId;
    @JacksonXmlProperty(localName = "TRADEDATE")
    private Calendar tradeDate;
    @JacksonXmlProperty(localName = "SECID")
    private String secId;
    @JacksonXmlProperty(localName = "NUMTRADES")
    private double numTrades;
    @JacksonXmlProperty(localName = "VALUE")
    private double value;
    @JacksonXmlProperty(localName = "OPEN")
    private double open;
    @JacksonXmlProperty(localName = "LOW")
    private double low;
    @JacksonXmlProperty(localName = "HIGH")
    private double high;
    @JacksonXmlProperty(localName = "LEGALCLOSEPRICE")
    private double legalClosePrice;
    @JacksonXmlProperty(localName = "WAPRICE")
    private double waPrice;
    @JacksonXmlProperty(localName = "CLOSE")
    private double close;
    @JacksonXmlProperty(localName = "VOLUME")
    private double volume;
    @JacksonXmlProperty(localName = "MARKETPRICE2")
    private double marketPrice2;
    @JacksonXmlProperty(localName = "MARKETPRICE3")
    private double marketPrice3;
    @JacksonXmlProperty(localName = "ADMITTEDQUOTE")
    private double admittedQuote;
    @JacksonXmlProperty(localName = "MP2VALTRD")
    private double mp2ValTrd;
    @JacksonXmlProperty(localName = "MARKETPRICE3TRADESVALUE")
    private double marketPrice3TradesValue;
    @JacksonXmlProperty(localName = "ADMITTEDVALUE")
    private double admittedValue;
    @JacksonXmlProperty(localName = "WAVAL")
    private double waVal;

}
