package main.java.net.therap.facebook.main;

import main.java.net.therap.facebook.services.AccountServiceImp;
import main.java.net.therap.facebook.utils.EmailValidator;
import main.java.net.therap.facebook.utils.UserInput;
import main.java.net.therap.facebook.utils.UserInterface;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public class Facebook {
    private Account account;
    private AccountServiceImp accountServiceImp;

    public Facebook() {
        accountServiceImp = new AccountServiceImp();
    }

    public void run() {
        UserInterface.showHeader("Facebook Simulation");
        int option = 0;
        do {
            UserInterface.showStartMenu();
            option = UserInput.getInstance().inputInteger();
            switch (option) {
                case 1:
                    this.login();
                    break;
                case 2:
                    this.signUp();
                    break;
                case 3:
                    System.out.println("\t\t\t***Thank You***\t\t\t");
                    break;
                default:
                    UserInterface.tryAgainMsg();
            }

        } while (option != 3);
    }

    private void signUp() {
        String email;
        String password;
        String confirmPassword;
        int userId;
        int tryCount = 0;
        email = UserInput.getInstance().getEmail();
        if (EmailValidator.getInstance().validate(email)) {
            if (accountServiceImp.isUsedEmail(email)) {
                System.out.println("This email is already used!!!");

            } else {
                while (tryCount < 3) {
                    password = UserInput.getInstance().getPassword(0);
                    confirmPassword = UserInput.getInstance().getPassword(1);
                    if (password.equals(confirmPassword)) {
                        tryCount = 3;
                        if (accountServiceImp.addNewAccount(email, password)) {
                            userId = accountServiceImp.login(email, password);
                            account = new Account(userId, email);
                            account.run();
                        } else {
                            System.out.println("Error!!! Try again...");
                        }
                    } else {
                        System.out.println("Password not matched!!!");
                    }
                    tryCount++;
                }
            }

        } else {
            System.out.println("Invalid email format!!!");
        }

    }

    private void login() {
        String email;
        String password;
        int userId;
        email = UserInput.getInstance().getEmail();
        password = UserInput.getInstance().getPassword(0);
        userId = accountServiceImp.login(email, password);
        if (userId != -1) {
            account = new Account(userId, email);
            account.run();
        } else {
            System.out.println("Invalid email or password.");
        }
    }
}
