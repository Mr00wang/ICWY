package Client.View;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;

import Client.Controller.ChatUIEntity;
import Client.Controller.ChatUIList;
import Client.Controller.Client;
import Client.Model.Config;
import Client.Model.SQL_Connect;


public class FaceJPanel extends JPanel implements Comparable<FaceJPanel>, MouseListener, Runnable {

    private String image;
    private String netName;
    private String info;
    private String friend_id;
    private JLabel label_image;
    private JLabel label_netName;
    private JLabel label_info;
    public  Client client;

    public FaceJPanel(String image, String netName, String info, String friend_id,Client client) {
        this.image = image;
        this.netName = netName;
        this.info = info;
        this.friend_id = friend_id;
        this.client = client;

        this.setLayout(null);

        label_image = new JLabel();
        label_image.setBounds(6, 0, 53, 53);
        add(label_image);

        setImage(image);

        label_netName = new JLabel();
        label_netName.setBounds(73, 0, 331, 18);
        add(label_netName);
        label_netName.setFont(new Font("微软雅黑", Font.BOLD, 14));
        label_netName.setText(netName);

        label_info = new JLabel();
        label_info.setBounds(73, 28, 357, 28);
        add(label_info);
        label_info.setText(info);

        this.addMouseListener(this);

    }


    public void setImage(String image) {
        ImageIcon image1 = new ImageIcon("face/" + image + ".jpg");
        Image img = image1.getImage();
        img = img.getScaledInstance(53, 53, Image.SCALE_DEFAULT);
        image1.setImage(img);
        label_image.setIcon(image1);
        this.image = image;
    }

    public void setNetname(String netName) {
        label_netName.setText(netName);
        this.netName = netName;
    }

    public void setInfo(String info) {
        label_info.setText(info);
        this.info = info;
    }

    int xx = 0;
    int yy = 0;

    public void showPopupMenu(Component invoker,int x,int y,String uid, String netName,String image,
                              String info)
    {
        JPopupMenu menuinfo = new JPopupMenu();
        JMenuItem menuitem1 = new JMenuItem("发起聊天");
        JMenuItem menuitem2 = new JMenuItem("查看资料");
        JMenuItem menuitem3 = new JMenuItem("删除好友");

        //menuinfo.add(menuitem1);
        menuinfo.add(menuitem2);
        menuinfo.add(menuitem3);

        menuitem2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                FriendInfoWindow frame =new FriendInfoWindow(uid);
                frame.setVisible(true);
            }
        });
        menuitem3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //String id = "666001";
                Connection conn = null;
                Connection conn_1 = null;
                Statement statement = null;
                Statement statement_1 = null;
                String sql1 = "delete from friend where (id='"+Config.id+"'and friend_id='"+uid+"')";
                String sql2 = "delete from friend where (id='"+uid+"'and friend_id='"+Config.id+"')";
                try {
                    conn_1 = SQL_Connect.getConnection();
                    statement_1 = conn_1.createStatement();
                    conn = SQL_Connect.getConnection();
                    statement = conn.createStatement();
                    statement.executeUpdate(sql1);
                    statement.executeUpdate(sql2);

                    //SearchWindow frame = new SearchWindow();
                    //frame.setVisible(true);

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
        menuinfo.show(invoker, x, y);
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2)
        {
            System.out.println("点击成功");
            //查看与该好友是否创建过窗口
            DialogWindow dialogwindow = ChatUIList.getDialogWindow(netName);
            if(dialogwindow == null) {
                dialogwindow = new DialogWindow(netName,image,friend_id,Config.id,client);
                ChatUIEntity chatUIEntity = new ChatUIEntity();
                chatUIEntity.setName(netName);
                chatUIEntity.setDialogWindow(dialogwindow);
                ChatUIList.addDialogWindow(chatUIEntity);
            } else {
                dialogwindow.setAlwaysOnTop(true);
                dialogwindow.show(); //如果以前创建过仅被别的窗口掩盖了 就重新显示
            }
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        if(e.isMetaDown())
        {
            showPopupMenu(e.getComponent(), e.getX(), e.getY(),friend_id,netName,info,image);
        }
    }

    public void mouseEntered(MouseEvent e) {
        xx = this.getX();
        yy = this.getY();
        this.setLocation(xx + 3, yy + 3);
    }

    public void mouseExited(MouseEvent e) {
        this.setLocation(xx, yy);
    }

    @Override
    public int compareTo(FaceJPanel o) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
