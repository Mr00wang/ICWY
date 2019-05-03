package Server.db;

import java.net.Socket;

public class UserInfo {
    private String uid;
    private String name;
    private String sign;
    private int age;
    private String sex;
    private String home;
    private String phone;
    private Socket socket;
    private String img;

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUid() {
        return uid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getSign() {
        return sign;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setHome(String home) {
        this.home = home;
    }
    public String getHome() {
        return home;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
