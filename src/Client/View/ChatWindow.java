package Client.View;

import Client.View.StyleWindow;
import Client.View.WindowXY;

import java.awt.*;
import java.awt.event.*;

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
    private JButton button_1;
    private JButton button;
    private JButton button_2;
    private JScrollPane scrollPane;
    private JLabel lblAdd;
    public String picture_path;
    private JPopupMenu menuinfo;
    private JMenuItem menuitem1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChatWindow frame = new ChatWindow();
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
    public ChatWindow() {
        new StyleWindow();

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
        ImageIcon image = new ImageIcon("E:\\java\\ICWY1\\picture\\head1.jpg");
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

        //列表
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setOpaque(true);
        tabbedPane.setBorder(new LineBorder(Color.GRAY));
        tabbedPane.setBounds(0, 54, 189, 544);
        c.add(tabbedPane);


        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("我的好友", null, tabbedPane_1, null);

        JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("我的群聊", null, tabbedPane_2, null);

        //分割板
        JSplitPane splitPane = new JSplitPane();
        splitPane.setBorder(new LineBorder(Color.GRAY));
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(320);
        splitPane.setBounds(190, 54, 652, 544);
        //c.add(splitPane);

        JPanel panel_1 = new JPanel();
        splitPane.setRightComponent(panel_1);

        panel_1.setLayout(null);

        FlowLayout flowLayout_1 = new FlowLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        JPanel panel_2 = new JPanel(flowLayout_1);
        panel_2.setBounds(0, 0, 651, 34);
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
        panel_3.setBounds(0, 175, 651, 33);
        panel_1.add(panel_3);

        //发送 send
        button = new JButton("\u53D1  \u9001");
        button.setFont(new Font("宋体", Font.PLAIN, 15));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        panel_3.add(button);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 44, 651, 121);
        panel_1.add(scrollPane_1);

        JTextArea textArea = new JTextArea();
        scrollPane_1.setViewportView(textArea);

        JPanel panel_4 = new JPanel();
        splitPane.setLeftComponent(panel_4);
        panel_4.setLayout(null);

        JPanel panel_5 = new JPanel();
        panel_5.setBounds(0, 0, 651, 33);
        panel_4.add(panel_5);

        //上面面板
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 31, 651, 288);
        panel_4.add(scrollPane);

        JTextArea textArea_2 = new JTextArea();
        scrollPane.setViewportView(textArea_2);
        MyEvent();
    }
    public static void showPopupMenu(Component invoker,int x,int y)
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
                //JOptionPane.showConfirmDialog(null,"确定关闭？");
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
