package Client.View;

import Client.View.StyleWindow;
import Client.View.UserInfoWindow;
import Client.View.WindowXY;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UserInfoChange extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton button_1;
    private JButton button;
    protected Point pressedPoint;
    private JLabel label;
    private JTextField textField;
    private JLabel label_1;
    private JTextField textField_1;
    private JLabel label_2;
    private JTextField textField_2;
    private JLabel label_3;
    private JLabel label_4;
    private JTextField textField_4;
    private JLabel label_6;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserInfoChange frame = new UserInfoChange();
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
    public UserInfoChange() {
        new StyleWindow();
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/changeinfo.png"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,342,512);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);
        setBounds(100, 100, 342, 512);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setLocation(WindowXY.getXY(this.getSize()));
        c.setBorder(new LineBorder(Color.GRAY));
        c.setLayout(null);

        button = new JButton("取    消");
        button.setBounds(206, 469, 113, 33);
        button.setFont(new Font("宋体", Font.PLAIN, 20));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        c.add(button);

        button_1 = new JButton("保    存");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_1.setBounds(35, 469, 113, 33);
        button_1.setFont(new Font("宋体", Font.PLAIN, 20));
        button_1.setBackground(new Color(0, 191, 255));
        button_1.setForeground(SystemColor.desktop);
        c.add(button_1);

        label = new JLabel("\u6635    \u79F0\uFF1A");
        label.setFont(new Font("宋体", Font.PLAIN, 15));
        label.setBounds(32, 117, 77, 29);
        c.add(label);

        textField = new JTextField();
        textField.setBounds(119, 120, 166, 24);
        c.add(textField);
        textField.setColumns(10);

        label_1 = new JLabel("\u5E74    \u9F84\uFF1A");
        label_1.setFont(new Font("宋体", Font.PLAIN, 15));
        label_1.setBounds(35, 244, 83, 24);
        c.add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(120, 244, 166, 24);
        c.add(textField_1);
        textField_1.setColumns(10);

        label_2 = new JLabel("\u5BB6    \u4E61\uFF1A");
        label_2.setFont(new Font("宋体", Font.PLAIN, 15));
        label_2.setBounds(35, 328, 78, 21);
        c.add(label_2);

        textField_2 = new JTextField();
        textField_2.setBounds(120, 327, 166, 24);
        c.add(textField_2);
        textField_2.setColumns(10);

        label_3 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
        label_3.setFont(new Font("宋体", Font.PLAIN, 15));
        label_3.setBounds(32, 168, 86, 29);
        c.add(label_3);

        label_4 = new JLabel("\u6027    \u522B\uFF1A");
        label_4.setFont(new Font("宋体", Font.PLAIN, 15));
        label_4.setBounds(35, 285, 77, 26);
        c.add(label_4);

        String[] s = {"男","女"};
        JComboBox comboBox = new JComboBox(s);
        comboBox.setBounds(120, 286, 166, 24);
        c.add(comboBox);

        JLabel label_5 = new JLabel("\u624B \u673A \u53F7\uFF1A");
        label_5.setFont(new Font("宋体", Font.PLAIN, 15));
        label_5.setBounds(35, 377, 78, 19);
        c.add(label_5);

        textField_4 = new JTextField();
        textField_4.setBounds(120, 375, 166, 24);
        c.add(textField_4);
        textField_4.setColumns(10);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(120, 171, 166, 58);
        c.add(textArea);

        label_6 = new JLabel(new ImageIcon("picture/info_1.png"));
        label_6.setToolTipText("关闭");
        label_6.setBounds(277, 10, 54, 33);
        c.add(label_6);
        MyEvent();
    }
    public void MyEvent()
    {
        label_6.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                dispose();
            }
        });
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
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
