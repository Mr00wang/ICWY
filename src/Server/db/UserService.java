package Server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Client.Model.User;


public class UserService {

    //login验证账号密码
    public boolean checkUser(User user) {
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String sql = "select * from user where id =? and password =?";
        try {
            conn = DBHelper.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserQQ());
            stmt.setString(2, user.getUserpwd());
            rs = stmt.executeQuery();
            if(rs.next()) {

                return true;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //登陆后向客户端发送其好友列表
	/*public User getFriendsList(User user) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select * from " + user.getUserQQ() + "_friends";
		ArrayList<String> friendslist = new ArrayList<String>(); //这里假设好友不超过20个
		try {
			conn = DBHelper.getConnection();

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int count = 0;
			while(rs.next()) {
				friendslist.add(rs.getString(2));	//获取好友qq
				count++;
			}
			user.setFriendsNum(count);
			user.setFriendsList(friendslist);
			return user;

		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}*/

	/*//用户注册
	public boolean registerUser(User user) {
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		Connection conn = null;
		ResultSet rs = null;
		int insertFlag = 0;
		int creatFlag = 0;
		conn = DBHelper.getConnection();
		String sql = "select * from userinfo where qq =?";
		String insertusersql = "insert into userinfo (qq, password, photoID, nickname, telephone, email) values(?, ?, ?, ?, ?, ?)";
		String creatfriendstabsql = "CREATE TABLE " + user.getUserQQ() + "_friends " + "(id INT NOT NULL AUTO_INCREMENT, qq VARCHAR(45) NOT NULL, PRIMARY KEY (id))";
		try {
			stmt1 = conn.prepareStatement(sql);
			stmt1.setString(1, user.getUserQQ());
			rs = stmt1.executeQuery();
			if(rs.next()) {
				System.out.println("该用户已存在" + user.getUserQQ() + "***");
				//用户已被注册
				return false;
			}
			else {
				System.out.println("该用户不存在" + user.getUserQQ() + "***");
				//向用户表插入数据
				stmt2 = conn.prepareStatement(insertusersql);
				stmt2.setString(1, user.getUserQQ());
				stmt2.setString(2, user.getUserpwd());
				stmt2.setInt(3, user.getUserPhotoID());
				stmt2.setString(4, user.getUserNickname());
				stmt2.setString(5, user.getUserTelephone());
				stmt2.setString(6, user.getUserEmail());
				insertFlag = stmt2.executeUpdate();
				System.out.println("向表中插入数据" + user.getUserQQ() + "****" + insertFlag);
				//创建好友表
				stmt3 = conn.prepareStatement(creatfriendstabsql);
				creatFlag = stmt3.executeUpdate();

				System.out.println("创建表" + user.getUserQQ() + "*******" + creatFlag);
				if(insertFlag == 1) {
					return true;
				}

			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt1 != null) {
					stmt1.close();
				}
				if(stmt2 != null) {
					stmt2.close();
				}
				if(stmt3 != null) {
					stmt3.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}*/

    //添加好友
	/*public boolean addFriend(String sender, String receiver) {
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		Connection conn = null;
		int updateResult1 = 0;
		int updateResult2 = 0;
		conn = DBHelper.getConnection();
		String sql1 = "insert into " + sender + "_friends (qq) values(?)";
		String sql2 = "insert into " + receiver + "_friends (qq) values(?)";
		try {
			stmt1 = conn.prepareStatement(sql1);
			stmt2 = conn.prepareStatement(sql2);
			stmt1.setString(1, receiver);
			stmt2.setString(1, sender);
			updateResult1 = stmt1.executeUpdate();
			updateResult2 = stmt2.executeUpdate();
			if(updateResult1 == 1 && updateResult2 == 1) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(stmt1 != null) {
					stmt1.close();
				}
				if(stmt2 != null) {
					stmt2.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}*/

    //修改信息
	/*public boolean changeInfo(User user) {
		return false;
	}*/

    //获得用户的相关信息
	/*public User getUser(User user) {
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		Connection conn = null;
		ResultSet rs = null;
		conn = DBHelper.getConnection();
		String sql = "select * from userinfo where qq =?";
		try {

			stmt1 = conn.prepareStatement(sql);
			stmt1.setString(1, user.getUserQQ());
			rs = stmt1.executeQuery();
			if(rs.next()) {
				user.setUserQQ(rs.getString("qq"));
				user.setUserpwd(rs.getString("password"));
				user.setUserPhotoID(rs.getInt("photoID"));
				user.setUserNickname(rs.getString("nickname"));
				user.setUserTelephone(rs.getString("telephone"));
				user.setUserEmail(rs.getString("email"));
				System.out.println(user);
				return user;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt1 != null) {
					stmt1.close();
				}
				if(stmt2 != null) {
					stmt2.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}*/

}
