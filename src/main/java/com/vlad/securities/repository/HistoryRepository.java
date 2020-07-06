package com.vlad.securities.repository;

import com.vlad.securities.entity.History;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "history", path = "history")
public interface HistoryRepository extends PagingAndSortingRepository<History, Integer> {
}
