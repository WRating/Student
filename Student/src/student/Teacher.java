package student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Teacher extends Login {

    public Teacher(){
        JFrame f1=new JFrame("��ʦ����");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setLayout(null);
        f1.setSize(500,500);
        f1.setLocation(300,100);
        JButton btn2=new JButton("ѧ������������");
        JButton btn3=new JButton("�����������");
        JButton btn4=new JButton("�����������");
        JButton btn5=new JButton("ѧ�����������ѯ���޸�");
        btn2.setBounds(160,100,200,50);
        btn3.setBounds(160,160,200,50);
        btn4.setBounds(160,220,200,50);
        btn5.setBounds(160,280,200,50);
        f1.add(btn2);
        f1.add(btn3);
        f1.add(btn4);
        f1.add(btn5);
        f1.setVisible(true);


        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					new Change1();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					new Reward();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					new Publishment();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
                
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					DatabaseCourseDesign m=new DatabaseCourseDesign();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//ѧ����Ϣ�޸����ѯ����
                f1.dispose();
            }
        });
        

    }

}
