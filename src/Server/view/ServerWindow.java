package Service.view;

import Client.View.StyleWindow;
import Client.View.WindowXY;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ServerWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -6035892162159620076L;
    protected Point pressedPoint;
    private JLabel label;
    private JLabel label_1;
    private JButton button;
    private JButton button_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerWindow frame = new ServerWindow();
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
    public ServerWindow() {
        new StyleWindow();
        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("picture/Server.jpg"));
        this.getLayeredPane().add(picture,new Integer(Integer.MIN_VALUE));
        picture.setBounds(0,0,641,474);

        //
        JPanel c = new JPanel();
        c = (JPanel)getContentPane();
        c.setOpaque(false);
        setBounds(100, 100, 641, 474);

        setUndecorated(true);
        setAlwaysOnTop(true);
        setLocation(WindowXY.getXY(this.getSize()));
        c.setBackground(new Color(255, 255, 255));
        c.setBorder(new LineBorder(Color.GRAY));
        c.setLayout(null);

        label = new JLabel(new ImageIcon("picture/最小化.png"));
        label.setBounds(516, 13, 72, 43);
        c.add(label);

        label_1 = new JLabel(new ImageIcon("picture/关闭.png"));
        label_1.setBounds(555, 13, 72, 43);
        c.add(label_1);

        button = new JButton("\u6253\u5F00\u670D\u52A1\u5668");
        button.setBounds(123, 227, 133, 43);
        button.setFont(new Font("宋体", Font.PLAIN, 20));
        button.setBackground(new Color(0, 191, 255));
        button.setForeground(SystemColor.desktop);
        c.add(button);

        button_1 = new JButton("\u5173\u95ED\u670D\u52A1\u5668");
        button_1.setBounds(372, 227, 148, 43);
        button_1.setFont(new Font("宋体", Font.PLAIN, 20));
        button_1.setBackground(new Color(0, 191, 255));
        button_1.setForeground(SystemColor.desktop);
        c.add(button_1);
        MyEvent();
    }
    public void MyEvent()
    {
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
        label_1.addMouseListener(new MouseAdapter() {
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
