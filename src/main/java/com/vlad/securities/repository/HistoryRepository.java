package com.vlad.securities.repository;

import com.vlad.securities.entity.HistoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "history", path = "history")
public interface HistoryRepository extends CrudRepository<HistoryEntity, Integer> {

    String startSqlShortOrderHistory = "SELECT h FROM HistoryEntity h, SecuritiesEntity s ";
    String startSqlShortOrderSecurities = "SELECT h FROM SecuritiesEntity s, HistoryEntity h ";
    String endSqlShort = " WHERE h.security.id = s.id " +
            "AND (:emitentTitle is null OR s.emitentTitle = :emitentTitle)" +
            "AND ( (cast(:beginDate as timestamp) is null OR h.tradeDate >= :beginDate) AND " +
                    "(cast(:endDate as timestamp) is null OR h.tradeDate <= :endDate) ) ";


    @Query(startSqlShortOrderHistory + endSqlShort)
    public List<HistoryEntity> shortInfoOrderByHistory(String emitentTitle,
                                                       @Temporal(TemporalType.DATE) Date beginDate,
                                                       @Temporal(TemporalType.DATE) Date endDate,
                                                       Sort sort);

    @Query(startSqlShortOrderSecurities + endSqlShort)
    public List<HistoryEntity> shortInfoOrderBySecurities(String emitentTitle,
                                         @Temporal(TemporalType.DATE)  Date beginDate,
                                         @Temporal(TemporalType.DATE) Date endDate,
                                         Sort sort);

}
