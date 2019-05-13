package Client.View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Client.Controller.ChatTread;
import Client.Controller.Client;
import Client.Controller.CommandTranser;
import Client.Model.Config;
import Client.Model.User;

import java.awt.event.*;
import java.net.Socket;

public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    public Point pressedPoint;
    private JPasswordField passwordField;
    public JButton button_1;
    public JLabel label,label_1,label_2,label_3,label_4;
    private JLabel label_5,label_6;
    ImageIcon image;
    Client client = new Client();
    public Socket socket = null;

    /*
     * Launch the application.
     */
    public static void main(String[] args) {
//
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginWindow frame = new LoginWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginWindow() {


        new StyleWindow();
        //background picture
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/login_1.png"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,402,429);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);
        c.setFont(new Font("Dialog", Font.PLAIN, 14));
        c.setForeground(Color.WHITE);
        c.setBackground(SystemColor.scrollbar);
        //setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setBounds(700,350, 402, 429);
        setLocation(WindowXY.getXY(this.getSize()));
        c.setLayout(null);
        c.setBorder(new LineBorder(Color.GRAY));
        setVisible(true);

        //image
        image = new ImageIcon("picture/login_2.png");
        Image img = image.getImage();
        img = img.getScaledInstance(75, 75, Image.SCALE_DEFAULT);
        image.setImage(img);
        label = new JLabel();
        label.setIcon(image);
        label.setBounds(10, 191, 75, 75);
        c.add(label);

        //id label
        label_1 = new JLabel("\u8D26  \u53F7\uFF1A");
        label_1.setBounds(95, 178, 65, 53);
        c.add(label_1);

        //password label
        label_2 = new JLabel("\u5BC6  \u7801\uFF1A");
        label_2.setBounds(95, 225, 70, 53);
        c.add(label_2);

        //id textField
        textField = new JTextField();
        textField.setBounds(170, 191, 215, 28);
        c.add(textField);
        textField.setColumns(10);

        //change password button
        label_3 = new JLabel("忘记密码？");
        label_3.setForeground(new Color(65, 105, 225));
        label_3.setBounds(170, 277, 65, 31);
        c.add(label_3);

        //register label
        label_4 = new JLabel("没有账号？");
        label_4.setForeground(new Color(65, 105, 225));
        label_4.setBounds(326, 274, 66, 37);
        c.add(label_4);

        //passwordField
        passwordField = new JPasswordField();
        passwordField.setBounds(170, 238, 215, 28);
        c.add(passwordField);

        //login button
        button_1 = new JButton("登          陆");
        button_1.setBackground(new Color(0, 191, 255));
        button_1.setForeground(SystemColor.desktop);
        button_1.setBounds(108, 319, 196, 48);
        c.add(button_1);

        //mix picture
        label_5 = new JLabel(new ImageIcon("picture/最小化.png"));
        label_5.setBounds(326, 10, 30, 37);
        label_5.setToolTipText("最小化");
        getContentPane().add(label_5);

        //exit picture
        label_6 = new JLabel(new ImageIcon("picture/关闭.png"));
        label_6.setBounds(366, 10, 30, 37);
        label_6.setToolTipText("关闭");
        getContentPane().add(label_6);
        MyEvent();
    }
    public void MyEvent()
    {
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                String id = textField.getText().trim();
                String password = passwordField.getText().trim();
                if (textField.getText().trim().equals("") || passwordField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "账号密码必须填写!");
                    return;
                }
                //
                Config.id = id;
                User user = new User(id, password);
                CommandTranser cmd = new CommandTranser();
                cmd.setCmd("login");
                cmd.setData(user);
                cmd.setReceiver(id);
                cmd.setSender(id);

                //实例化客户端 连接服务器 发送数据 密码是否正确?

                Client client = new Client(); //创建唯一的客户端（用于接受服务器发来的消息， socket接口），
                client.sendData(cmd); //发送数据
                cmd = client.getData(); //接受反馈的消息

                if(cmd != null) {
                    if(cmd.isFlag()) {
                        JOptionPane.showMessageDialog(null,  "登录成功");
                        dispose();
                        user = (User)cmd.getData();
                        ChatWindow chatwindow = new ChatWindow(user, client); //将user的全部信息传到FriendsUI中，并将唯一与服务器交流的接口传到FriendUI中 这里传client仅为了发送消息
                        ChatTread thread = new ChatTread(client, user, chatwindow); //这里传client为了收消息， 整个客户端用一个 ChatTread，一个client
                        thread.start();
                    }else {

                        JOptionPane.showMessageDialog(null, cmd.getResult());
                    }
                }
				/*Config.id = id;
				Config.password = password;
				Connection conn = null;
				Statement statement = null;
				ResultSet rs = null;
				boolean state = false;

				System.out.println(state);
				String sql = "select password from user where id = '"+Config.id+"'";
				try {
					conn = SQL_Connect.getConnection();
					statement = conn.createStatement();
					rs = statement.executeQuery(sql);
					while(rs.next())
					{
						if(state==true)
						{
							JOptionPane.showMessageDialog(null, "该用户已登陆！");
							break;
						}

					    else if(Config.password.equals(rs.getString("password")))
						{
					    	System.out.println(state);
							JOptionPane.showMessageDialog(null, "登陆成功");
							Client client = new Client(); //创建唯一的客户端（用于接受服务器发来的消息， socket接口），
							ChatTread thread = new ChatTread(client, Config.id);

							SocketEntity socketEntity = new SocketEntity();
							socketEntity.setName(Config.id);


							socketEntity.setSocket(socket);
							SocketList.addSocket(socketEntity);

							thread.start();


							ChatWindow frame = new ChatWindow(Config.id,client);
							frame.setVisible(true);
							dispose();
						}else
						{
							JOptionPane.showMessageDialog(null, "账号或密码错误！");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("数据库连接失败");
					e1.printStackTrace();
				}*/
				/*String id_1 = textField.getText().trim();
				String password_1 = passwordField.getText().trim();
				if (id_1.trim().equals("") || password_1.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "用户名和密码必须填写!");
					return;
				}
				Config.id = id_1;
				Config.password = password_1;
				try {
					JSONObject json = NetService.getNetService().login();

					if (json.getInt("state") == 0) {

						// 登录成功后 显示好友列表
						ChatWindow frame = new ChatWindow(Config.id);
						frame.setVisible(true);
						new ChatWindow(Config.id).setVisible(true);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, json.getString("msg"));
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "网络连接失败!...");
				}*/

            }
        });
        label_3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e)
            {
                label_3.setForeground(new Color(0, 191, 255));
            }
            public void mouseExited(MouseEvent e)
            {
                label_3.setForeground(new Color(65, 105, 225));
            }
            public void mousePressed(MouseEvent e)
            {
                PasswordWindow frame = new PasswordWindow();
                frame.setVisible(true);
            }
        });
        label_4.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e)
            {
                label_4.setForeground(new Color(0, 191, 255));
            }
            public void mouseExited(MouseEvent e)
            {
                label_4.setForeground(new Color(65, 105, 225));
            }
            public void mousePressed(MouseEvent e)
            {
                RegisterWindow frame1 = new RegisterWindow();
                frame1.setVisible(true);
                dispose();
            }
        });
        label_5.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e)
            {

            }
            public void mousePressed(MouseEvent e)
            {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
        label_6.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                dispose();
            }

        });
        //面板拖拉
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { //鼠标按下事件
                pressedPoint = e.getPoint(); //记录鼠标坐标
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) { // 鼠标拖拽事件
                Point point = e.getPoint();// 获取当前坐标
                Point locationPoint = getLocation();// 获取窗体坐标
                int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标
                int y = locationPoint.y + point.y - pressedPoint.y;
                setLocation(x, y);// 改变窗体位置
            }
        });
    }

}
