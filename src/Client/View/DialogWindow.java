package Client.View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Client.Controller.ChatUIList;
import Client.Controller.Client;
import Client.Controller.CommandTranser;
import Client.Model.SystemTime;
import Client.Model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogWindow extends JFrame {

    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPane;
    private JButton button;
    private JButton button_1;
    private JButton button_2;
    private JTextArea textArea;
    private JTextArea textArea_2;
    private JLabel nameLabel;
    protected Point pressedPoint;
    private Client client;
    private String who;
    private String imgStr;
    private String friend_id;
    private String friend_name;
    private String info;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    //DialogWindow frame = new DialogWindow(null,"netnameStr","head4",null,null);
                    //frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public DialogWindow(String friend_name,String imgStr, String friend_id, String who, Client client) {
        this.imgStr=imgStr;
        this.friend_name=friend_name;
        this.friend_id=friend_id;
        this.who=who;
        this.client = client;

        this.setTitle(friend_id);
        ImageIcon imageIcon = new ImageIcon("face/" + imgStr + ".jpg");

        this.setIconImage(imageIcon.getImage());

        //nameLabel.setText(friend_name);
        //img.setIcon(imageIcon);


        new StyleWindow();
        //setUndecorated(true);
        setResizable(false);
        setLocation(WindowXY.getXY(this.getSize()));
        setBounds(190, 54, 775, 639);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        JSplitPane splitPane = new JSplitPane();
        splitPane.setBorder(new LineBorder(Color.GRAY));
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(360);
        splitPane.setBounds(0, 0, 750, 600);
        contentPane.add(splitPane);


        JPanel panel_1 = new JPanel();
        splitPane.setRightComponent(panel_1);

        panel_1.setLayout(null);

        FlowLayout flowLayout_1 = new FlowLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        JPanel panel_2 = new JPanel(flowLayout_1);
        panel_2.setBounds(0, 13, 748, 34);
        panel_1.add(panel_2);

        //字体 font
        button_1 = new JButton("\u5B57  \u4F53");
        button_1.setFont(new Font("宋体", Font.PLAIN, 15));
        button_1.setBackground(new Color(0, 191, 255));
        button_1.setForeground(SystemColor.desktop);
        panel_2.add(button_1);

        //大小 size
        button_2 = new JButton("\u5927  \u5C0F");
        button_2.setFont(new Font("宋体", Font.PLAIN, 15));
        button_2.setBackground(new Color(0, 191, 255));
        button_2.setForeground(SystemColor.desktop);
        panel_2.add(button_2);

        //文件 file
        JButton button_3 = new JButton("文  件");
        button_3.setFont(new Font("宋体", Font.PLAIN, 15));
        button_3.setBackground(new Color(0, 191, 255));
        button_3.setForeground(SystemColor.desktop);
        panel_2.add(button_3);

        //发送面板
        FlowLayout flowLayout_2 = new FlowLayout();
        flowLayout_2.setAlignment(FlowLayout.RIGHT);
        JPanel panel_3 = new JPanel(flowLayout_2);
        panel_3.setBounds(0, 187, 748, 34);
        panel_1.add(panel_3);

        JButton button_4 = new JButton("\u9000  \u51FA");
        button_4.setFont(new Font("宋体", Font.PLAIN, 15));
        button_4.setBackground(new Color(0, 191, 255));
        button_4.setForeground(SystemColor.desktop);
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Config.closeDialogWindow(uidStr);
                ChatUIList.deletDialogWindow(friend_id);
                dispose();
            }
        });
        panel_3.add(button_4);

        //发送 send
        button = new JButton("\u53D1  \u9001");
        button.setFont(new Font("宋体", Font.PLAIN, 15));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        panel_3.add(button);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 60, 748, 135);
        panel_1.add(scrollPane_1);

        textArea = new JTextArea();
        scrollPane_1.setViewportView(textArea);

        JPanel panel_4 = new JPanel();
        splitPane.setLeftComponent(panel_4);
        panel_4.setLayout(null);

        JPanel panel_5 = new JPanel();
        panel_5.setBounds(0, 0, 748, 66);
        panel_4.add(panel_5);
        panel_5.setLayout(null);

        ImageIcon image = new ImageIcon("face/"+imgStr+".jpg");
        Image img1 = image.getImage();
        img1 = img1.getScaledInstance(66, 66, Image.SCALE_DEFAULT);
        image.setImage(img1);
        JLabel img = new JLabel();
        img.setIcon(image);
        img.setBounds(14, 0, 66, 66);
        panel_5.add(img);

        nameLabel = new JLabel();
        nameLabel.setToolTipText("\u6635\u79F0");
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        nameLabel.setBounds(106, 13, 359, 40);
        panel_5.add(nameLabel);

        //上面面板
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 67, 748, 292);
        panel_4.add(scrollPane);

        textArea_2 = new JTextArea();
        textArea_2.setEditable(false);
        scrollPane.setViewportView(textArea_2);

        MyEvent();

    }
    public JTextArea getChatWin() {
        return textArea_2;
    }
    public void MyEvent()
    {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ChatUIList.deletDialogWindow(friend_id);
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                ChatUIList.deletDialogWindow(friend_id);
                dispose();
            }

        });
        //发送
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                textArea_2.setText("");
                String message = new SystemTime().getSystemTime() + "\t"+"我说 : " +"\n"+ textArea.getText()+"\n";
                //聊天框添加消息
                textArea_2.append(message);

                //数据
                CommandTranser cmd = new CommandTranser();
                cmd.setCmd("message");
					/*if("WorldChat".equals(owner_name)) {
						cmd.setCmd("WorldChat");
					}*/
                cmd.setSender(who);
                cmd.setReceiver(friend_id);
                cmd.setData(textArea.getText());

                //发送
                client.sendData(cmd);

                //发送完消息后清除输入框的内容
                textArea.setText(null);
            }
        });
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


    public static DialogWindow getDialogWindow(String friendname) {
        // TODO Auto-generated method stub
        return null;
    }



}
