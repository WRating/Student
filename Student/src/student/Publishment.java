package student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Publishment extends JFrame implements ActionListener {
    // �������
    JLabel jLStudentInfoTable = null;//ѧ����Ϣ��
    JLabel jLSelectQueryField = null;//ѡ���ѯ�ֶ�
    JLabel jLEqual = null;//=
    JLabel jLSNo = null;//��¼��
    JLabel jLSName = null;//ѧ��
    JLabel jLSSex = null;//�������
    JLabel jLSClass = null;//��¼ʱ��
    JLabel jLSDepartment = null;//�Ƿ���Ч
    JLabel jLSBrithday = null;//����

    JTextField jTFQueryField = null;//��ѯ�ֶ�
    JTextField jTFSNo = null;//��¼��
    JTextField jTFSName = null;//ѧ��
    JTextField jTFSSex = null;//�������
    JTextField jTFSClass = null;//��¼ʱ��
    JTextField jTFSDepartment = null;//�Ƿ���Ч
    JTextField jTFSBrithday = null;//����

    JButton jBQuery = null;//��ѯ
    JButton jBQueryAll = null;//��ѯ���м�¼
    JButton jBInsert = null;//����
    JButton jBUpdate = null;//����
    JButton jBReturn = null;//����

    //JComboBox jCBSelectQueryField = null;
    JComboBox<String> jCBSelectQueryField = null;//��ѯ�ֶ�
    JPanel jP1, jP2,jP3,jP4,jP5,jP6 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable studentJTable = null;
    JScrollPane studentJScrollPane = null;
    Vector studentVector = null;
    Vector titleVector = null;


    private static DbProcess dbProcess;
    String SelectQueryFieldStr = "ѧ��";

    // ���캯��
    public Publishment() throws SQLException {
        // �������
        jLStudentInfoTable = new JLabel("���������ѯ���޸�");
        jLSelectQueryField = new JLabel("ѡ���ѯ�ֶ�");
        jLEqual = new JLabel(" = ");
        jLSNo = new JLabel("��¼��");
        jLSName = new JLabel("ѧ��");
        jLSSex = new JLabel("�������");
        jLSClass = new JLabel("��¼ʱ��");
        jLSDepartment = new JLabel("�Ƿ���Ч");
        jLSBrithday= new JLabel("����");


        jTFQueryField = new JTextField(12);//��ѯ�ֶ�
        jTFSNo = new JTextField(10);//ѧ��
        jTFSName = new JTextField(10);//����
        jTFSSex = new JTextField(10);//�Ա�
        jTFSClass = new JTextField(10);//�༶���
        jTFSDepartment = new JTextField(10);//Ժϵ���
        jTFSBrithday = new JTextField(10);//����

        jBQuery = new JButton("��ѯ");
        jBQueryAll = new JButton("��ѯ���м�¼");
        jBInsert = new JButton("����");
        jBUpdate = new JButton("����");
        jBReturn = new JButton("����");
        // ���ü���
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);
        jBInsert.addActionListener(this);
        jBUpdate.addActionListener(this);
        jBReturn.addActionListener(this);

        jCBSelectQueryField = new JComboBox<String>();//��ѯ�ֶ�
        jCBSelectQueryField.addItem("��¼��");
        jCBSelectQueryField.addItem("ѧ��");
        jCBSelectQueryField.addItem("�Ա�");
        jCBSelectQueryField.addItem("�������");
        jCBSelectQueryField.addItem("��¼ʱ��");
        jCBSelectQueryField.addItem("����");
        jCBSelectQueryField.addItemListener(new ItemListener() {//�������¼�����
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectQueryFieldStr = (String) event.getItem();
                        System.out.println("ѡ�У�" + SelectQueryFieldStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("ȡ��ѡ�У�" + event.getItem());
                        break;
                }
            }
        });



        studentVector = new Vector();
        titleVector = new Vector();

        // �����ͷ
        titleVector.add("��¼��");
        titleVector.add("ѧ��");
        titleVector.add("�������");
        titleVector.add("��¼ʱ��");
        titleVector.add("�Ƿ���Ч");
        titleVector.add("����");
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(500,160));
        studentJScrollPane = new JScrollPane(studentJTable);
        //�ֱ�����ˮƽ�ʹ�ֱ�������Զ�����
        studentJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //Ϊ�����Ӽ�����
        studentJTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // �����λ��
                System.out.println("mouseClicked(). row = " + row);
                Vector v = new Vector();
                v = (Vector) studentVector.get(row);

                jTFSNo.setText((String) v.get(0));// ѧ��
                jTFSName.setText((String) v.get(1));// ����
                jTFSSex.setText((String) v.get(2));// �Ա�
                jTFSClass.setText((String) v.get(3));// �༶���
                jTFSDepartment.setText((String) v.get(4));// Ժϵ���
                jTFSBrithday.setText((String) v.get(5));// ����
            }
        });


        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP6 = new JPanel();
        jPTop = new JPanel();
        jPBottom = new JPanel();

        jP1.add(jLStudentInfoTable,BorderLayout.SOUTH);
        jP2.add(studentJScrollPane);

        jP3.add(jLSelectQueryField);
        jP3.add(jCBSelectQueryField);
        jP3.add(jLEqual);
        jP3.add(jTFQueryField);
        jP3.add(jBQuery);
        jP3.add(jBQueryAll);
        jP3.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP3.setPreferredSize(new Dimension(20,20));

        jP4.add(jLSNo);
        jP4.add(jTFSNo);
        jP4.add(jLSName);
        jP4.add(jTFSName);
        jP4.add(jLSSex);
        jP4.add(jTFSSex);
        jP4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP4.setPreferredSize(new Dimension(20,20));


        jP5.add(jLSClass);
        jP5.add(jTFSClass);
        jP5.add(jLSDepartment);
        jP5.add(jTFSDepartment);
        jP5.add(jLSBrithday);
        jP5.add(jTFSBrithday);
        jP5.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP5.setPreferredSize(new Dimension(20,20));

        jP6.add(jBInsert);
        jP6.add(jBUpdate);
        jP6.add(jBReturn);
        jP6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP6.setPreferredSize(new Dimension(20,20));

        jPTop.add(jP1);
        jPTop.add(jP2);

        jPBottom.setLayout(new GridLayout(4, 1));
        jPBottom.add(jP3);
        jPBottom.add(jP4);
        jPBottom.add(jP5);
        jPBottom.add(jP6);

        this.add("North", jPTop);
        this.add("South", jPBottom);

        this.setLayout(new GridLayout(2, 1));
        this.setTitle("ѧ������ϵͳ");
        this.setSize(550, 550);
        this.setLocation(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        dbProcess = new DbProcess();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("��ѯ")
                && !jTFQueryField.getText().isEmpty()){
            System.out.println("actionPerformed(). ��ѯ");
            String sQueryField = jTFQueryField.getText().trim();
            queryProcess(sQueryField);
            jTFQueryField.setText("");
        }else if(e.getActionCommand().equals("��ѯ���м�¼")) {
            System.out.println("actionPerformed(). ��ѯ���м�¼");
            queryAllProcess();
        }else if(e.getActionCommand().equals("����")
                && !jTFSNo.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSClass.getText().isEmpty()
                && !jTFSDepartment.getText().isEmpty()
                && !jTFSBrithday.getText().isEmpty()){
            System.out.println("actionPerformed(). ����");
            insertProcess();
        }else if(e.getActionCommand().equals("����")
                && !jTFSNo.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSClass.getText().isEmpty()
                && !jTFSDepartment.getText().isEmpty()
                && !jTFSBrithday.getText().isEmpty()){
            System.out.println("actionPerformed(). ����");
            updateProcess();
        }else if(e.getActionCommand().equals("����"))
        {
            new Teacher();
            this.dispose();
        }
    }

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        Publishment getcon = new  Publishment();
    }

    public void queryProcess(String sQueryField)
    {
        try{
            // ������ѯ����
            String sql = "select * from publishment where ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

            if(queryFieldStr.equals("ID")){//int ID
                sql = sql + queryFieldStr;
                sql = sql + " = " + sQueryField;
            }else{
                sql = sql + queryFieldStr;
                sql = sql + " = ";
                sql = sql + "'" + sQueryField + "';";
            }

            System.out.println("queryProcess(). sql = " + sql);

            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);

            // ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
            studentVector.clear();
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("ID"));
                v.add(rs.getString("StundetID"));
                v.add(rs.getString("Rec_time"));
                v.add(rs.getString("Enable"));
                v.add(rs.getString("Description"));
                studentVector.add(v);
            }

            studentJTable.updateUI();

            dbProcess.disconnect();
        }catch(SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryAllProcess()
    {
        try{
            // ������ѯ����
            String sql = "select * from publishment;";
            System.out.println("queryAllProcess(). sql = " + sql);

            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);

            // ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
            studentVector.clear();
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("ID"));
                v.add(rs.getString("StundetID"));
                v.add(rs.getString("Levels"));
                v.add(rs.getString("Rec_time"));
                v.add(rs.getString("Enable"));
                v.add(rs.getString("Description"));
                studentVector.add(v);
            }

            studentJTable.updateUI();

            dbProcess.disconnect();
        }catch(SQLException sqle){
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertProcess()
    {
        String ID = jTFSNo.getText().trim();
        String StundetID = jTFSName.getText().trim();
        String Levels = jTFSSex.getText().trim();
        String Rec_time = jTFSClass.getText().trim();
        String Enable = jTFSDepartment.getText().trim();
        String Description = jTFSBrithday.getText().trim();

        // ������������
        String sql = "insert into publishment values('";
        sql = sql + ID + "','";
        sql = sql + StundetID + "','";
        sql = sql + Levels + "','";
        sql = sql + Rec_time + "','";
        sql = sql + Enable + "','";
        sql = sql + Description + "');";

        System.out.println("insertProcess(). sql = " + sql);
        try{
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("insertProcess(). insert database failed.");
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }

    public void updateProcess()
    {
        String ID = jTFSNo.getText().trim();
        String StundetID = jTFSName.getText().trim();
        String Levels = jTFSSex.getText().trim();
        String Rec_time = jTFSClass.getText().trim();
        String Enable = jTFSDepartment.getText().trim();
        String Description = jTFSBrithday.getText().trim();

        // ������������
        String sql = "update publishment set StundetID = '";
        sql = sql + StundetID + "', Levels = '";
        sql = sql + Levels + "', Rec_time = '";
        sql = sql + Rec_time + "', Enable = '";
        sql = sql + Enable + "', Description = '";
        sql = sql + Description + "'";
        sql = sql + " WHERE ID = '" + ID + "';";
        System.out.println("updateProcess(). sql = " + sql);
        try{
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }


    public String jCBSelectQueryFieldTransfer(String InputStr)
    {
        String outputStr = "";
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

        if(InputStr.equals("��¼��")){
            outputStr = "ID";
        }else if(InputStr.equals("ѧ��")){
            outputStr = "StundetID";
        }else if(InputStr.equals("�������")){
            outputStr = "Levels";
        }else if(InputStr.equals("��¼ʱ��")){
            outputStr = "Rec_time";
        }else if(InputStr.equals("�Ƿ���Ч")){
            outputStr = "Enable";
        }else if(InputStr.equals("����")){
            outputStr = "Description";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}
