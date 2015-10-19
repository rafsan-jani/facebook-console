package main.java.net.therap.facebook.services;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface AccountService {
    boolean isUsedEmail(String email);

    boolean addNewAccount(String email, String password);

    int login(String email, String password);
}
