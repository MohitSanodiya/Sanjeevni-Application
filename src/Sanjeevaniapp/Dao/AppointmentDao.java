
package Sanjeevaniapp.Dao;

import Sanjeevaniapp.pojo.AppointmentPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sanjeevaniapp.dbutil.DBConnection;


public class AppointmentDao {
 public static boolean addAppointment(AppointmentPojo appt)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Insert into appointments values(?,?,?,?,?,?,?)");
     ps.setString(1,appt.getPatientId());
     ps.setString(2, appt.getPatientName());
     ps.setString(3,appt.getStatus());
     ps.setString(4, appt.getOpd());
     ps.setString(5, appt.getAppointmentDate());
     ps.setString(6,appt.getDoctorName());
     ps.setString(7,appt.getMobileNo());
     return ps.executeUpdate()==1;
}
 public static List<AppointmentPojo> getAllAppointmentsByDoctorName(String doctorName)throws SQLException{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("Select * from appointments where doctor_name=? and status='REQUEST' order by patient_id");
    ps.setString(1,doctorName);
    ResultSet rs=ps.executeQuery();
    List<AppointmentPojo>appList=new ArrayList<>();
    while(rs.next()){
    AppointmentPojo app=new AppointmentPojo();
    app.setPatientId(rs.getString("patient_id"));
    app.setPatientName(rs.getString("patient_name"));
    app.setOpd(rs.getString("opd"));
    app.setAppointmentDate(rs.getString("date_time"));
    app.setStatus(rs.getString("status"));
    app.setDoctorName(rs.getString("doctor_name"));
    app.setMobileNo(rs.getString("mobile_no"));
    appList.add(app);   
    
    }
    return appList;
} 
 public static boolean updateStatus(AppointmentPojo app)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update appointments set status=?,date_time=? where patient_id=?" );
     ps.setString(1, app.getStatus());
     ps.setString(2, app.getAppointmentDate());
     ps.setString(3,app.getPatientId());
     return ps.executeUpdate()==1;
    
     
 }
public static List<AppointmentPojo> getAllConfirmAppointmentsDetails()throws SQLException{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("Select * from appointments where status='CONFIRM' order by patient_id");
    ResultSet rs=ps.executeQuery();
    List<AppointmentPojo>appList=new ArrayList<>();
    while(rs.next()){
    AppointmentPojo app=new AppointmentPojo();
    app.setPatientId(rs.getString("patient_id"));
    app.setPatientName(rs.getString("patient_name"));
    app.setOpd(rs.getString("opd"));
    app.setAppointmentDate(rs.getString("DATE_TIME"));
    app.setStatus(rs.getString("STATUS"));
    app.setDoctorName(rs.getString("DOCTOR_NAME"));
    app.setMobileNo(rs.getString("MOBILE_NO"));
    appList.add(app);
}
    return appList;
}

   public static void deleteAppointmentByName(String name)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("delete from appointments where patient_id=?");
     ps.setString(1, name);
     ps.executeUpdate();
}
   public static boolean updateAppointment(AppointmentPojo pat)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update appointments set patient_name=?,mobile_no=?,opd=? where patient_id=?");
     ps.setString(1, pat.getPatientName());
     ps.setString(2, pat.getMobileNo());
     ps.setString(3, pat.getOpd());
     ps.setString(4, pat.getPatientId());
     return ps.executeUpdate()==1;
  }
 
}
