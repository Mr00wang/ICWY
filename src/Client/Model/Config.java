package Client.Model;

import java.net.DatagramSocket;
import java.util.Hashtable;
import java.util.Vector;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Config {

    // 服务器地址
    public static final String IP = "192.168.43.16";
    // 登录端口
    public static final int LOGIN_PORT = 4009;
    // 用户名和密码寄存
    public static String id="666311" ;
    public static String password;

    //public static HaoyouListJPanel haoyouListJPanel;

    // 好友信息列表 JSON
    public static String haoyou_json_data = "";

    // 好友信息列表
    public static String haoyou_liebiao_data = "";

    /**
     * 取出好友列表值
     *
     * @param haoyou_json_data
     */
    public static void jiexi_haoyou_json_data(String haoyou_json_data) {
        Config.haoyou_json_data = haoyou_json_data;
        JSONArray json = JSONArray.fromObject(haoyou_json_data);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < json.size(); i++) {
            JSONObject jsonobj = (JSONObject) json.get(i);
            stringBuffer.append(jsonobj.getString("uid"));
            stringBuffer.append(",");
        }
        haoyou_liebiao_data = stringBuffer.toString();

    }

    // 个人资料
    public static String geren_json_data = "";

    // 好友在线
    public static String haoyou_online = "";

    // UDP发送和接收 以及心跳端
    public static DatagramSocket datagramSocket_client = null;

	/*
	 * // 聊天窗口登记
	public  static Hashtable<String, LiaotianFrame> liaotianTable = new Hashtable<String, LiaotianFrame>();

	// 显示聊天窗口
	public static void showLiaotianFrame(String uid, String netName, String info,
			String img,Vector<Msg> msgs) {

		if (liaotianTable.get(uid) == null) {
			LiaotianFrame liaotian = new LiaotianFrame(
					uid, netName, img, info,msgs);
			liaotianTable.put(uid, liaotian);
		} else {
			liaotianTable.get(uid).setAlwaysOnTop(true);
			liaotianTable.get(uid).setVisible(true);
		}

	}

	public static void closeLiaotianFrame(String uid) {

		liaotianTable.remove(uid);
	}
	*/

    //好友列表对象
    //public static Hashtable<String, FaceJPanel> list = new Hashtable();
}
