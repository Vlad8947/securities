package com.vlad.securities.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vlad.securities.entity.HistoryEntity;
import com.vlad.securities.entity.SecuritiesEntity;
import com.vlad.securities.model.DataModel;
import com.vlad.securities.model.HistoryModel;
import com.vlad.securities.repository.HistoryRepository;
import com.vlad.securities.repository.SecuritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    private static final String localPath = "./history";
    private static final String fileStartsWith = "history_";
    private static final String fileEndsWith = ".xml";
    private static final String dataId = "history";
    private static final ObjectMapper objectMapper = new XmlMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private SecuritiesRepository securitiesRepository;


    public void saveFromLocalFiles() throws IOException {

        File dirFile = new File(localPath);
        if (!dirFile.isDirectory()) {
            throw new FileNotFoundException("History local path is not directory.");
        }
        for (File historyXmlFile: dirFile.listFiles()) {
            if (historyXmlFile.isFile()) {
                String historyXmlFileName = historyXmlFile.getName();
                if (historyXmlFileName.startsWith(fileStartsWith) && historyXmlFileName.endsWith(fileEndsWith)) {
                    List<DataModel<HistoryModel>> dataList = objectMapper.readValue(
                            historyXmlFile, new TypeReference<List<DataModel<HistoryModel>>>() {});
//                    HistoryXmlModel xmlModel = objectMapper.readValue(
//                            historyXmlFile, new TypeReference<List<DataModel<String>>>() {});
                    saveXml(dataList);
                }
            }
        }
    }

    public void saveXml(List<DataModel<HistoryModel>> dataModelList) {
        DataModel<HistoryModel> dataModel = null;
        for(DataModel<HistoryModel> tmpData: dataModelList) {
            if (tmpData.getId().equals(dataId)) {
                dataModel = tmpData;
                break;
            }
        }
//        List<HistoryModel> historyList = dataModel.getRows().stream()
//                .map(str -> {
//                    try {
//                        return objectMapper.readValue(str, HistoryModel.class);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());

        historyRepository.saveAll(convert(dataModel.getRows()));
    }

    private List<HistoryEntity> convert(List<HistoryModel> modelList) {
        Set<String> secIds = modelList.stream().map(HistoryModel::getSecId).collect(Collectors.toSet());
        Map<String, SecuritiesEntity> securitiesMap = new HashMap<>();
        for (String secId: secIds) {
            Optional<SecuritiesEntity> optional = securitiesRepository.findBySecId(secId);
            optional.ifPresent(sec -> securitiesMap.put(secId, optional.get()));
        }

        return modelList.stream()
                .filter(hist -> securitiesMap.containsKey(hist.getSecId()))
                .map(his -> this.convert(his, securitiesMap.get(his.getSecId())))
                .collect(Collectors.toList());
    }

    private HistoryEntity convert(HistoryModel model, SecuritiesEntity securitiesEntity) {
        
        return new HistoryEntity(
                0, securitiesEntity, model.getBoardId(), model.getTradeDate(),
                model.getNumTrades(), model.getValue(), model.getOpen(),
                model.getLow(), model.getHigh(), model.getLegalClosePrice(),
                model.getWaPrice(), model.getClose(), model.getVolume(),
                model.getMarketPrice2(), model.getMarketPrice3(), model.getAdmittedQuote(),
                model.getMp2ValTrd(), model.getMarketPrice3TradesValue(), model.getAdmittedValue(),
                model.getWaVal()
        );
    }

}
