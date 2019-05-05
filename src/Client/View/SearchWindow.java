package Client.View;

import Client.Model.Config;
import Client.Model.SQL_Connect;
import Client.View.WindowXY;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

public class SearchWindow extends JFrame {

    private static final long serialVersionUID = 2889360176127471427L;
    //private JPanel contentPane;
    private JLabel lblMin;
    private JLabel lblExit;
    protected Point pressedPoint;
    private JTextField textField;
    private JButton button;
    private JScrollPane scrollPane;
    private JPanel c;
    int q = 0;
    private JButton button_1;
    protected String id_1;
    protected String name_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchWindow frame = new SearchWindow();
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
    public SearchWindow() {
        new StyleWindow();
        //
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/searchfriend.png"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,735,255);

        //
        c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);
        //setAlwaysOnTop(true);
        setUndecorated(true);
        setBounds(100, 100, 735, 255);
        //c = new JPanel();
        setLocation(WindowXY.getXY(this.getSize()));
        //c.setBorder(new EmptyBorder(5, 5, 5, 5));
        c.setLayout(null);
        c.setBorder(new LineBorder(Color.GRAY));

        //最小化
        lblMin = new JLabel(new ImageIcon("picture/最小化.png"));
        lblMin.setBounds(630, 10, 54, 33);
        lblMin.setToolTipText("最小化");
        c.add(lblMin);

        //Exit
        lblExit = new JLabel(new ImageIcon("picture/关闭.png"));
        lblExit.setBounds(680, 10, 54, 33);
        lblExit.setToolTipText("关闭");
        c.add(lblExit);

        textField = new JTextField();
        textField.setBounds(116, 89, 366, 34);
        c.add(textField);
        textField.setColumns(10);

        button = new JButton("查    找");
        button.setBounds(533, 82, 151, 45);
        button.setFont(new Font("宋体", Font.PLAIN, 20));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        c.add(button);


        button_1 = new JButton("添 加 好 友");
        button_1.setBounds(533, 170, 151, 45);
        button_1.setFont(new Font("宋体", Font.PLAIN, 20));
        button_1.setBackground(new Color(0, 191, 255));
        button_1.setForeground(SystemColor.desktop);
        // c.add(button_1);



        MyEvent();
    }
    public void MyEvent()
    {
        button_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //String id = "666001";
                Connection conn = null;
                Connection conn_1 = null;
                Statement statement = null;
                Statement statement_1 = null;
                ResultSet rs = null;
                String sql = "select friend_id from friend where id = '"+Config.id+"'";
                String sql1 = "insert into friend values('"+Config.id+"','"+id_1+"')";
                String sql2 = "insert into friend values('"+id_1+"','"+Config.id+"')";
                try {
                    int p = 0,k=0;
                    conn_1 = SQL_Connect.getConnection();
                    statement_1 = conn_1.createStatement();
                    rs = statement_1.executeQuery(sql);
                    conn = SQL_Connect.getConnection();
                    statement = conn.createStatement();
                    while(rs.next())
                    {
                        if(id_1.equals(rs.getString("friend_id")))
                        {
                            k=1;
                            break;
                        }
                    }
                    if(k==0)
                    {
                        p = statement.executeUpdate(sql1);
                        p = statement.executeUpdate(sql2);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "您已有该好友！");
                        SearchWindow frame = new SearchWindow();
                        frame.setVisible(true);
                        dispose();
                    }
                    if(p!=0)
                    {
                        JOptionPane.showMessageDialog(null, "添加好友成功！");
                        SearchWindow frame = new SearchWindow();
                        frame.setVisible(true);
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "添加好友失败！");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("数据库连接失败");
                    e1.printStackTrace();
                }
                finally {
                    try {
                        statement.close();
                        conn.close();
                        statement_1.close();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        System.out.println("关闭statement失败");
                        e1.printStackTrace();
                    }
                }
            }
        });
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Connection conn = null;
                Statement statement = null;
                ResultSet rs = null;
                //String id = "666001";
                String str = "select id,name from user where id = '"+textField.getText().trim()+"'";
                try {
                    conn = SQL_Connect.getConnection();
                    System.out.println("数据库连接成功");
                    statement = conn.createStatement();
                    rs = statement.executeQuery(str);
                    while(rs.next())
                    {
                        if(textField.getText().trim().equals(rs.getString("id")))
                        {
                            q = 1;
                            //JTable
                            id_1 = rs.getString("id");
                            name_1 = rs.getString("name");
                            String[][] userinfo = {{id_1,name_1}};
                            String[] name = {"账号","昵称"};
                            JTable table = new JTable(userinfo,name);

                            //设置字体大小
                            table.setFont(new Font("微软雅黑",5,18));
                            //设置表头字体大小
                            JTableHeader header = table.getTableHeader();
                            header.setPreferredSize(new Dimension(header.getWidth(),40));
                            header.setFont(new Font("楷体",5,15));
                            table.setRowHeight(42);

                            //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//取消自动调整
                            //table.getColumnModel().getColumn(1).setPreferredWidth(300);
                            scrollPane = new JScrollPane();
                            scrollPane.setBounds(116, 149, 366, 90);

                            c.add(button_1);
                            scrollPane.setViewportView(table);
                            c.add(scrollPane);
                        }
                    }
                    if(q==0)
                    {
                        JOptionPane.showMessageDialog(null, "没有该用户！");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("数据库连接失败！");
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
            }
        });
        lblMin.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
        lblExit.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
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
