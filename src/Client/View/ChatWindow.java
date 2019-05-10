package Client.View;

import Client.Controller.Client;
import Client.Controller.CommandTranser;
import Client.Model.Config;
import Client.Model.SQL_Connect;
import Client.Model.User;
import Client.View.StyleWindow;
import Client.View.WindowXY;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;

public class ChatWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected Point pressedPoint;
    private JLabel label;
    private JLabel label_1;
    private JLabel lblAdd;
    public String picture_path;
    public Client client;
    private User owner;

    /**
     * Create the frame.
     */
    public ChatWindow(User owner,Client client) {
        this.owner=owner;
        this.client=client;
        //new StyleWindow();

        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/ChatWindow.jpg"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,842,610);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);
        //
        setBounds(100, 100, 842, 602);
        setUndecorated(true);
        //setAlwaysOnTop(true);
        setLocation(WindowXY.getXY(this.getSize()));
        c.setLayout(null);
        c.setBorder(new LineBorder(Color.GRAY));

        //头面板
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBounds(0, 0, 842, 54);
        c.add(panel);
        panel.setLayout(null);

        //最小化
        label = new JLabel(new ImageIcon("picture/最小化_1.jpg"));
        label.setToolTipText("最小化");
        label.setBounds(730, 5, 45, 41);
        panel.add(label);

        //Exit
        label_1 = new JLabel(new ImageIcon("picture/关闭_2.jpg"));
        label_1.setToolTipText("关闭");
        label_1.setBounds(780, 3, 39, 41);
        panel.add(label_1);

        //add friend
        lblAdd = new JLabel(new ImageIcon("picture/add.jpg"));
        lblAdd.setToolTipText("添加好友");
        lblAdd.setBounds(133, 10, 72, 34);
        panel.add(lblAdd);

        //head
        System.out.println(Config.id);
        Connection conn = null;
        Statement statement = null;
        ResultSet  rs = null;
        String sql = "select id,img from user";
        String imgstr = "";
        try {
            conn = SQL_Connect.getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while(rs.next())
            {
                if(Config.id.equals(rs.getString("id")))
                {
                    imgstr = rs.getString("img");
                    break;
                }

            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        finally {

            try {
                statement.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        ImageIcon image = new ImageIcon("face/"+imgstr+".jpg");
        Image img = image.getImage();
        img = img.getScaledInstance(60, 49, Image.SCALE_DEFAULT);
        image.setImage(img);
        JLabel lblHead = new JLabel();
        lblHead.setIcon(image);
        lblHead.setBounds(37, 0, 60, 54);
        panel.add(lblHead);


        //Menu Info
        lblHead.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e)
            {
                if(e.isMetaDown())
                {
                    showPopupMenu(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        JPanel panel_6 = new JPanel();
        panel_6.setLayout(new BorderLayout());
        JPanel panel_7 = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(2, 54, 189, 544);
        getContentPane().add(tabbedPane);
        tabbedPane.setBackground(SystemColor.desktop);
        tabbedPane.addTab("我的好友", null,panel_6,null);
        tabbedPane.addTab("我的群聊", null,panel_7,null);
        JScrollPane scrollpane = new JScrollPane();
        panel_6.add(scrollpane,BorderLayout.CENTER);

        //加载好友列表
        HaoyouListJPanel haoyouListJPanel = new HaoyouListJPanel(client);
        haoyouListJPanel.setForeground(Color.WHITE);
        scrollpane.setViewportView(haoyouListJPanel);
        setVisible(true);
        MyEvent();
    }
    public void showPopupMenu(Component invoker,int x,int y)
    {
        JPopupMenu menuinfo = new JPopupMenu();
        JMenuItem menuitem1 = new JMenuItem("个人资料");
        menuinfo.add(menuitem1);
        menuitem1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                UserInfoWindow frame = new UserInfoWindow();
                frame.setVisible(true);
            }
        });
        menuinfo.show(invoker, x, y);
    }
    public void MyEvent()
    {
        lblAdd.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                SearchWindow frame = new SearchWindow();
                frame.setVisible(true);
            }
        });
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
        label_1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                CommandTranser cmd = new CommandTranser();
                cmd.setCmd("logout");
                cmd.setReceiver("Server");
                cmd.setSender(owner.getUserQQ());
                client.sendData(cmd);
                dispose();
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
}
