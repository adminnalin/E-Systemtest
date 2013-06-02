/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class UserRegister extends Dbconnection {
    
     Connection conn=Createconnection(); 
    public ResultSet GetuserdetailsbyID(String UserCode, String jobtyp)
    {
       ResultSet rs=null;
        try
        {
            String slectqry="SELECT `sysusertbl`.`UserID`,`sysusertbl`.`UserType`"+
                            " FROM `electionsystemdb`.`sysusertbl` where  UserCode=? and UserType=?";
              
            PreparedStatement ps=con.prepareStatement(slectqry);
            ps.setString(1, UserCode);
            ps.setString(2, jobtyp);

            rs=ps.executeQuery();
            return  rs;
        }
        catch(Exception ex)
        {
            return rs;
        }
        
    }

    public boolean insertUserdetails(String userCode, String usertype, String fistname, String lstname, String gender, String birthdy, String mbno, String lndno, String nic, String email, byte[] image, String createdby, String createddate, String updatedby, String updateddate) {
        boolean flage=false;
        
        try
        {
           
            //SimpleDateFormat convertDate = new SimpleDateFormat("MMM dd yyyy");

            String query="INSERT INTO `electionsystemdb`.`sysusertbl` "+
                         " (UserCode,UserType,FirstName,LastName,Gender, DateofBirth,MobileNo,LandNo,NIC_No,Email,Image, CreatedBy,CreatedDate,UpdatedBy, UpdatedDate) "+ 
                         " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement prestate=conn.prepareStatement(query);
            prestate.setString(1, userCode);
            prestate.setString(2, usertype);
            prestate.setString(3, fistname);
            prestate.setString(4, lstname);
            prestate.setString(5, gender);
            prestate.setString(6, birthdy);
            prestate.setString(7, mbno);
            prestate.setString(8, lndno);
            prestate.setString(9, nic);
            prestate.setString(10,email);
            prestate.setBytes(11, image);
            prestate.setString(12, createdby);
            prestate.setString(13, createddate);
            prestate.setString(14, updatedby);
            prestate.setString(15, updateddate);
          
            int rslt=prestate.executeUpdate();
            if(rslt>0)
            {
                flage=true;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            return flage;
        }
        finally
        {
        return flage;
        }
    }
    
    public ResultSet GetUserProPicture() {
        try
        {
            ResultSet rs=null;
            String slectqry="SELECT Image from sysusertbl";
            java.sql.PreparedStatement ps=conn.prepareStatement(slectqry);
            rs = ps.executeQuery(slectqry);  
                        
            return rs;
        }
        catch(Exception ex){
        throw new UnsupportedOperationException("Not yet implemented");
        }
    }
    
}
