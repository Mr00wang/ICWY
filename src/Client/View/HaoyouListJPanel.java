package Client.View;

import javax.swing.JPanel;

import Client.Controller.Client;
import Client.Model.Config;
import Client.Model.SQL_Connect;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;

public class HaoyouListJPanel extends JPanel {

    /**
     * Create the panel.
     */
    public Client client;
    public HaoyouListJPanel(Client client) {
        this.client = client;
        setLayout(null);
        setBackground(new Color(0, 191, 255));
        genxin();

        this.setPreferredSize(new Dimension(0,530));


    }
    public void genxin()
    {

        Connection conn = null;
        Statement statement = null,statement1 = null;
        ResultSet rs=null,rs1 = null;
        String sql1 = "select friend_id from friend where id='"+Config.id+"'";

        try {
            conn = SQL_Connect.getConnection();
            statement = conn.createStatement();
            statement1 = conn.createStatement();
            rs = statement.executeQuery(sql1);

            while(rs.next())
            {
                String friend_id = rs.getString("friend_id");
                String sql2 = "select img,name,sign,id from user where id ='"+friend_id+"'";
                rs1 = statement1.executeQuery(sql2);
                while(rs1.next())
                {

                    Config.list.put(rs1.getString("id"),new FaceJPanel(rs1.getString("img"),rs1.getString("name"),
                            rs1.getString("sign"),rs1.getString("id"),client));
                    break;
                }

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
                statement1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Collection<FaceJPanel> faceJPanels = Config.list.values();
        List<FaceJPanel> list = new ArrayList(faceJPanels);
        Collections.sort(list);
        int i=0;
        for (FaceJPanel faceJPanel : list) {
            faceJPanel.setBounds(0, i++ * 60, 546, 59);
            this.add(faceJPanel);
        }

    }

}
