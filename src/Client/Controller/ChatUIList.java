package Client.Controller;

import java.util.HashMap;

import Client.View.DialogWindow;



public class ChatUIList {
    private static HashMap<String, DialogWindow> map = new HashMap<String, DialogWindow>();

    //向map里面“注册”
    public static void addDialogWindow(ChatUIEntity chatUIEntity) {
        map.put(chatUIEntity.getId(), chatUIEntity.getDialogWindow());
    }

    //关闭窗口后要删除
    public static void deletDialogWindow(String chatUIName) {

        //删除之前查看是否有这个窗口, 防止出错
        if(map.get(chatUIName) != null) {
            map.remove(chatUIName);
        }

    }

    //通过昵称返回窗口
    public static DialogWindow getDialogWindow(String id) {
        return map.get(id);
    }
}

