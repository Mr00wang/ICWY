package Client.Model;

import java.io.Serializable;


public class Person implements Serializable{

    private static final long serialVersionUID = 1L;
    public String user_qq;
    public String user_nickname;
    public int user_photo; //用户头像

    public Person() {
        super();
    }
    public Person(String user_qq, String user_nickname) {
        super();
        this.user_qq = user_qq;
        this.user_nickname = user_nickname;
    }

    public String getUserQQ() {
        return user_qq;
    }

    public String getUserNickname() {
        return user_nickname;
    }


    public int getUserPhotoID() {
        return user_photo;
    }


}
