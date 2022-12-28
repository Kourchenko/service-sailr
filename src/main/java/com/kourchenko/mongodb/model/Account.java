package com.kourchenko.mongodb.model;

import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import com.kourchenko.mongodb.dao.AccountHttpStatus;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;

@Document(collection = "account")
public class Account {

    @Id
    private String id;

    private String name;
    private String url;

    @Transient
    private AccountHttpStatus accountHttpStatus;

    public Account() {}

    public Account(String name, String url, AccountHttpStatus accountHttpStatus) {
        this.name = name;
        this.url = url;
        this.accountHttpStatus = accountHttpStatus;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return this.url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public AccountHttpStatus getAccountHttpStatus() {
        return this.accountHttpStatus;
    }

    public void setAccountHttpStatus(AccountHttpStatus accountHttpStatus) {
        this.accountHttpStatus = accountHttpStatus;
    }

    public void override(Account account, DataFetchingEnvironment env) {
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();
        Map<String, Map<String, Object>> map = selectionSet.getArguments();

        // Override 'name' if argument includes 'name' and 'name' is not null.
        if (map.containsKey("name") && account.getName() != null) {
            this.setName(account.getName());
        }

        // Override 'url' if argument includes 'url' and 'url' is not null.
        if (map.containsKey("url") && account.getURL() != null) {
            this.setURL(account.getURL());
        }
    }
}
