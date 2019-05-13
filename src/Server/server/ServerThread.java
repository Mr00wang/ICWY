package Server.server;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Client.Controller.CommandTranser;
import Client.Model.User;
import Server.db.UserService;




public class ServerThread extends Thread{
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        //ObjectOutputStream oos3 = null;

        while(socket != null) {
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                CommandTranser cmd = (CommandTranser) ois.readObject();

                //执行命令来自客户端的请求
                cmd = execute(cmd);

                //消息对话请求，服务器将sender发来的消息发送给receiver
                if("message".equals(cmd.getCmd())) {
                    //如果 msg.ifFlag即 服务器处理成功 可以向朋友发送信息 如果服务器处理信息失败 信息发送给发送者本人
                    if(cmd.isFlag()) {

                        oos1 = new ObjectOutputStream(SocketList.getSocket(cmd.getReceiver()).getOutputStream());
                    } else {

                        oos2 = new ObjectOutputStream(socket.getOutputStream());
                    }
                }

				/*if ("WorldChat".equals(cmd.getCmd())) {
					HashMap<String, Socket> map = SocketList.getMap();
					Iterator<Map.Entry<String, Socket>> it = map.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry<String, Socket> entry = it.next();
						if(!entry.getKey().equals(cmd.getSender())) {
							oos1 = new ObjectOutputStream(entry.getValue().getOutputStream());
							oos1.writeObject(cmd);
						}
					}
					continue;

				}*/
                //登录请求 将数据发送给sender
                if ("login".equals(cmd.getCmd())) {
                    oos1 = new ObjectOutputStream(socket.getOutputStream());
                }

                //注册请求 将数据发送给sender
				/*if ("register".equals(cmd.getCmd())) {
					System.out.println("向客户端发送消息");
					oos1 = new ObjectOutputStream(socket.getOutputStream());
				}
				*/
                //添加好友请求将数据发送给 receiver
				/*if ("requeste_add_friend".equals(cmd.getCmd())) {
					//在线，将请求发给receiver
					if(cmd.isFlag()) {
						oos1 = new ObjectOutputStream(SocketList.getSocket(cmd.getReceiver()).getOutputStream());
					} else {
						//不管在不在线都要向发送方提示消息发送成功
						oos2 = new ObjectOutputStream(socket.getOutputStream());
					}
				}
				*/
                //同意添加好友请求将数据发送给 receiver和sender
				/*if ("accept_add_friend".equals(cmd.getCmd())) {
					oos1 = new ObjectOutputStream(socket.getOutputStream());
					if(SocketList.getSocket(cmd.getReceiver()) != null) {
						oos2 = new ObjectOutputStream(SocketList.getSocket(cmd.getReceiver()).getOutputStream());
					}
				}*/

				/*
				if("updatefriendlist".equals(cmd.getCmd())) {
					oos1 = new ObjectOutputStream(socket.getOutputStream());
				}
				*/

                //拒绝添加好友请求将数据发送给 receiver
				/*if ("refuse_to_add".equals(cmd.getCmd())) {
					//被拒绝方在线
					if(cmd.isFlag()) {
						oos1 = new ObjectOutputStream(SocketList.getSocket(cmd.getReceiver()).getOutputStream());
					}else { //被拒方不在线则向拒绝方发送消息
						oos2 = new ObjectOutputStream(socket.getOutputStream());
					}
				}*/

                //修改资料请求 发送给sender 功能暂未实现
				/*if ("changeinfo".equals(cmd.getCmd())) {
					oos1 = new ObjectOutputStream(socket.getOutputStream());
				}

				//修改密码请求 将数据发送给sender
				if ("changepwd".equals(cmd.getCmd())) {
					oos1 = new ObjectOutputStream(socket.getOutputStream());
				}

				//修改密码请求 将数据发送给sender
				if ("findfriend".equals(cmd.getCmd())) {
					oos1 = new ObjectOutputStream(socket.getOutputStream());
				}*/

                //用户下线
                if("logout".equals(cmd.getCmd())) {

                }

                if(oos1 != null) {
                    oos1.writeObject(cmd);
                }
                if(oos2 != null) {
                    oos2.writeObject(cmd);
                }
            } catch(IOException e) {
                socket = null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //处理客户端发来的命令
    private CommandTranser execute(CommandTranser cmd) {

        //登录请求
        if("login".equals(cmd.getCmd())) {
            UserService userservice = new UserService();
            User user = (User)cmd.getData();
            cmd.setFlag(userservice.checkUser(user));
            //如果登陆成功，将该客户端加入已经连接成功的map集合里面 并且开启此用户的接受线程
            if(cmd.isFlag()) {
                // 将该线程加入连接成功的map集合
                SocketEntity socketEntity = new SocketEntity();
                socketEntity.setName(cmd.getSender());
                socketEntity.setSocket(socket);
                SocketList.addSocket(socketEntity);

                //从数据库获取其好友列表并将其好友列表发送至客户端
                //cmd.setData(userservice.getFriendsList(user));
                cmd.setResult("登录成功");
            } else {
                cmd.setResult("密码错误");
            }
        }

        //注册请求
		/*if("register".equals(cmd.getCmd())) {
			UserService userservice = new UserService();
			User user = (User)cmd.getData();
			cmd.setFlag(userservice.registerUser(user));
			//如果注册成功
			if(cmd.isFlag()) {
				SocketEntity socketEntity = new SocketEntity();
				socketEntity.setName(cmd.getSender());
				socketEntity.setSocket(socket);
				SocketList.addSocket(socketEntity);
				cmd.setData(userservice.getFriendsList(user));
				//刚注册的肯定没有好友
				cmd.setResult("注册成功");
			} else {
				cmd.setResult("注册失败可能该用户已存在");
			}
		}*/

        //修改资料请求 功能暂未实现
		/*if("changeInfo".equals(cmd.getCmd())) {
			UserService userservice = new UserService();
			User user = (User)cmd.getData();
			cmd.setFlag(userservice.changeInfo(user));
			if(cmd.isFlag()) {
				cmd.setResult("修改信息成功");
			} else {
				cmd.setResult("修改信息失败");
			}
		}*/

        //添加好友
		/*if("requeste_add_friend".equals(cmd.getCmd())) {
			//检查用户是否在线
			if(SocketList.getSocket(cmd.getReceiver()) != null) {
				cmd.setFlag(true);
				cmd.setResult("对方收到了您的好友请求");
			} else {
				cmd.setFlag(false);
				cmd.setResult("当前用户不在线或者改用户不存在");
			}
		}*/

        //同意添加好友请求
		/*if("accept_add_friend".equals(cmd.getCmd())) {
			UserService userservice = new UserService();
			cmd.setFlag(userservice.addFriend(cmd.getReceiver(), cmd.getSender()));
			if(cmd.isFlag()) {
				cmd.setResult("好友添加成功请重新登陆刷新");
			} else {
				cmd.setResult("服务器故障导致添加好友失败或者您们已经为好友");
			}
		}

		if("updatefriendlist".equals(cmd.getCmd())) {
			UserService userservice = new UserService();
			User user = (User)cmd.getData();
			user = userservice.getFriendsList(user);
			if(user.getFriendsNum() == 0) {
				cmd.setFlag(false);
			} else {
				cmd.setData(user);
			}
		}*/

        //拒绝添加好友
		/*if("refuse_to_add".equals(cmd.getCmd())) {
			//检查是否在线
			if(SocketList.getSocket(cmd.getReceiver()) != null) {
				cmd.setFlag(true);
				cmd.setResult("您被 " + cmd.getSender() +  " 拒绝了");
			} else {
				cmd.setFlag(false);
				cmd.setResult("对方不在线不知道你拒绝了他的好友请求");
			}
		}*/

        //发送消息指令
        if("message".equals(cmd.getCmd())) {
            //检查是否在线
            if(SocketList.getSocket(cmd.getReceiver()) != null) {
                cmd.setFlag(true);
            } else {
                cmd.setFlag(false);
                cmd.setResult("当前用户不在线");
            }
        }

        if("WordChat".equals(cmd.getCmd())) {
            cmd.setFlag(true);
        }

        if("logout".equals(cmd.getCmd())) {
            SocketList.getSocket(cmd.getSender());
        }

        return cmd;
    }
}
