package com.vlad.securities.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SecuritiesModel {

    private int id;

    @JacksonXmlProperty(localName = "secid")
    private String secId;
    @JacksonXmlProperty(localName = "shortname")
    private String shortName;
    private String name;
    @JacksonXmlProperty(localName = "regnumber")
    private String regNumber;
    @JacksonXmlProperty(localName = "is_traded")
    private Integer isTraded;
    @JacksonXmlProperty(localName = "isin")
    private String isIn;
    @JacksonXmlProperty(localName = "emitent_id")
    private Integer emitentId;
    @JacksonXmlProperty(localName = "emitent_title")
    private String emitentTitle;
    @JacksonXmlProperty(localName = "emitent_inn")
    private String emitentInn;
    @JacksonXmlProperty(localName = "emitent_okpo")
    private String emitentOkpo;
    @JacksonXmlProperty(localName = "gosreg")
    private String gosReg;
    private String type;
    private String group;
    @JacksonXmlProperty(localName = "primary_boardid")
    private String primaryBoardId;
    @JacksonXmlProperty(localName = "marketprice_boardid")
    private String marketPriceBoardId;

}
