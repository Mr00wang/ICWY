package Client.View;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RegisterWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //	private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JLabel label_5;
    private JLabel label_6;
    private JLabel label_7;
    private JTextField textField_2;
    private JButton button_1;
    private JButton button_2;
    private JLabel label_8;
    private JButton button;
    protected Point pressedPoint;
    public ImageIcon image;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(RevertSql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RevertSql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RevertSql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RevertSql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterWindow frame = new RegisterWindow();
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
    public RegisterWindow() {
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/Register.jpg"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,842,530);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);

        setUndecorated(true);
        setBounds(600, 400, 842, 530);
        setLocation(WindowXY.getXY(this.getSize()));
        c.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(c);
        c.setLayout(null);
        setAlwaysOnTop(true);
        c.setBorder(new LineBorder(Color.GRAY));

        //nick
        JLabel label = new JLabel("昵    称：");
        label.setFont(new Font("宋体", Font.PLAIN, 22));
        label.setBounds(368, 147, 151, 34);
        c.add(label);

        textField = new JTextField();
        textField.setBounds(515, 151, 230, 32);
        c.add(textField);
        textField.setColumns(10);

        //age
        JLabel label_1 = new JLabel("年    龄：");
        label_1.setFont(new Font("宋体", Font.PLAIN, 22));
        label_1.setBounds(368, 194, 151, 34);
        c.add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(515, 198, 230, 32);
        c.add(textField_1);
        textField_1.setColumns(10);

        //sex
        JLabel label_2 = new JLabel("性    别：");
        label_2.setFont(new Font("宋体", Font.PLAIN, 22));
        label_2.setBounds(368, 241, 151, 34);
        c.add(label_2);

        JRadioButton radioButton = new JRadioButton("男");
        radioButton.setFont(new Font("宋体", Font.PLAIN, 20));
        radioButton.setSelected(true);
        radioButton.setBounds(514, 246, 51, 27);
        c.add(radioButton);

        JRadioButton radioButton_1 = new JRadioButton("女");
        radioButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
        radioButton_1.setBounds(590, 246, 51, 27);
        c.add(radioButton_1);

        //password
        JLabel label_3 = new JLabel("密    码：");
        label_3.setFont(new Font("宋体", Font.PLAIN, 22));
        label_3.setBounds(368, 288, 138, 34);
        c.add(label_3);

        JLabel label_4 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
        label_4.setFont(new Font("宋体", Font.PLAIN, 22));
        label_4.setBounds(368, 335, 151, 53);
        c.add(label_4);

        //confirm password
        passwordField = new JPasswordField();
        passwordField.setBounds(515, 292, 230, 32);
        c.add(passwordField);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(515, 348, 230, 32);
        c.add(passwordField_1);

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

        //phone
        label_7 = new JLabel("手 机 号：");
        label_7.setFont(new Font("宋体", Font.PLAIN, 22));
        label_7.setBounds(368, 401, 150, 34);
        c.add(label_7);

        textField_2 = new JTextField();
        textField_2.setBounds(515, 405, 230, 32);
        c.add(textField_2);
        textField_2.setColumns(10);

        //register button
        button_1 = new JButton("注       册");
        button_1.setFont(new Font("宋体", Font.PLAIN, 20));
        button_1.setBackground(new Color(0, 191, 255));
        button_1.setForeground(SystemColor.desktop);
        button_1.setBounds(513, 470, 196, 48);
        c.add(button_1);

        //back button
        button_2 = new JButton("返       回");
        button_2.setFont(new Font("宋体", Font.PLAIN, 20));
        button_2.setBackground(new Color(0, 191, 255));
        button_2.setForeground(SystemColor.desktop);
        button_2.setBounds(128, 470, 196, 48);
        c.add(button_2);


        //head
        image = new ImageIcon("picture/login_2.png");
        Image img = image.getImage();
        img = img.getScaledInstance(171, 171, Image.SCALE_DEFAULT);
        image.setImage(img);
        label_8 = new JLabel();
        label_8.setIcon(image);
        label_8.setBounds(86, 135, 171, 171);
        c.add(label_8);

        //choose head
        button = new JButton("选择头像");
        button.setFont(new Font("宋体", Font.PLAIN, 20));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        button.setBounds(86, 359, 151, 45);
        c.add(button);
        MyEvent();
    }
    public void MyEvent()
    {
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow frame = new LoginWindow();
                frame.setVisible(true);
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
