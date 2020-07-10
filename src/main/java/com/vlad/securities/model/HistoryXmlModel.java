package com.vlad.securities.model;

import lombok.Data;

import java.util.List;

@Data
public class HistoryXmlModel {


    private List<DataModel<String>> document;

}
