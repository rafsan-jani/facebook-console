package main.java.net.therap.facebook.entities;

import java.util.Calendar;
import java.util.Date;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class UserInfo {
    private int userId;
    private String firstName;
    private String lastName;
    private String sex;
    private Date dateOfBirth;
    private int age;


    public UserInfo(int userId) {
        this.userId = userId;
        firstName = null;
        lastName = null;
        sex = null;
        dateOfBirth = null;
        age = -1;
    }

    public UserInfo(int userId, String firstName, String lastName, String sex, Date dateOfBirth, int age) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public UserInfo(int userId, String firstName, String lastName, String sex, Date dateOfBirth) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge(dateOfBirth);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int calculateAge(Date dateOfBirth) {
        Calendar birthDay = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        birthDay.setTime(dateOfBirth);

        int age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);

        if (birthDay.get(Calendar.MONTH) > today.get(Calendar.MONTH)) {
            age--;
        } else if (birthDay.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                && birthDay.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
