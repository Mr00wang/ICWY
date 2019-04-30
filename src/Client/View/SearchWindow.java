package Client.View;

import Client.View.WindowXY;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class SearchWindow extends JFrame {

    //private JPanel contentPane;
    private JLabel lblMin;
    private JLabel lblExit;
    protected Point pressedPoint;
    private JTextField textField;
    private JButton button;

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
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/searchfriend.png"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,735,255);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);
        setAlwaysOnTop(true);
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
        textField.setBounds(81, 120, 366, 34);
        c.add(textField);
        textField.setColumns(10);

        button = new JButton("查    找");
        button.setBounds(508, 112, 151, 45);
        button.setFont(new Font("宋体", Font.PLAIN, 20));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        c.add(button);
        MyEvent();
    }
    public void MyEvent()
    {
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
