package com.vlad.securities.service;

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

import static com.vlad.securities.StaticResource.XML_MAPPER;

@Service
public class SecuritiesService {

    private static final String localPath = "./securities";
    private static final String fileStartsWith = "securities_";
    private static final String fileEndsWith = ".xml";

    private SecuritiesRepository securitiesRepository;

    @Autowired
    public SecuritiesService(SecuritiesRepository securitiesRepository) {
        this.securitiesRepository = securitiesRepository;
    }

    public void saveFromLocalFiles() throws IOException {
        File dirFile = new File(localPath);
        if (!dirFile.isDirectory()) {
            throw new FileNotFoundException("Securities local path is not directory");
        }
        for (File secXmlFile: dirFile.listFiles()) {
            if (secXmlFile.isFile()) {
                String secXmlFileName = secXmlFile.getName();
                if (secXmlFileName.startsWith(fileStartsWith) && secXmlFileName.endsWith(fileEndsWith)) {
                    SecuritiesXmlModel xmlModel = XML_MAPPER.readValue(secXmlFile, SecuritiesXmlModel.class);
                    saveXml(xmlModel);
                }
            }
        }
    }

    public void saveXml(SecuritiesXmlModel securitiesXmlModel) {
        securitiesRepository.saveAll(convert(securitiesXmlModel.getData().getRows()));
    }

    private List<SecuritiesEntity> convert(List<SecuritiesModel> modelList) {
        List<String> secIdList = modelList.stream().map(SecuritiesModel::getSecId).collect(Collectors.toList());
        List<String> existSecIds = securitiesRepository.findAllBySecIdIn(secIdList).stream()
                .map(SecuritiesEntity::getSecId).collect(Collectors.toList());
        return modelList.stream()
                .filter(model -> !existSecIds.contains(model.getSecId()))
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
