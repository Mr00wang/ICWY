package Client.Model;

import java.io.Serializable;

import java.util.ArrayList;

/**

 */
public class User extends Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private String user_pwd;
    private int friends_num = 0;
    private ArrayList<String> friendslist;

    public User() {
        super();
    }

    public User(String userqq, String password) {
        super();
        this.user_qq = userqq;
        this.user_pwd = password;
    }

    public String setUserQQ(String userqq) {
        return this.user_qq = userqq;
    }

    public String getUserpwd() {
        return user_pwd;
    }

    public String setUserpwd(String password) {
        return this.user_pwd = password;
    }


    public String setUserNickname(String nickname) {
        return this.user_nickname = nickname;
    }

    public int setUserPhotoID(int user_photo) {
        return this.user_photo = user_photo;
    }

    public int getFriendsNum() {
        return friends_num;
    }

    public void setFriendsNum(int friends_num) {
        this.friends_num = friends_num;
    }

    public void setFriendsList(ArrayList<String> friendList) {
        this.friendslist = new ArrayList<String>(friendList);
        this.friends_num = friendList.size();
    }

    public ArrayList<String> getFriend() {
        return friendslist;
    }

}
