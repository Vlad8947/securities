package com.vlad.securities.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vlad.securities.entity.SecuritiesEntity;
import com.vlad.securities.model.SecuritiesModel;
import com.vlad.securities.model.SecuritiesXmlModel;
import com.vlad.securities.repository.SecuritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecuritiesService {

    private static final String localPath = "./securities";
    private static final String fileStartsWith = "securities_";
    private static final String fileEndsWith = ".xml";

    @Autowired
    private SecuritiesRepository securitiesRepository;

    public void saveFromLocalFiles() throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        File dirFile = new File(localPath);
        if (!dirFile.isDirectory()) {
            throw new FileNotFoundException("Securities local path is not directory");
        }
        for (File secXmlFile: dirFile.listFiles()) {
            if (secXmlFile.isFile()) {
                String secXmlFileName = secXmlFile.getName();
                if (secXmlFileName.startsWith(fileStartsWith) && secXmlFileName.endsWith(fileEndsWith)) {
                    SecuritiesXmlModel xmlModel = objectMapper.readValue(secXmlFile, SecuritiesXmlModel.class);
                    saveXml(xmlModel);
                }
            }
        }
    }

    public void saveXml(SecuritiesXmlModel securitiesXmlModel) {
        securitiesRepository.saveAll(convert(securitiesXmlModel.getData().getRows()));
    }

    private List<SecuritiesEntity> convert(List<SecuritiesModel> modelList) {
        return modelList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private SecuritiesEntity convert(SecuritiesModel model) {
        return new SecuritiesEntity(
            0, null, model.getSecId(),
                model.getShortName(), model.getName(),
                model.getIsTraded(), model.getRegNumber(),
                model.getIsIn(), model.getEmitentId(),
                model.getEmitentTitle(), model.getEmitentInn(),
                model.getEmitentOkpo(), model.getGosReg(),
                model.getType(), model.getGroup(),
                model.getPrimaryBoardId(), model.getMarketPriceBoardId()
        );
    }

}
