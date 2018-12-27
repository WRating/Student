package student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbProcess{
    Connection connection = null;
    ResultSet rs = null;
    private static DbProcess dbProcess;

    //mysql���ݿ�url
    String userMySql="root";
    String passwordMySql="123456";
    String urlMySql = "jdbc:mysql://localhost:3306/infodb?serverTimezone=UTC&user="
            +userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&useSSL=false";


    public DbProcess() throws SQLException {
        try {
            //mysql���ݿ�����������������
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("mysql���ݿ��������سɹ�");
            
        }
        catch(java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public  void connect(){
        try{
            //mysql���ݿ�
            connection = DriverManager.getConnection(urlMySql);
//            String sql="select * from wrtsbsb;";
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()){
//            System.out.println(rs.getString("wrtsb"));
//           }

            if(connection!=null){
                System.out.println("���ݿ����ӳɹ�");
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            if(connection != null){
                connection.close();
                connection = null;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public ResultSet executeQuery(String sql) {
        try {
            System.out.println("executeQuery(). sql = " + sql);

            PreparedStatement pstm = connection.prepareStatement(sql);
            // ִ�в�ѯ
            rs = pstm.executeQuery();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    //����
    //executeUpdate �ķ���ֵ��һ��������ָʾ��Ӱ��������������¼�������
    //executeUpdate����ִ�� INSERT��UPDATE �� DELETE ���
    //�Լ� SQL DDL�����ݶ������ԣ���䣬���� CREATE TABLE �� DROP TABLE��

    //ִ������ɾ�������ķ��� 
    public int executeUpdate(String sql) {
        int count = 0;
        connect();
        try {
            Statement stmt = connection.createStatement();
            count = stmt.executeUpdate(sql);
        }
        catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
        return count;
    }
    
    public static void main(String[] args) throws SQLException {
    	DbProcess m= new DbProcess();
    	m.connect();
    }
}