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

public class DatabaseCourseDesign extends JFrame implements ActionListener {
    // 定义组件
    JLabel jLStudentInfoTable = null;//学生信息表
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLEqual = null;//=
    JLabel jLSNo = null;//学号
    JLabel jLSName = null;//姓名
    JLabel jLSSex = null;//性别
    JLabel jLSClass = null;//班级编号
    JLabel jLSDepartment = null;//所属院系编号
    JLabel jLSBrithday = null;//生日
    JLabel jLSNative_place = null;//籍贯

    JTextField jTFQueryField = null;//查询字段
    JTextField jTFSNo = null;//学号
    JTextField jTFSName = null;//姓名
    JTextField jTFSSex = null;//性别
    JTextField jTFSClass = null;//班级编号
    JTextField jTFSDepartment = null;//所属院系编号
    JTextField jTFSBrithday = null;//生日
    JTextField jTFSNative_place = null;//籍贯

    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBInsert = null;//插入
    JButton jBUpdate = null;//更新
    JButton jBDeleteCurrentRecord = null;//删除当前记录
    JButton jBDeleteAllRecords = null;//删除所有记录
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
    public DatabaseCourseDesign() throws SQLException {
        // 创建组件
        jLStudentInfoTable = new JLabel("学生信息表");
        jLSelectQueryField = new JLabel("选择查询字段");
        jLEqual = new JLabel(" = ");
        jLSNo = new JLabel("学号");
        jLSName = new JLabel("姓名");
        jLSSex = new JLabel("性别");
        jLSClass = new JLabel("班级编号");
        jLSDepartment = new JLabel("院系编号");
        jLSBrithday= new JLabel("生日");
        jLSNative_place= new JLabel("籍贯");


        jTFQueryField = new JTextField(12);//查询字段
        jTFSNo = new JTextField(10);//学号
        jTFSName = new JTextField(10);//姓名
        jTFSSex = new JTextField(10);//性别
        jTFSClass = new JTextField(10);//班级编号
        jTFSDepartment = new JTextField(10);//院系编号
        jTFSBrithday = new JTextField(10);//生日
        jTFSNative_place = new JTextField(10);//籍贯

        jBQuery = new JButton("查询");
        jBQueryAll = new JButton("查询所有记录");
        jBInsert = new JButton("插入");
        jBUpdate = new JButton("更新");
        jBDeleteCurrentRecord = new JButton("删除当前记录");
        jBDeleteAllRecords = new JButton("删除所有记录");
        jBReturn = new JButton("返回");
        // 设置监听
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);
        jBInsert.addActionListener(this);
        jBUpdate.addActionListener(this);
        jBDeleteCurrentRecord.addActionListener(this);
        jBDeleteAllRecords.addActionListener(this);
        jBReturn.addActionListener(this);

        jCBSelectQueryField = new JComboBox<String>();//查询字段
        jCBSelectQueryField.addItem("学号");
        jCBSelectQueryField.addItem("姓名");
        jCBSelectQueryField.addItem("性别");
        jCBSelectQueryField.addItem("班级编号");
        jCBSelectQueryField.addItem("院系编号");
        jCBSelectQueryField.addItem("生日");
        jCBSelectQueryField.addItem("籍贯");
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
        titleVector.add("学号");
        titleVector.add("姓名");
        titleVector.add("性别");
        titleVector.add("班级编号");
        titleVector.add("院系编号");
        titleVector.add("生日");
        titleVector.add("籍贯");
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
                jTFSNative_place.setText((String) v.get(6));//籍贯
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
        jP4.add(jLSBrithday);
        jP4.add(jTFSBrithday);
        jP4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP4.setPreferredSize(new Dimension(20,20));

        jP5.add(jLSClass);
        jP5.add(jTFSClass);
        jP5.add(jLSDepartment);
        jP5.add(jTFSDepartment);
        jP5.add(jLSNative_place);
        jP5.add(jTFSNative_place);
        jP5.setLayout(new FlowLayout(FlowLayout.LEFT));
        jP5.setPreferredSize(new Dimension(20,20));

        jP6.add(jBInsert);
        jP6.add(jBUpdate);
        jP6.add(jBDeleteCurrentRecord);
        jP6.add(jBDeleteAllRecords);
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
                && !jTFSBrithday.getText().isEmpty()
                && !jTFSNative_place.getText().isEmpty()){
            System.out.println("actionPerformed(). 插入");
            insertProcess();
        }else if(e.getActionCommand().equals("更新")
                && !jTFSNo.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSClass.getText().isEmpty()
                && !jTFSDepartment.getText().isEmpty()
                && !jTFSBrithday.getText().isEmpty()
                && !jTFSNative_place.getText().isEmpty()){
            System.out.println("actionPerformed(). 更新");
            updateProcess();
        }else if(e.getActionCommand().equals("删除当前记录")){
            System.out.println("actionPerformed(). 删除当前记录");
            deleteCurrentRecordProcess();
        }else if(e.getActionCommand().equals("删除所有记录")){
            System.out.println("actionPerformed(). 删除所有记录");
            deleteAllRecordsProcess();
        }else if(e.getActionCommand().equals("返回"))
        {
            new Teacher();
            this.dispose();
        }
    }

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        DatabaseCourseDesign getcon = new  DatabaseCourseDesign();
    }

    public void queryProcess(String sQueryField)
    {
        try{
            // 建立查询条件
            String sql = "select * from student where ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

            if(queryFieldStr.equals("StudentID")){//int sNo
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
                v.add(rs.getString("StudentID"));
                v.add(rs.getString("Name"));
                v.add(rs.getString("Sex"));
                v.add(rs.getString("Class"));
                v.add(rs.getString("Department"));
                v.add(rs.getString("Brithday"));
                v.add(rs.getString("Native_place"));
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
            String sql = "select * from student;";
            System.out.println("queryAllProcess(). sql = " + sql);

            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);

            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            studentVector.clear();
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("StudentID"));
                v.add(rs.getString("Name"));
                v.add(rs.getString("Sex"));
                v.add(rs.getString("Class"));
                v.add(rs.getString("Department"));
                v.add(rs.getString("Brithday"));
                v.add(rs.getString("Native_place"));
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
        String StudentID = jTFSNo.getText().trim();
        String Name = jTFSName.getText().trim();
        String Sex = jTFSSex.getText().trim();
        String Class = jTFSClass.getText().trim();
        String Department = jTFSDepartment.getText().trim();
        String Brithday = jTFSBrithday.getText().trim();
        String Native_place = jTFSNative_place.getText().trim();

        // 建立插入条件
        String sql = "insert into student values('";
        sql = sql + StudentID + "','";
        sql = sql + Name + "','";
        sql = sql + Sex + "','";
        sql = sql + Class + "','";
        sql = sql + Department + "','";
        sql = sql + Brithday + "','";
        sql = sql + Native_place + "');";

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
        String StudentID = jTFSNo.getText().trim();
        String Name = jTFSName.getText().trim();
        String Sex = jTFSSex.getText().trim();
        String Class = jTFSClass.getText().trim();
        String Department = jTFSDepartment.getText().trim();
        String Brithday = jTFSBrithday.getText().trim();
        String Native_place = jTFSNative_place.getText().trim();

        // 建立更新条件
        String sql = "update student set Name = '";
        sql = sql + Name + "', Sex = '";
        sql = sql + Sex + "', Class = '";
        sql = sql + Class + "', Department = '";
        sql = sql + Department + "', Brithday = '";
        sql = sql + Brithday + "', Native_place = '";
        sql = sql + Native_place + "'";
        sql = sql + " WHERE StudentID = '" + StudentID + "';";
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

    public void deleteCurrentRecordProcess()
    {
        String StudentID = jTFSNo.getText().trim();

        // 建立删除条件
        String sql = "delete from student where StudentID = '" + StudentID + "';";
        System.out.println("deleteCurrentRecordProcess(). sql = " + sql);
        try{
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("deleteCurrentRecordProcess(). delete database failed.");
            }
        }catch(Exception e){
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }

    public void deleteAllRecordsProcess()
    {
        // 建立删除条件
        String sql = "delete from student;";
        System.out.println("deleteAllRecordsProcess(). sql = " + sql);
        try{
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("deleteAllRecordsProcess(). delete database failed.");
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

        if(InputStr.equals("学号")){
            outputStr = "StudentID";
        }else if(InputStr.equals("姓名")){
            outputStr = "Name";
        }else if(InputStr.equals("性别")){
            outputStr = "Sex";
        }else if(InputStr.equals("班级编号")){
            outputStr = "Class";
        }else if(InputStr.equals("院系编号")){
            outputStr = "Department";
        }else if(InputStr.equals("生日")){
            outputStr = "Brithday";
        }else if(InputStr.equals("籍贯")){
            outputStr = "Native_place";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}
