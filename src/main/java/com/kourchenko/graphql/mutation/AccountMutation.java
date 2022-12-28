package com.kourchenko.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.kourchenko.graphql.service.AccountService;
import com.kourchenko.mongodb.dao.AccountPage;
import com.kourchenko.mongodb.model.Account;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMutation implements GraphQLMutationResolver {

    @Autowired
    private AccountService accountService;

    public Account createAccount(Account account, DataFetchingEnvironment env) {
        account = accountService.create(account, env);
        return account;
    }

    public Account updateAccount(Account account, DataFetchingEnvironment env) {
        if (account == null || account.getId() == null) {
            return account;
        }

        Account existingAccount = accountService.getAccount(account.getId(), env);
        existingAccount.override(account, env);

        accountService.save(existingAccount);

        return existingAccount;
    }

    public AccountPage deleteAccount(String accountId, Integer limit, Integer offset, DataFetchingEnvironment env) {
        return accountService.deleteAccount(accountId, limit, offset, env);
    }
}
