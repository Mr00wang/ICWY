package Client.View;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Client.Model.SQL_Connect;

public class PasswordWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //	private JPanel contentPane;
    private JLabel label_5;
    private JLabel label_6;
    protected Point pressedPoint;
    private JLabel label;
    private JTextField textField;
    private JLabel label_1;
    private JTextField textField_1;
    private JLabel label_2;
    private JPasswordField passwordField;
    private JLabel label_3;
    private JPasswordField passwordField_1;
    private JButton button;
    private JButton button_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PasswordWindow frame = new PasswordWindow();
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
    public PasswordWindow() {
        new StyleWindow();
        //
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/ResetPassword_1.jpg"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,842,530);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);

        setUndecorated(true);
        //setAlwaysOnTop(true);
        setBounds(100, 100, 842, 530);
        setLocation(WindowXY.getXY(this.getSize()));
        //c = new JPanel();
        c.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(c);
        c.setLayout(null);
        c.setBorder(new LineBorder(Color.GRAY));

        //最小化
        label_5 = new JLabel(new ImageIcon("picture/最小化.png"));
        label_5.setBounds(724, 13, 72, 45);
        label_5.setToolTipText("最小化");
        c.add(label_5);

        //Exit
        label_6 = new JLabel(new ImageIcon("picture/关闭.png"));
        label_6.setBounds(770, 13, 72, 45);
        label_6.setToolTipText("关闭");
        c.add(label_6);

        //nick
        label = new JLabel("\u6635    \u79F0\uFF1A");
        label.setFont(new Font("宋体", Font.PLAIN, 24));
        label.setBounds(357, 155, 128, 29);
        c.add(label);

        textField = new JTextField();
        textField.setBounds(512, 155, 282, 32);
        c.add(textField);
        textField.setColumns(10);

        //phone
        label_1 = new JLabel("\u624B \u673A \u53F7\uFF1A");
        label_1.setFont(new Font("宋体", Font.PLAIN, 24));
        label_1.setBounds(357, 225, 134, 36);
        c.add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(512, 230, 282, 32);
        c.add(textField_1);
        textField_1.setColumns(10);

        label_2 = new JLabel("\u5BC6    \u7801\uFF1A");
        label_2.setFont(new Font("宋体", Font.PLAIN, 24));
        label_2.setBounds(357, 295, 136, 38);
        c.add(label_2);

        passwordField = new JPasswordField();
        passwordField.setBounds(510, 304, 282, 32);
        c.add(passwordField);

        label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
        label_3.setFont(new Font("宋体", Font.PLAIN, 24));
        label_3.setBounds(357, 365, 157, 38);
        c.add(label_3);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(511, 371, 282, 32);
        c.add(passwordField_1);

        //reset password
        button = new JButton("重        置");
        button.setFont(new Font("宋体", Font.PLAIN, 20));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        button.setBounds(561, 461, 196, 48);
        c.add(button);

        //cancel button
        button_1 = new JButton("取      消");
        button_1.setFont(new Font("宋体", Font.PLAIN, 20));
        button_1.setBackground(new Color(0, 191, 255));
        button_1.setForeground(SystemColor.desktop);
        button_1.setBounds(98, 461, 196, 48);
        c.add(button_1);
        MyEvent();
    }
    public void MyEvent()
    {
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Connection conn = null;
                Statement statement = null;
                ResultSet result = null;
                try {
                    conn = SQL_Connect.getConnection();
                    System.out.println("数据库连接成功");
                    statement = conn.createStatement();
                    String sql = "select name,phone from user where name='"+textField.getText()+"'";
                    String sql_1 = "update user set password='"+passwordField.getText().trim()+"' where phone='"+textField_1.getText().trim()+"'";
                    result = statement.executeQuery(sql);
                    if(textField.getText().trim().equals("") || textField_1.getText().trim().equals("") || passwordField.getText().trim().equals("") || passwordField_1.getText().trim().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "输入不能有空！");
                    }
                    else {
                        while(result.next())
                        {
                            if(textField.getText().trim().equals(result.getString("name")) && textField_1.getText().trim().equals(result.getString("phone")))
                            {
                                if(passwordField.getText().trim().equals(passwordField_1.getText().trim()))
                                {
                                    statement.executeUpdate(sql_1);
                                    JOptionPane.showMessageDialog(null, "重置成功");
                                    dispose();
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "两次密码不一致");
                                    passwordField.setText("");
                                    passwordField_1.setText("");
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "重置失败！\n昵称与手机号不一致!");
                                PasswordWindow frame = new PasswordWindow();
                                frame.setVisible(true);
                                dispose();
                            }
                        }
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    System.out.println("数据库连接失败");
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
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        label_5.addMouseListener(new MouseAdapter() {
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
