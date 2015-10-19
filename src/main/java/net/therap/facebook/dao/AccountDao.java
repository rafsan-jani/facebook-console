package main.java.net.therap.facebook.dao;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public interface AccountDao {

    int getUserId(String email, String password);

    boolean isUsedEmail(String email);

    boolean insertIntoAccount(String email, String password);
}
