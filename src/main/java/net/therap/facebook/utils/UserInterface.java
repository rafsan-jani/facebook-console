package main.java.net.therap.facebook.utils;


import main.java.net.therap.facebook.entities.Comment;
import main.java.net.therap.facebook.entities.Message;
import main.java.net.therap.facebook.entities.Post;
import main.java.net.therap.facebook.entities.UserInfo;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class UserInterface {

    public static void showHeader(String msg) {
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("\t\t\t\t" + msg + "\t\t\t");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
    }

    public static void showStartMenu() {
        System.out.println("Please select from the following options...");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Quit");
    }

    public static void showProfileMenu() {
        System.out.println("Please select from the following options...");
        System.out.println("1. Update Status");
        System.out.println("2. Personal Information");
        System.out.println("3. Timeline");
        System.out.println("4. Friend List");
        System.out.println("5. Add Friend");
        System.out.println("6. Message");
        System.out.println("7. View Post Details");
        System.out.println("8. Logout");
    }

    public static void updatePersonalInfoMenu() {
        System.out.println("Select from the following options...");
        System.out.println("1. Update Information");
        System.out.println("[0|2-8]. Back");
    }

    public static void tryAgainMsg() {
        System.out.println("Please try again...");
    }

    public static void showUserInfo(UserInfo userInfo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Name: " + userInfo.getFirstName() + " " + userInfo.getLastName());
        System.out.println("Sex: " + userInfo.getSex());
        System.out.println("Date of birth (dd/mm/yyyy): " + simpleDateFormat.format(userInfo.getDateOfBirth()));
        System.out.println("Age: " + userInfo.getAge());
        System.out.println();
    }

    public static void success() {
        System.out.println("Success");
    }

    public static void showPost(Post post) {
        showName(post.getUserInfo());
        System.out.println(" posted:");
        showContent(post.getContent());
        showComments(post.getComments());
        showLikes(post.getLikes());

    }

    private static void showLikes(List<UserInfo> likes) {
        System.out.println("Likes: (" + likes.size() + ")");
        for (UserInfo userInfo : likes) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.print("-->");
            showName(userInfo);
        }
        System.out.println();
    }

    private static void showComments(List<Comment> comments) {
        System.out.println("Comments: (" + comments.size() + ")");
        for (Comment comment : comments) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.print("\t@");
            showName(comment.getUserInfo());
            System.out.print("\t-->");
            showContent(comment.getContent());
            System.out.println();
        }
        System.out.println();
    }

    public static void showFriendlist(List<UserInfo> friends) {
        if (friends == null || friends.size() == 0) {
            emptyMsg("This list ");
        } else {
            int id = 1;
            for (UserInfo userInfo : friends) {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.println("User id: " + id);
                showUserInfo(userInfo);
                id++;
            }
        }
    }

    public static void showMessageOptions() {
        System.out.println("Please select from the following options...");
        System.out.println("1. Inbox");
        System.out.println("2. Outbox");
        System.out.println("3. Compose");
    }

    public static void showContent(String messageContent) {
        System.out.print("Content: ");
        System.out.println(messageContent);
    }

    public static void showMessge(List<Message> messages, String msg) {
        if (messages == null || messages.size() == 0) {
            emptyMsg(msg);

        } else {
            for (Message message : messages) {
                System.out.println("\n-------------------------------------------------------------------");
                if ("Outbox".equals(msg)) {
                    System.out.print("Receiver: ");
                    showName(message.getReceiver());
                } else if ("Inbox".equals(msg)) {
                    System.out.print("Sender: ");
                    showName(message.getSender());
                }
                showContent(message.getContent());
            }
        }
    }

    public static void showOutbox(List<Message> outbox) {
        showMessge(outbox, "Outbox");
    }

    public static void showInbox(List<Message> inbox) {
        showMessge(inbox, "Inbox");
    }

    public static void emptyMsg(String msg) {
        System.out.println(msg + " is empty...");
    }

    public static void showName(UserInfo userInfo) {
        System.out.println(userInfo.getFirstName() + " " + userInfo.getLastName());
    }

    public static void showPostOptions() {
        System.out.println("Please select from the following options...");
        System.out.println("1. Comments");
        System.out.println("2. Likes");
        System.out.println("0|[3-9]. Back");
    }

    public static void showCommentOptions() {
        System.out.println("Please select from the following options...");
        System.out.println("1. Make Comment");
        System.out.println("[0|2-9] Back");
    }

    public static void showPosts(List<Post> posts) {
        int id = 1;
        for (Post post : posts) {
            System.out.println("\n-------------------------------------------------------------------");
            System.out.println("Post Id: " + id);
            showName(post.getUserInfo());
            showContent(post.getContent());
            id++;
        }
    }
}
