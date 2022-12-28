package com.kourchenko.graphql.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.kourchenko.mongodb.dao.AccountPage;
import com.kourchenko.mongodb.model.Account;
import graphql.schema.DataFetchingEnvironment;
import com.kourchenko.graphql.service.AccountService;

@Component
public class AccountQuery implements GraphQLQueryResolver {

    @Autowired
    private AccountService accountService;

    public Account getAccount(String accountId, DataFetchingEnvironment env) {
        return accountService.getAccount(accountId, env);
    }

    public AccountPage getAccounts(Integer limit, Integer offset, DataFetchingEnvironment env) {
        return accountService.getAccounts(limit, offset, env);
    }
}
