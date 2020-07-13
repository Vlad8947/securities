package com.vlad.securities.controller;

import com.vlad.securities.model.HistoryXmlModel;
import com.vlad.securities.model.ShortModel;
import com.vlad.securities.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.vlad.securities.StaticResource.DATE_FORMAT_STR;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping(path = "/add-from-xml",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE},
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> addFromXML(@RequestBody HistoryXmlModel historyXmlModel) {
        historyService.saveXml(historyXmlModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/scan")
    public ResponseEntity<String> scan() throws IOException {
        historyService.saveFromLocalFiles();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/short-info")
    public List<ShortModel> shortInfo(@RequestParam(defaultValue = "name") String sortCol,
                                      @RequestParam(required = false) String emitentTitle,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = DATE_FORMAT_STR) Date beginDate,
                                      @RequestParam(required = false) @DateTimeFormat(pattern = DATE_FORMAT_STR) Date endDate) {

        if (emitentTitle != null) {
            emitentTitle = emitentTitle.isBlank() ? null : emitentTitle;
        }

        return historyService.findShort(
                emitentTitle,
                beginDate,
                endDate,
                sortCol);
    }

}
