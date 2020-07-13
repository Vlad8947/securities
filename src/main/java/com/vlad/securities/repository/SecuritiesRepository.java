package com.vlad.securities.repository;

import com.vlad.securities.entity.SecuritiesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "securities")
public interface SecuritiesRepository extends CrudRepository<SecuritiesEntity, Integer> {

    List<SecuritiesEntity> findAllBySecIdIn(Iterable<String> secIds);

}
