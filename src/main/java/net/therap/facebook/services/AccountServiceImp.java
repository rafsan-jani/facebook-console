package main.java.net.therap.facebook.services;


import main.java.net.therap.facebook.dao.AccountDaoImp;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class AccountServiceImp implements AccountService {
    private AccountDaoImp accountDaoImp;

    public AccountServiceImp() {
        accountDaoImp = new AccountDaoImp();
    }

    @Override
    public boolean isUsedEmail(String email) {
        return accountDaoImp.isUsedEmail(email);
    }

    @Override
    public boolean addNewAccount(String email, String password) {
        return accountDaoImp.insertIntoAccount(email, password);
    }

    @Override
    public int login(String email, String password) {
        return accountDaoImp.getUserId(email, password);
    }
}
