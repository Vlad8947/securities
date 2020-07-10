package com.vlad.securities.controller;

import com.vlad.securities.model.SecuritiesXmlModel;
import com.vlad.securities.service.SecuritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/securities")
public class SecuritiesController {

    @Autowired
    private SecuritiesService securitiesService;

    @PostMapping(path = "/add-from-xml",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> addFromXML(@RequestBody SecuritiesXmlModel securitiesXml) {
        securitiesService.saveXml(securitiesXml);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("scan")
    public ResponseEntity<String> scan() throws IOException {
        securitiesService.saveFromLocalFiles();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
