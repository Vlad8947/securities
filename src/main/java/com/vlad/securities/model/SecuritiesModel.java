package com.vlad.securities.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SecuritiesModel {

    private int id;

    @NotBlank
    @JacksonXmlProperty(localName = "secid")
    private String secId;

    @NotBlank
    @JacksonXmlProperty(localName = "shortname")
    private String shortName;

    @NotBlank
    private String name;

    @JacksonXmlProperty(localName = "regnumber")
    private String regNumber;
    @JacksonXmlProperty(localName = "is_traded")
    private int isTraded;
    @JacksonXmlProperty(localName = "isin")
    private String isIn;
    @JacksonXmlProperty(localName = "emitent_id")
    private int emitentId;
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
