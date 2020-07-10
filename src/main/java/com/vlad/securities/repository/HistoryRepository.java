package com.vlad.securities.repository;

import com.vlad.securities.entity.HistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "history", path = "history")
public interface HistoryRepository extends CrudRepository<HistoryEntity, Integer> {
}
