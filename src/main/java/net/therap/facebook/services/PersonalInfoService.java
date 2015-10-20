package main.java.net.therap.facebook.services;

import main.java.net.therap.facebook.entities.UserInfo;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public interface PersonalInfoService {
    UserInfo getPersonalDetails(int userId);

    boolean updatePersonalDetails(UserInfo userInfo);

    boolean insertPersonalDetails(UserInfo userInfo);
}
