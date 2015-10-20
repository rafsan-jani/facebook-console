package main.java.net.therap.facebook.dao;


import main.java.net.therap.facebook.entities.UserInfo;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface PersonalInfoDao {


    UserInfo getPersonalInfo(int userId);

    boolean updatePersonalInfo(UserInfo userInfo);

    boolean insertPersonalInfo(UserInfo userInfo);
}
