package Server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class UserOnlineList {
    private UserOnlineList() {
    }
    private static UserOnlineList userOnlineList = new UserOnlineList();

    public static UserOnlineList getUserOnlineList() {
        return userOnlineList;
    }

    // 我们把所有的在线账户 全部登记在集合中
    /*
     * String 是用户的编号
     */
    private HashMap<String, UserInfo> hashMap = new HashMap<String, UserInfo>();

    // 注册在线用户
    public void regOnline(String uid, Socket socket, String email, String phoneNumber) {

        // 判断其他的客户端是否登录一样用户名 如果一样 就强行弄下去
        UserInfo userInfo = hashMap.get(uid);
        if (userInfo != null) {
            try {
                try {
                    userInfo.getSocket().getOutputStream().write(4);
                } catch (Exception e) {
                }
                userInfo.getSocket().close();
            } catch (Exception e) {
            }
        }

        userInfo = new UserInfo();
        userInfo.setUid(uid);
        userInfo.setEmail(email);
        userInfo.setPhone(phoneNumber);
        userInfo.setSocket(socket);
        hashMap.put(uid, userInfo);// 登记在线
    }

    /*
     * 更新客户端的UDP信息
     *
     * @param uid
     *            用户编号
     * @param ip
     *            udp IP地址
     * @param port
     *            udp端口
     * @throws NullPointerException
     *             空指针异常
     */
    public void updateOnlineUDP(String uid, String ip, int port) throws NullPointerException {
        UserInfo userInfo = hashMap.get(uid);
        userInfo.setUdpip(ip);
        userInfo.setUdpport(port);
    }

    // 判断用户是否在线 如果是true 在线 false 不在线
    public boolean isUserOnline(String uid) {
        return hashMap.containsKey(uid);
    }

    /*
     * 获得在线用户信息
     *
     * @param uid
     * @return
     */
    public UserInfo getOnlineUserInfo(String uid) {
        return hashMap.get(uid);
    }

    /*
     * 下线了
     *
     * @param uid
     */
    public void logout(String uid) {
        hashMap.remove(uid);
    }

    /*
     * 获得所有的在线信息
     *
     * @return
     */
    public Set<String> getUserInfos() {
        return hashMap.keySet();
    }
}
