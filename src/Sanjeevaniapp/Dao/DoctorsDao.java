package Sanjeevaniapp.Dao;

import Sanjeevaniapp.pojo.DoctorPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sanjeevaniapp.dbutil.DBConnection;




public class DoctorsDao {
public static void updateName(String currName,String newName)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update doctors set doctor_name=? where doctor_name=?");
     ps.setString(1, newName);
     ps.setString(2, currName);
     ps.executeUpdate();
}
public static boolean addDoctor(DoctorPojo doc)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Insert into doctors values(?,?,?,?,?,?,?)");
     ps.setString(1,doc.getDoctorId());
     ps.setString(2, doc.getDoctorName());
     ps.setString(3,doc.getEmailId());
     ps.setString(4, doc.getContactNo());
     ps.setString(5, doc.getQualification());
     ps.setString(7, doc.getSpecialist());
     ps.setString(6, doc.getGender());
     return ps.executeUpdate()==1;
}
public static String getNewDocId()throws  SQLException{
   Connection conn=DBConnection.getConnection();
   Statement st=conn.createStatement();
   ResultSet rs=st.executeQuery("Select max(doctor_id) from doctors");
   rs.next();
   int docId=101;
   String id=rs.getString(1);
//   System.out.println("Seen"+id);
   if(id!=null){
       String num=id.substring(3);
//       System.out.println("Info"+num);
       docId=Integer.parseInt(num)+1;
    }
   return "DOC"+docId;
}

public static List<String> getAllDoctorId()throws SQLException
{
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select doctor_id from doctors");
    List<String>doctorList=new ArrayList<>();
    while(rs.next())
    {
        doctorList.add(rs.getString(1));
    }
    return doctorList;
}
public static boolean deleteDoctorById(String docId)throws SQLException{
     Connection conn=DBConnection.getConnection();
//     System.out.println(" get docId by DeleteDoctorById :"+docId);
     PreparedStatement ps=conn.prepareStatement("Select doctor_name from doctors where doctor_id=?");
     ps.setString(1, docId);
     ResultSet rs=ps.executeQuery();
     rs.next();
     String docName=rs.getString(1);
//     System.out.println("get docName :"+docName);
     UserDao.deleteUserByName(docName);
     ps=conn.prepareStatement("Delete from doctors where doctor_id=?");
     ps.setString(1, docId);
     return ps.executeUpdate()==1;
}
public static List<DoctorPojo> getAllDoctorDetails()throws SQLException{
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from doctors order by doctor_id");
    List<DoctorPojo>docList=new ArrayList<>();
    while(rs.next()){
    DoctorPojo doc=new DoctorPojo();
    doc.setDoctorName(rs.getString("doctor_name"));
    doc.setContactNo(rs.getString("contact_no"));
    doc.setDoctorId(rs.getString("doctor_id"));
    doc.setEmailId(rs.getString("email_id"));
    doc.setQualification(rs.getString("qualification"));
    doc.setGender(rs.getString("gender"));
    doc.setSpecialist(rs.getString("specialist"));
    docList.add(doc);
    
    }
    return docList;
} 
public static String getDoctorNameById(String docId)throws SQLException{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("Select doctor_name from doctors where doctor_id=?");
    ps.setString(1,docId);
    ResultSet rs=ps.executeQuery();
    rs.next();
    return rs.getString(1);
    
 } 
}

