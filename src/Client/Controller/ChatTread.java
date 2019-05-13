package Client.Controller;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.sound.midi.SysexMessage;
import javax.swing.JOptionPane;

import Client.Model.SystemTime;
import Client.Model.User;
import Client.View.ChatWindow;
import Client.View.DialogWindow;

public class ChatTread extends Thread{
    private Client client;
    private boolean isOnline = true;
    private User user; //如果同意好友请求， 则刷新好友列表
    private String userQQ; //如果创建新的聊天窗口
    private ChatWindow chatwindow;

    public ChatTread(Client client, User user, ChatWindow chatwindow) {
        this.client = client;
        this.user = user;
        this.chatwindow = chatwindow;
        this.userQQ = user.getUserQQ();
    }

    public boolean isOnline() {
        return isOnline;
    }

    @Override
    public void run() {
        if(!isOnline) {
            JOptionPane.showMessageDialog(null,  "unbelievable ！！！");
            return;
        }
        while(isOnline) {

            CommandTranser cmd = client.getData();
            //与服务器端相同处理接收到的消息(命令)
            //这里处理来自服务器的消息(命令)
            if(cmd != null) {

                execute(cmd);
            }
        }
    }

    //处理消息(命令)
    @SuppressWarnings("deprecation")
    private void execute(CommandTranser cmd) {
        //登录、注册消息未在此处处理
        System.out.println(cmd.getCmd());

        //聊天消息请求
        if("message".equals(cmd.getCmd())) {
            if(cmd.isFlag() == false) {
                JOptionPane.showMessageDialog(null, cmd.getResult());
                return;
            }
            //查询是否有与该好友的窗口该窗口
            String friend_id = cmd.getSender();
            DialogWindow dialogWindow = ChatUIList.getDialogWindow(friend_id);
            if(dialogWindow == null) {
                dialogWindow = new DialogWindow(null, null,friend_id, userQQ, client);
                ChatUIEntity chatUIEntity = new ChatUIEntity();
                chatUIEntity.setId(friend_id);
                chatUIEntity.setDialogWindow(dialogWindow);
                ChatUIList.addDialogWindow(chatUIEntity);
            } else {
                dialogWindow.show(); //如果以前创建过仅被别的窗口掩盖了 就重新显示
            }
            String message = new SystemTime().getSystemTime()+"\t"+friend_id + "说：" + "\n"
                    + (String) cmd.getData()+"\n";

            dialogWindow.getChatWin().append(message); //追加消息
            return;
        }

		/*if("WorldChat".equals(cmd.getCmd())) {
			//查询是否有与该好友的窗口该窗口
			String friendname = cmd.getSender();
			DialogWindow chatUI = ChatUIList.getDialogWindow("WorldChat");
			if(chatUI == null) {
				chatUI = new DialogWindow("WorldChat", "WorldChat", user.getUserQQ(), client);
				ChatUIEntity chatUIEntity = new ChatUIEntity();
				chatUIEntity.setName("WorldChat");
				chatUIEntity.setDialogWindow(chatUI);
				ChatUIList.addDialogWindow(chatUIEntity);
			} else {
				chatUI.show(); //如果以前创建过仅被别的窗口掩盖了 就重新显示
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yy-MM-dd hh:mm:ss a");
			String message = friendname + "说："
					+ (String) cmd.getData() + "\t" + sdf.format(date)
					+ "\n";
			chatUI.getChatWin().append(message); //追加消息
			return;
		}*/

		/*if("requeste_add_friend".equals(cmd.getCmd())) {
			if(cmd.isFlag() == false) {
				JOptionPane.showMessageDialog(null, cmd.getResult());
				return;
			}
			String sendername = cmd.getSender();
			int flag = JOptionPane.showConfirmDialog(null, "是否同意" + sendername + "的好友请求", "好友请求", JOptionPane.YES_NO_OPTION);
			System.out.println(flag);
			if(flag == 0) {
				cmd.setCmd("accept_add_friend");
			} else {
				cmd.setCmd("refuse_add_friend");
			}
			cmd.setSender(userQQ);
			cmd.setReceiver(sendername);
			client.sendData(cmd);
			return;
		}*/


		/*if("accept_add_friend".equals(cmd.getCmd())) {
			JOptionPane.showMessageDialog(null, cmd.getResult());

			return;
		}

		if("updatefriendlist".equals(cmd.getCmd())) {
			if(cmd.isFlag() == false) {
				JOptionPane.showMessageDialog(null, cmd.getResult());
				return;
			}

			User tmp = (User)cmd.getData();
			user.setFriendsList(tmp.getFriend());
			chatwindow.validate();
			chatwindow.repaint();
			chatwindow.setVisible(true);

			return;
		}

		if("refuse_to_add".equals(cmd.getCmd())) {
			JOptionPane.showMessageDialog(null, cmd.getResult());
			return;
		}

		if("changepwd".equals(cmd.getCmd())) {
			JOptionPane.showMessageDialog(null, cmd.getResult());
			return;
		}*/
        return;
    }

}

