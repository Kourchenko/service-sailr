package com.kourchenko.graphql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kourchenko.mongodb.dao.AccountHttpStatus;
import com.kourchenko.mongodb.dao.AccountPage;
import com.kourchenko.mongodb.model.Account;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import com.kourchenko.graphql.service.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HttpStatusService httpStatusService;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account create(Account account, DataFetchingEnvironment env) {

        AccountHttpStatus accountHttpStatus = this.getAccountHttpStatus(account, env);
        account.setAccountHttpStatus(accountHttpStatus);

        return accountRepository.save(account);
    }

    public Account getAccount(String accountId, DataFetchingEnvironment env) {
        Optional<Account> object = accountRepository.findById(accountId);
        if (!object.isPresent()) {
            return null;
        }

        Account account = object.get();
        AccountHttpStatus accountHttpStatus = this.getAccountHttpStatus(account, env);
        account.setAccountHttpStatus(accountHttpStatus);

        return account;
    }

    public AccountPage getAccounts(Integer limit, Integer offset, DataFetchingEnvironment env) {
        AccountPage accountPage = new AccountPage();

        List<Account> accountList = new ArrayList<>();

        if (limit != null || offset != null) {
            limit = limit != null ? limit : 5;
            offset = offset != null ? offset : 0;

            Pageable pageable = PageRequest.of(offset, limit);
            Page<Account> page = accountRepository.findAll(pageable);

            accountList = page.getContent();

            accountPage = new AccountPage();
            accountPage.setAccounts(accountList);

            accountPage.setCount(page.getNumberOfElements());
            accountPage.setTotalCount(Math.toIntExact(page.getTotalElements()));

            accountPage.setPage(page.getNumber());
            accountPage.setTotalPages(page.getTotalPages());

        } else {
            Long countLong = accountRepository.count();
            Integer countInt = Math.toIntExact(countLong);
            accountList = accountRepository.findAll();
            accountPage.setAccounts(accountList);

            accountPage.setCount(accountList.size());
            accountPage.setTotalCount(countInt);

            accountPage.setPage(0);
            accountPage.setTotalPages(1);
        }

        if (requestHasAccountHttpStatus(env)) {
            for (Account account : accountList) {
                AccountHttpStatus accountHttpStatus = this.getAccountHttpStatus(account, env);
                account.setAccountHttpStatus(accountHttpStatus);
            }
        }

        return accountPage;
    }

    public AccountPage deleteAccount(String accountId, Integer limit, Integer offset, DataFetchingEnvironment env) {
        Account account = accountRepository.findById(accountId).get();
        if (account != null) {
            // Delete Account
            accountRepository.delete(account);
        }

        return getAccounts(limit, offset, env);
    }

    public AccountHttpStatus getAccountHttpStatus(Account account, DataFetchingEnvironment env) {
        if (!this.requestHasAccountHttpStatus(env)) {
            return new AccountHttpStatus(false, 0, null);
        }

        AccountHttpStatus accountHttpStatus = httpStatusService.getAccountHttpStatus(account.getURL());
        return accountHttpStatus;
    }

    public boolean requestHasAccountHttpStatus(DataFetchingEnvironment env) {
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();
        Map<String, Map<String, Object>> map = selectionSet.getArguments();

        return map.containsKey("accounts/accountHttpStatus") || map.containsKey("accountHttpStatus");

    }
}
