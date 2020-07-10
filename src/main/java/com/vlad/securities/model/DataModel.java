package com.vlad.securities.model;

import lombok.Data;

import java.util.List;

@Data
public class DataModel<M> {

    private String id;
    private List<M> rows;

}
