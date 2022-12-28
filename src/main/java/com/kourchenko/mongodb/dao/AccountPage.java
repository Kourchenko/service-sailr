package com.kourchenko.mongodb.dao;

import java.util.List;
import com.kourchenko.mongodb.model.Account;

public class AccountPage {

    public List<Account> accounts;
    public int count;
    public int totalCount;
    public int page;
    public int totalPages;

    public AccountPage () {}

    public AccountPage (List<Account> accounts, int page, int totalPages) {}


    public List<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
