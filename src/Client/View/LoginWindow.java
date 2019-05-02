package Client.View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class LoginWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    public Point pressedPoint;
    private JPasswordField passwordField;
    public JButton button_1;
    public JLabel label,label_1,label_2,label_3,label_4;
    private JLabel label_5,label_6;
    ImageIcon image;

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
        setAlwaysOnTop(true);
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
        label_2.setBounds(95, 225, 65, 53);
        c.add(label_2);

        //id textField
        textField = new JTextField();
        textField.setBounds(170, 191, 215, 28);
        c.add(textField);
        textField.setColumns(10);

        //change password button
        label_3 = new JLabel("忘记密码？");
        label_3.setForeground(new Color(65, 105, 225));
        label_3.setBounds(170, 277, 60, 31);
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
                RegisterWindow frame = new RegisterWindow();
                frame.setVisible(true);
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
