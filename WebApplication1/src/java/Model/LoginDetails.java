/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author User
 */
public class LoginDetails extends Dbconnection {
    
    Connection con=Createconnection();
    
    public boolean insertlogindetl(int UserId,String UserName,String Password, String Post)
    {
       boolean flag=false;
       try
        {
            String query="insert into logindetails (UserID,UserName,Password,UserType) values (?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, UserId);
            ps.setString(2, UserName); 
            ps.setString(3, Password);
            ps.setString(4, Post); 

            int rslt=ps.executeUpdate();
            if(rslt>0)
            {
                flag=true;
            }
            return flag;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            return flag;
        }
       finally
       {
           return flag;
       }
    }
}
