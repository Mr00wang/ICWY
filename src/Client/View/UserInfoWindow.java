package Client.View;

import Client.View.WindowXY;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UserInfoWindow extends JFrame {

    //private JPanel contentPane;
    private JLabel label;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    protected Point pressedPoint;
    private JTextField textField_5;
    private JTextField textField_6;
    private JButton button;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserInfoWindow frame = new UserInfoWindow();
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
    public UserInfoWindow() {
        new StyleWindow();
        //
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/UserInfo.png"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,342,512);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);

        setUndecorated(true);
        setAlwaysOnTop(true);
        setBounds(100, 100, 342, 512);
        //c = new JPanel();
        c.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocation(WindowXY.getXY(this.getSize()));
        //setContentPane(c);
        c.setLayout(null);
        c.setBorder(new LineBorder(Color.GRAY));

        label = new JLabel(new ImageIcon("picture/Info.png"));
        label.setBounds(288, 10, 60, 38);
        c.add(label);

        JLabel lblNi = new JLabel("\u6635    \u79F0\uFF1A");
        lblNi.setBounds(59, 378, 75, 15);
        c.add(lblNi);

        textField = new JTextField();
        textField.setText("");
        textField.setBounds(144, 375, 120, 21);
        c.add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("\u6027    \u522B\uFF1A");
        label_1.setBounds(59, 403, 75, 24);
        c.add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(144, 405, 120, 21);
        c.add(textField_1);
        textField_1.setColumns(10);

        JLabel label_2 = new JLabel("\u5E74    \u9F84\uFF1A");
        label_2.setBounds(59, 437, 71, 15);
        c.add(label_2);

        textField_2 = new JTextField();
        textField_2.setBounds(144, 434, 120, 21);
        c.add(textField_2);
        textField_2.setColumns(10);

        JLabel label_3 = new JLabel("\u5BB6    \u4E61\uFF1A");
        label_3.setBounds(59, 462, 71, 15);
        c.add(label_3);

        textField_3 = new JTextField();
        textField_3.setBounds(144, 459, 120, 21);
        c.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u624B \u673A \u53F7\uFF1A");
        lblNewLabel.setBounds(59, 487, 71, 15);
        c.add(lblNewLabel);

        textField_4 = new JTextField();
        textField_4.setBounds(144, 484, 120, 21);
        c.add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(91, 336, 140, 29);
        c.add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("宋体", Font.BOLD, 24));
        textField_6.setBounds(72, 288, 179, 38);
        c.add(textField_6);
        textField_6.setColumns(10);

        //change
		/*ImageIcon icon = new ImageIcon("picture/edit_1.png");
		button = new JButton(); //设定透明效果
		button.setOpaque(false); //去掉背景点击效果
		button.setContentAreaFilled(false); //去掉聚焦线
		button.setFocusPainted(false); //去掉边框
		button.setBorder(null); //设置显示的图片
		button.setIcon(icon);*/
        button = new JButton("\u4FEE  \u6539");
        button.setBounds(257, 220, 75, 38);
        button.setFont(new Font("宋体", Font.PLAIN, 15));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        c.add(button);
        MyEvent();
    }
    public void MyEvent()
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInfoChange frame = new UserInfoChange();
                frame.setVisible(true);
            }
        });
        label.addMouseListener(new MouseAdapter() {
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
