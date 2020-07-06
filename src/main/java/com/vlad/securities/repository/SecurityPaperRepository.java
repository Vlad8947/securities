package com.vlad.securities.repository;

import com.vlad.securities.entity.SecurityPaper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityPaperRepository extends CrudRepository<SecurityPaper, Integer> {
}
