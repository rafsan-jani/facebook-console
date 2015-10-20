package main.java.net.therap.facebook.services;


import main.java.net.therap.facebook.dao.PersonalInfoDaoImp;
import main.java.net.therap.facebook.entities.UserInfo;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class PersonalInfoServiceImp implements PersonalInfoService {
    PersonalInfoDaoImp personalInfoDaoImp;

    public PersonalInfoServiceImp() {
        personalInfoDaoImp = new PersonalInfoDaoImp();
    }

    @Override
    public UserInfo getPersonalDetails(int userId) {
        return personalInfoDaoImp.getPersonalInfo(userId);
    }

    @Override
    public boolean updatePersonalDetails(UserInfo userInfo) {
        return personalInfoDaoImp.updatePersonalInfo(userInfo);
    }

    @Override
    public boolean insertPersonalDetails(UserInfo userInfo) {
        return personalInfoDaoImp.insertPersonalInfo(userInfo);
    }
}
