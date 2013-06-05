/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class AdminRegister extends UserRegister {
    
    Connection conn=Createconnection(); 
   
    public boolean insertAdminDetail(int UserId,String location,String createdby,String createddate,String updatedby,String updateddate,String UserName, String Password, String Post)
    {
         boolean flage=false;
         try
         {
            String query="INSERT INTO `electionsystemdb`.`admintbl` "+
                         " (UserID,AssignArea, CreatedBy,CreatedDate,UpdatedBy, UpdatedDate) "+ 
                         " values (?,?,?,?,?,?)";
            
            PreparedStatement prestate=conn.prepareStatement(query);
            prestate.setInt(1, UserId);
            prestate.setString(2, location);
            prestate.setString(3, createdby);
            prestate.setString(4, createddate);
            prestate.setString(5, updatedby);
            prestate.setString(6, updateddate);
            
            int rslt=prestate.executeUpdate();
            if(rslt>0)
            {
                LoginDetails userlogin=new LoginDetails();
                boolean loginrslt=userlogin.insertlogindetl(UserId,UserName,Password, Post);
                if(loginrslt==true)
                {
                    flage=true;
                }else
                {
                    flage=false;
                }
                return flage;
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
    
    public ResultSet GetAdminProPicture() {
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
