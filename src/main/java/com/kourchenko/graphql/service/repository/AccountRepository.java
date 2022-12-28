package com.kourchenko.graphql.service.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kourchenko.mongodb.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findAll();
    Page<Account> findAll(Pageable pageable);
}
