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
    // 定义组件
    JLabel jLStudentInfoTable = null;//学生信息表
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLEqual = null;//=
    JLabel jLSNo = null;//记录号
    JLabel jLSName = null;//学号
    JLabel jLSSex = null;//级别代码
    JLabel jLSClass = null;//记录时间
    JLabel jLSDepartment = null;//是否生效
    JLabel jLSBrithday = null;//描述

    JTextField jTFQueryField = null;//查询字段
    JTextField jTFSNo = null;//记录号
    JTextField jTFSName = null;//学号
    JTextField jTFSSex = null;//级别代码
    JTextField jTFSClass = null;//记录时间
    JTextField jTFSDepartment = null;//是否生效
    JTextField jTFSBrithday = null;//描述

    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBInsert = null;//插入
    JButton jBUpdate = null;//更新
    JButton jBReturn = null;//返回

    //JComboBox jCBSelectQueryField = null;
    JComboBox<String> jCBSelectQueryField = null;//查询字段
    JPanel jP1, jP2,jP3,jP4,jP5,jP6 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable studentJTable = null;
    JScrollPane studentJScrollPane = null;
    Vector studentVector = null;
    Vector titleVector = null;


    private static DbProcess dbProcess;
    String SelectQueryFieldStr = "学号";

    // 构造函数
    public Publishment() throws SQLException {
        // 创建组件
        jLStudentInfoTable = new JLabel("处罚情况查询与修改");
        jLSelectQueryField = new JLabel("选择查询字段");
        jLEqual = new JLabel(" = ");
        jLSNo = new JLabel("记录号");
        jLSName = new JLabel("学号");
        jLSSex = new JLabel("级别代码");
        jLSClass = new JLabel("记录时间");
        jLSDepartment = new JLabel("是否生效");
        jLSBrithday= new JLabel("描述");


        jTFQueryField = new JTextField(12);//查询字段
        jTFSNo = new JTextField(10);//学号
        jTFSName = new JTextField(10);//姓名
        jTFSSex = new JTextField(10);//性别
        jTFSClass = new JTextField(10);//班级编号
        jTFSDepartment = new JTextField(10);//院系编号
        jTFSBrithday = new JTextField(10);//生日

        jBQuery = new JButton("查询");
        jBQueryAll = new JButton("查询所有记录");
        jBInsert = new JButton("插入");
        jBUpdate = new JButton("更新");
        jBReturn = new JButton("返回");
        // 设置监听
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);
        jBInsert.addActionListener(this);
        jBUpdate.addActionListener(this);
        jBReturn.addActionListener(this);

        jCBSelectQueryField = new JComboBox<String>();//查询字段
        jCBSelectQueryField.addItem("记录号");
        jCBSelectQueryField.addItem("学号");
        jCBSelectQueryField.addItem("性别");
        jCBSelectQueryField.addItem("级别代码");
        jCBSelectQueryField.addItem("记录时间");
        jCBSelectQueryField.addItem("描述");
        jCBSelectQueryField.addItemListener(new ItemListener() {//下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectQueryFieldStr = (String) event.getItem();
                        System.out.println("选中：" + SelectQueryFieldStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });



        studentVector = new Vector();
        titleVector = new Vector();

        // 定义表头
        titleVector.add("记录号");
        titleVector.add("学号");
        titleVector.add("级别代码");
        titleVector.add("记录时间");
        titleVector.add("是否生效");
        titleVector.add("描述");
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(500,160));
        studentJScrollPane = new JScrollPane(studentJTable);
        //分别设置水平和垂直滚动条自动出现
        studentJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //为表格添加监听器
        studentJTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
                System.out.println("mouseClicked(). row = " + row);
                Vector v = new Vector();
                v = (Vector) studentVector.get(row);

                jTFSNo.setText((String) v.get(0));// 学号
                jTFSName.setText((String) v.get(1));// 姓名
                jTFSSex.setText((String) v.get(2));// 性别
                jTFSClass.setText((String) v.get(3));// 班级编号
                jTFSDepartment.setText((String) v.get(4));// 院系编号
                jTFSBrithday.setText((String) v.get(5));// 生日
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
        this.setTitle("学生管理系统");
        this.setSize(550, 550);
        this.setLocation(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        dbProcess = new DbProcess();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查询")
                && !jTFQueryField.getText().isEmpty()){
            System.out.println("actionPerformed(). 查询");
            String sQueryField = jTFQueryField.getText().trim();
            queryProcess(sQueryField);
            jTFQueryField.setText("");
        }else if(e.getActionCommand().equals("查询所有记录")) {
            System.out.println("actionPerformed(). 查询所有记录");
            queryAllProcess();
        }else if(e.getActionCommand().equals("插入")
                && !jTFSNo.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSClass.getText().isEmpty()
                && !jTFSDepartment.getText().isEmpty()
                && !jTFSBrithday.getText().isEmpty()){
            System.out.println("actionPerformed(). 插入");
            insertProcess();
        }else if(e.getActionCommand().equals("更新")
                && !jTFSNo.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSClass.getText().isEmpty()
                && !jTFSDepartment.getText().isEmpty()
                && !jTFSBrithday.getText().isEmpty()){
            System.out.println("actionPerformed(). 更新");
            updateProcess();
        }else if(e.getActionCommand().equals("返回"))
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
            // 建立查询条件
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

            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryAllProcess()
    {
        try{
            // 建立查询条件
            String sql = "select * from publishment;";
            System.out.println("queryAllProcess(). sql = " + sql);

            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);

            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
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
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
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

        // 建立插入条件
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
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
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

        // 建立更新条件
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
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }


    public String jCBSelectQueryFieldTransfer(String InputStr)
    {
        String outputStr = "";
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

        if(InputStr.equals("记录号")){
            outputStr = "ID";
        }else if(InputStr.equals("学号")){
            outputStr = "StundetID";
        }else if(InputStr.equals("级别代码")){
            outputStr = "Levels";
        }else if(InputStr.equals("记录时间")){
            outputStr = "Rec_time";
        }else if(InputStr.equals("是否生效")){
            outputStr = "Enable";
        }else if(InputStr.equals("描述")){
            outputStr = "Description";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}
