package student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;


//1.����Login�࣬
public class Login {

    public static void main(String[] args) {
        JFrame f=new JFrame("ѧ������ϵͳ��¼����");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(500,500);
        f.setLocation(300,100);
        JLabel title1=new JLabel("��ӭ����");
        JLabel title2=new JLabel("ѧ����Ϣ����ϵͳ");
        JLabel title3=new JLabel("��������ǣ�");
        title1.setFont(new Font("΢���ź�",Font.BOLD, 30));
        title2.setFont(new Font("΢���ź�",Font.BOLD, 30));
        title1.setBounds(180,20,300,50);
        title2.setBounds(120,80,300,60);
        title3.setBounds(100,160,100,40);

        //ѡ����û�����ʦ����ѧ�����������ʦ��������ʦ���棬ѧ������ѧ������
        JButton n1=new JButton("��ʦ");
        JButton n2=new JButton("ѧ��");
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
                JFrame frame1=new JFrame("��ʦ��¼����");
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame1.setLayout(null);
                frame1.setSize(500,500);
                frame1.setLocation(300,100);
                JLabel title=new JLabel("��ӭ��¼ѧ����Ϣ����ϵͳ");
                title.setBounds(60,20,400,50);
                title.setFont(new Font("΢���ź�",Font.BOLD, 30));

                JLabel t1=new JLabel("�û���");
                JTextField t2=new JTextField(20);
                t1.setBounds(90,150,70,40);
                t2.setBounds(170,150,200,40);
                JLabel t3=new JLabel("����");
                JPasswordField t4=new JPasswordField(20);
                t3.setBounds(90,210,70,40);
                t4.setBounds(170,210,200,40);
                JButton bt1=new JButton("��¼");
                JButton bt2=new JButton("ȡ��");
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

                //�����¼��ť�ж��û��������Ƿ���ȷ������ȷ������һҳ��
                bt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //���ѡ������ʦ���ж��˺������Ƿ���ȷ����ȷ����������ʦ���棬��������Բ���������󣬷�����һ����
                        String admin = t2.getText();
                        char[] password = t4.getPassword();
                        String str = String.valueOf(password); //��char����ת��Ϊstring����
                        if (admin.equals("teacher") && str.equals("123456")) {
                            JOptionPane.showMessageDialog(null, "��½�ɹ���");
                            //this.setVisible(false);
                            new Teacher();
                        } else if (admin.equals("") || str.equals("")) {
                            JOptionPane.showMessageDialog(null, "�������û��������룡");
                        } else {
                            JOptionPane.showMessageDialog(null, "�û������������");
                        }
                    }

                });

                //���ȡ����ť֮��ͽ����ʼ����
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
                JFrame frame2=new JFrame("ѧ����¼����");
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.setLayout(null);
                frame2.setSize(500,500);
                frame2.setLocation(300,100);
                JLabel title=new JLabel("��ӭ��¼ѧ����Ϣ����ϵͳ");
                title.setBounds(60,20,400,50);
                title.setFont(new Font("΢���ź�",Font.BOLD, 30));

                JLabel t1=new JLabel("�û���");
                JTextField t2=new JTextField(20);
                t1.setBounds(90,150,70,40);
                t2.setBounds(170,150,200,40);
                JLabel t3=new JLabel("����");
                JPasswordField t4=new JPasswordField(20);
                t3.setBounds(90,210,70,40);
                t4.setBounds(170,210,200,40);
                JButton bt1=new JButton("��¼");
                JButton bt2=new JButton("ȡ��");
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

                //�����¼��ť�ж��û��������Ƿ���ȷ������ȷ������һҳ��
                bt1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //���ѡ����ѧ�����ж��˺������Ƿ���ȷ����ȷ��������ʦ���棬��������Բ���������󣬷�����һ����

                        String admin = t2.getText();
                        char[] password = t4.getPassword();
                        String str = String.valueOf(password); //��char����ת��Ϊstring����
                        if (admin.equals("student") && str.equals("123456")) {
                            JOptionPane.showMessageDialog(null, "��½�ɹ���");
                            //this.setVisible(false);
                            try {
								new Student();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                        } else if (admin.equals("") || str.equals("")) {
                            JOptionPane.showMessageDialog(null, "�������û��������룡");
                        } else {
                            JOptionPane.showMessageDialog(null, "�û������������");
                        }
                    }

                });

                //���ȡ����ť֮��;ͽ����ʼ����
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


