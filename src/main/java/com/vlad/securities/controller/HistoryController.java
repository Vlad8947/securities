package com.vlad.securities.controller;

import com.vlad.securities.model.HistoryXmlModel;
import com.vlad.securities.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping(path = "/add-xml",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public HistoryXmlModel addFromXML(@RequestBody HistoryXmlModel historyXmlModel) {
        return historyXmlModel;
    }

    @GetMapping("/scan")
    public ResponseEntity<String> scan() throws IOException {
        historyService.saveFromLocalFiles();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
