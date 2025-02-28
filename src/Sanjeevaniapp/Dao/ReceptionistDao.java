/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.Dao;

import Sanjeevaniapp.pojo.ReceptionistPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sanjeevaniapp.dbutil.DBConnection;

public class ReceptionistDao {
     public static void updateName(String currName,String newName)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update receptionists set receptionist_name=? where receptionist_name=?");
     ps.setString(1, newName);
     ps.setString(2, currName);
     ps.executeUpdate();
}
     
    public static String getNewRecId()throws  SQLException{
   Connection conn=DBConnection.getConnection();
   Statement st=conn.createStatement();
   ResultSet rs=st.executeQuery("Select max(receptionist_id) from receptionists");
   rs.next();
   int recId=101;
   String id=rs.getString(1);
   if(id!=null){
       String num=id.substring(3);
       recId=Integer.parseInt(num)+1;
    }
   return "REC"+recId;
}      

  public static boolean addReceptionist(ReceptionistPojo rec)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Insert into receptionists values(?,?,?)");
     ps.setString(1,rec.getReceptionistId());
     ps.setString(2,rec.getReceptionistName());
     ps.setString(3,rec.getGender());
     return ps.executeUpdate()==1;
}
  public static List<ReceptionistPojo> getAllReceptionistDetails()throws SQLException{
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from receptionists order by receptionist_id");
    List<ReceptionistPojo>recList=new ArrayList<>();
    while(rs.next()){
    ReceptionistPojo recep=new ReceptionistPojo();
    recep.setReceptionistId(rs.getString(1));
    recep.setReceptionistName(rs.getString(2));
    recep.setGender(rs.getString(3));
    recList.add(recep);
    
    }
    return recList;
    
} 
  public static boolean deleteReceptionistById(String RECId)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Select receptionist_name from receptionists where receptionist_id=?");
     ps.setString(1, RECId);
     ResultSet rs=ps.executeQuery();
     rs.next();
     String ReceptionName=rs.getString(1);
     UserDao.deleteUserByName(ReceptionName);
     ps=conn.prepareStatement("Delete from receptionists where receptionist_id=?");
     ps.setString(1, RECId);
     return ps.executeUpdate()==1;
}
  
  public static List<String> getAllReceptionistId()throws SQLException
{
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select receptionist_id from receptionists");
    List<String>receptionistList=new ArrayList<>();
    while(rs.next())
    {
        receptionistList.add(rs.getString(1));
    }
    return receptionistList;
}

  public static List<String> getAllreceptionistId()throws SQLException{
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select receptionist_id from receptionists");
    List<String> recepIdList=new ArrayList<>();
    while(rs.next()){
        recepIdList.add(rs.getString(1));
    }
    return recepIdList;
}
  
  public static ReceptionistPojo getReceptionistDetails(String recepId)throws SQLException{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("Select * from receptionists where receptionist_id=?");
    ps.setString(1,recepId);
    ResultSet rs=ps.executeQuery();
    rs.next();
    ReceptionistPojo recep = new ReceptionistPojo();
    recep.setReceptionistId(rs.getString(1));
    recep.setReceptionistName(rs.getString(2));
    recep.setGender(rs.getString(3));
    return recep;
   
   
  }
  
 public static boolean updateReceptionist(ReceptionistPojo recep)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update receptionists set gender=? where receptionist_id=?");
     ps.setString(1, recep.getGender());
     ps.setString(2, recep.getReceptionistId());
     return ps.executeUpdate()==1;
 }
}

  
