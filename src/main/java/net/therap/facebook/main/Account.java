package main.java.net.therap.facebook.main;


import main.java.net.therap.facebook.entities.Comment;
import main.java.net.therap.facebook.entities.Message;
import main.java.net.therap.facebook.entities.Post;
import main.java.net.therap.facebook.entities.UserInfo;
import main.java.net.therap.facebook.services.FriendListServiceImp;
import main.java.net.therap.facebook.services.MessageServiceImp;
import main.java.net.therap.facebook.services.PersonalInfoServiceImp;
import main.java.net.therap.facebook.services.PostServiceImp;
import main.java.net.therap.facebook.utils.PostComparator;
import main.java.net.therap.facebook.utils.UserInput;
import main.java.net.therap.facebook.utils.UserInterface;

import java.util.*;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */
public class Account {
    private int userId;
    private String email;
    private UserInfo userInfo;
    private List<UserInfo> friends;
    private List<Post> allPosts;

    private PersonalInfoServiceImp personalInfoServiceImp;
    private MessageServiceImp messageServiceImp;
    private PostServiceImp postServiceImp;
    private FriendListServiceImp friendListServiceImp;

    public Account(int userId, String email) {
        this.userId = userId;
        this.email = email;

        userInfo = null;
        friends = new ArrayList<UserInfo>();
        allPosts = new ArrayList<Post>();

        personalInfoServiceImp = new PersonalInfoServiceImp();
        postServiceImp = new PostServiceImp();
        friendListServiceImp = new FriendListServiceImp();
        messageServiceImp = new MessageServiceImp();
    }

    public List<UserInfo> getFriends() {
        return friends;
    }

    public void setFriends(List<UserInfo> friends) {
        this.friends = friends;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private void loadUserInfo() {
        this.setUserInfo(personalInfoServiceImp.getPersonalDetails(this.getUserId()));
        if (this.getUserInfo() == null) {
            this.setUserInfo(UserInput.getInstance().getUserInfo(this.getUserId()));
            personalInfoServiceImp.insertPersonalDetails(this.getUserInfo());
        }
    }

    public void run() {

        int option = 0;
        int subOption = 0;

        loadUserInfo();
        String msg = "Welcome to Facebook ";
        msg += this.getUserInfo().getFirstName() + " " + this.getUserInfo().getLastName();

        do {
            UserInterface.showHeader(msg);
            UserInterface.showProfileMenu();
            option = UserInput.getInstance().inputInteger();
            switch (option) {
                case 1:
                    updatePost();
                    break;
                case 2:
                    UserInterface.showUserInfo(this.getUserInfo());
                    UserInterface.updatePersonalInfoMenu();
                    subOption = UserInput.getInstance().inputInteger();
                    if (subOption == 1) {
                        updateProfile();
                    }
                    break;
                case 3:
                    showTimeline();
                    break;
                case 4:
                    showFriendList();
                    break;
                case 5:
                    addFriend();
                    break;
                case 6:
                    messageList();
                    break;
                case 7:
                    viewPostDetails();
                    break;
                case 8:
                    break;
                default:
                    UserInterface.tryAgainMsg();
            }
            loadUserInfo();
        } while (option != 8);

    }

    private void viewPostDetails() {
        showTimeline();
        int postId = UserInput.getInstance().getId();
        if (postId - 1 >= 0 && postId <= allPosts.size()) {
            postId = allPosts.get(postId - 1).getPostId();
            Post currentPost = postServiceImp.getPostByPostId(postId);
            UserInterface.showPost(currentPost);
            UserInterface.showPostOptions();
            int option = UserInput.getInstance().inputInteger();

            if (option == 1) {
                addComment(postId);
            } else if (option == 2) {
                addLike(postId);
            }
        } else {
            UserInterface.tryAgainMsg();
        }
    }

    private void addLike(int postId) {

        if (postServiceImp.updateLike(postId, this.getUserId())) {

        } else {
            UserInterface.tryAgainMsg();
        }
    }

    private void addComment(int postId) {
        String content = UserInput.getInstance().getComment();
        Date time = Calendar.getInstance().getTime();
        Comment comment = new Comment(-1, postId, this.getUserInfo(), content, time);
        if (postServiceImp.addComment(comment)) {
            UserInterface.success();
        } else {
            UserInterface.tryAgainMsg();
        }
    }

    private void messageList() {
        int option;

        UserInterface.showMessageOptions();
        option = UserInput.getInstance().inputInteger();

        if (option == 1) {
            showInbox();
        } else if (option == 2) {
            showOutbox();
        } else if (option == 3) {
            composeMessage();
        } else {
            UserInterface.tryAgainMsg();
        }
    }

    private void composeMessage() {
        showFriendList();

        int receiverId = UserInput.getInstance().getId();
        if (receiverId - 1 >= 0 && receiverId <= friends.size()) {
            UserInfo receiver = personalInfoServiceImp.getPersonalDetails(friends.get(receiverId - 1).getUserId());
            String content = UserInput.getInstance().getMessage();
            Message message = new Message(-1, this.getUserInfo(), receiver, content);

            if (messageServiceImp.sendMessage(message)) {
                UserInterface.success();
            } else {
                UserInterface.tryAgainMsg();
            }
        } else {
            UserInterface.tryAgainMsg();
        }
    }

    private void showOutbox() {
        List<Message> outbox = messageServiceImp.getOutbox(this.getUserInfo());
        UserInterface.showOutbox(outbox);
    }

    private void showInbox() {
        List<Message> inbox = messageServiceImp.getInbox(this.getUserInfo());
        UserInterface.showInbox(inbox);
    }

    private void addFriend() {
        List<UserInfo> notFriends = friendListServiceImp.getNotYetFriendList(this.getUserInfo());
        UserInterface.showFriendlist(notFriends);
        int friendId = UserInput.getInstance().getId();
        if (friendId - 1 >= 0 && friendId <= notFriends.size()) {
            friendListServiceImp.makeFriend(this.getUserId(), notFriends.get(friendId - 1).getUserId());
        } else {
            UserInterface.tryAgainMsg();
        }
    }

    private void showFriendList() {
        updateFriendList();
        UserInterface.showFriendlist(this.getFriends());
    }

    private void updateFriendList() {
        friends = friendListServiceImp.getFriendList(this.getUserInfo());
    }

    private void showTimeline() {
        updateFriendList();
        List<Post> tmp = postServiceImp.getAllPosts(friends);
        allPosts = postServiceImp.getPostByUser(this.getUserInfo());
        for (Post post : tmp) {
            allPosts.add(post);
        }
        Collections.sort(allPosts, new PostComparator());
        UserInterface.showPosts(allPosts);
    }

    private void showComments(int postId) {
        UserInterface.showCommentOptions();

    }

    private void updateProfile() {
        UserInfo newUserInfo = UserInput.getInstance().getUserInfo(this.getUserId());
        if (personalInfoServiceImp.updatePersonalDetails(newUserInfo)) {
            UserInterface.success();
            this.setUserInfo(newUserInfo);
        } else {
            UserInterface.tryAgainMsg();
        }
    }

    private void updatePost() {
        String content = UserInput.getInstance().getStatus();
        Post post = new Post(this.getUserInfo(), content, Calendar.getInstance().getTime());
        postServiceImp.addPost(post);
    }

}
