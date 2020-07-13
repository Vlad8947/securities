package com.vlad.securities;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StaticResource {

    public static final XmlMapper XML_MAPPER = new XmlMapper();
    public static final String DATE_FORMAT_STR = "yyyy-MM-dd";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STR);

    static {
        XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}
