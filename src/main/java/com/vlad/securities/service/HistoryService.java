package com.vlad.securities.service;

import com.vlad.securities.StaticResource;
import com.vlad.securities.entity.HistoryEntity;
import com.vlad.securities.entity.SecuritiesEntity;
import com.vlad.securities.model.DataModel;
import com.vlad.securities.model.HistoryModel;
import com.vlad.securities.model.HistoryXmlModel;
import com.vlad.securities.model.ShortModel;
import com.vlad.securities.repository.HistoryRepository;
import com.vlad.securities.repository.SecuritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vlad.securities.StaticResource.XML_MAPPER;

@Service
public class HistoryService {

    private static final String localPath = "./history";
    private static final String fileStartsWith = "history_";
    private static final String fileEndsWith = ".xml";
    private static final String dataId = "history";

    private HistoryRepository historyRepository;
    private SecuritiesRepository securitiesRepository;


    @Autowired
    public HistoryService(HistoryRepository historyRepository,
                          SecuritiesRepository securitiesRepository) {
        this.historyRepository = historyRepository;
        this.securitiesRepository = securitiesRepository;
    }


    public List<ShortModel> findShort(String emitentTitle, Date beginDate, Date endDate, String sortCol) {

        List<HistoryEntity> historyEntities;
        if (ShortModel.securitiesCols.contains(sortCol)) {
            historyEntities = historyRepository.shortInfoOrderBySecurities(emitentTitle, beginDate, endDate, Sort.by(sortCol));
        } else if (ShortModel.historyCols.contains(sortCol)) {
            historyEntities = historyRepository.shortInfoOrderByHistory(emitentTitle, beginDate, endDate, Sort.by(sortCol));
        } else {
            throw new ValidationException("Sort column doesn't exist");
        }
        return convertToShort(historyEntities);
    }

    public void saveFromLocalFiles() throws IOException {

        File dirFile = new File(localPath);
        if (!dirFile.isDirectory()) {
            throw new FileNotFoundException("History local path is not directory.");
        }
        for (File historyXmlFile: dirFile.listFiles()) {
            if (historyXmlFile.isFile()) {
                String historyXmlFileName = historyXmlFile.getName();
                if (historyXmlFileName.startsWith(fileStartsWith) && historyXmlFileName.endsWith(fileEndsWith)) {
                    HistoryXmlModel xmlModel = XML_MAPPER.readValue(
                            historyXmlFile, HistoryXmlModel.class);
                    saveXml(xmlModel);
                }
            }
        }
    }

    public void saveXml(HistoryXmlModel xmlModel) {
        DataModel<HistoryModel> historyDataModel = null;
        for(DataModel<HistoryModel> tmpData: xmlModel) {
            if (tmpData.getId().equals(dataId)) {
                historyDataModel = tmpData;
                break;
            }
        }
        historyRepository.saveAll(convert(historyDataModel.getRows()));
    }

    private List<ShortModel> convertToShort(List<HistoryEntity> historyEntities) {
        return historyEntities.stream().map(this::convertToShort).collect(Collectors.toList());
    }

    private ShortModel convertToShort(HistoryEntity history) {
        SecuritiesEntity security = history.getSecurity();
        DateFormat dateFormat = StaticResource.DATE_FORMAT;
        return new ShortModel(
                security.getSecId(), security.getRegNumber(),
                security.getName(), security.getEmitentTitle(),
                dateFormat.format(history.getTradeDate()), history.getNumTrades(),
                history.getOpen(), history.getClose());
    }

    private List<HistoryEntity> convert(List<HistoryModel> modelList) {
        Set<String> secIds = modelList.stream().map(HistoryModel::getSecId).collect(Collectors.toSet());
        Map<String, SecuritiesEntity> securitiesMap =
                securitiesRepository.findAllBySecIdIn(secIds).stream()
                .collect(Collectors.toMap(SecuritiesEntity::getSecId, securitiesEntity -> securitiesEntity));

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
