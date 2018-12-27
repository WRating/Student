package student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;


//1.定义Login类，
public class Login {

    public static void main(String[] args) {
        JFrame f=new JFrame("学生管理系统登录界面");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(500,500);
        f.setLocation(300,100);
        JLabel title1=new JLabel("欢迎来到");
        JLabel title2=new JLabel("学生信息管理系统");
        JLabel title3=new JLabel("您的身份是：");
        title1.setFont(new Font("微软雅黑",Font.BOLD, 30));
        title2.setFont(new Font("微软雅黑",Font.BOLD, 30));
        title1.setBounds(180,20,300,50);
        title2.setBounds(120,80,300,60);
        title3.setBounds(100,160,100,40);

        //选择该用户是老师还是学生，如果是老师，进入老师界面，学生进入学生界面
        JButton n1=new JButton("老师");
        JButton n2=new JButton("学生");
        n1.setBounds(160,220,70,30);
        n2.setBounds(240,220,70,30);

        f.add(title1);
        f.add(title2);
        f.add(title3);
        f.add(n1);
        f.add(n2);
        f.setVisible(true);

        n1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1=new JFrame("老师登录界面");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setLayout(null);
                frame1.setSize(500,500);
                frame1.setLocation(300,100);
                JLabel title=new JLabel("欢迎登录学生信息管理系统");
                title.setBounds(60,20,400,50);
                title.setFont(new Font("微软雅黑",Font.BOLD, 30));

                JLabel t1=new JLabel("用户名");
                JTextField t2=new JTextField(20);
                t1.setBounds(90,150,70,40);
                t2.setBounds(170,150,200,40);
                JLabel t3=new JLabel("密码");
                JPasswordField t4=new JPasswordField(20);
                t3.setBounds(90,210,70,40);
                t4.setBounds(170,210,200,40);
                JButton bt1=new JButton("登录");
                JButton bt2=new JButton("取消");
                bt1.setBounds(160,300,70,30);
                bt2.setBounds(240,300,70,30);

                frame1.add(t1);
                frame1.add(t2);
                frame1.add(t3);
                frame1.add(t4);
                frame1.add(bt1);
                frame1.add(bt2);
                frame1.add(title);
                frame1.setVisible(true);
                f.dispose();

                //点击登录按钮判断用户名密码是否正确，如正确进入下一页面
                bt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //如果选择了老师，判断账号密码是否正确，正确，即进入老师界面，错误，输出对不起密码错误，返回上一界面
                        String admin = t2.getText();
                        char[] password = t4.getPassword();
                        String str = String.valueOf(password); //将char数组转化为string类型
                        if (admin.equals("teacher") && str.equals("123456")) {
                            JOptionPane.showMessageDialog(null, "登陆成功！");
                            //this.setVisible(false);
                            new Teacher();
                        } else if (admin.equals("") || str.equals("")) {
                            JOptionPane.showMessageDialog(null, "请输入用户名或密码！");
                        } else {
                            JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                        }
                    }

                });

                //点击取消按钮之后就进入初始界面
                bt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.setVisible(true);
                        frame1.dispose();
                    }
                });
            }
        });

        n2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2=new JFrame("学生登录界面");
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.setLayout(null);
                frame2.setSize(500,500);
                frame2.setLocation(300,100);
                JLabel title=new JLabel("欢迎登录学生信息管理系统");
                title.setBounds(60,20,400,50);
                title.setFont(new Font("微软雅黑",Font.BOLD, 30));

                JLabel t1=new JLabel("用户名");
                JTextField t2=new JTextField(20);
                t1.setBounds(90,150,70,40);
                t2.setBounds(170,150,200,40);
                JLabel t3=new JLabel("密码");
                JPasswordField t4=new JPasswordField(20);
                t3.setBounds(90,210,70,40);
                t4.setBounds(170,210,200,40);
                JButton bt1=new JButton("登录");
                JButton bt2=new JButton("取消");
                bt1.setBounds(160,300,70,30);
                bt2.setBounds(240,300,70,30);

                frame2.add(t1);
                frame2.add(t2);
                frame2.add(t3);
                frame2.add(t4);
                frame2.add(bt1);
                frame2.add(bt2);
                frame2.add(title);
                frame2.setVisible(true);
                f.dispose();

                //点击登录按钮判断用户名密码是否正确，如正确进入下一页面
                bt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //如果选择了学生，判断账号密码是否正确，正确即进入老师界面，错误，输出对不起密码错误，返回上一界面

                        String admin = t2.getText();
                        char[] password = t4.getPassword();
                        String str = String.valueOf(password); //将char数组转化为string类型
                        if (admin.equals("student") && str.equals("123456")) {
                            JOptionPane.showMessageDialog(null, "登陆成功！");
                            //this.setVisible(false);
                            try {
								new Student();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                        } else if (admin.equals("") || str.equals("")) {
                            JOptionPane.showMessageDialog(null, "请输入用户名或密码！");
                        } else {
                            JOptionPane.showMessageDialog(null, "用户名或密码错误！");
                        }
                    }

                });

                //点击取消按钮之后就就进入初始界面
                bt2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.setVisible(true);
                        frame2.dispose();
                    }
                });
            }
        });
    }
}


