/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author User
 */
public class CandiRegister extends Dbconnection{
    
    java.sql.Connection conn=Createconnection(); 
    
    public boolean insertCandiDetail(int UserId,int Electparty,String Seat,String ElectNo,String createdby,String createddate,String updatedby,String updateddate,String UserName, String Password, String Post)
    {
        boolean rst=false;
        
        try{
            String Query="INSERT INTO `electionsystemdb`.`candidatetble` "+
                         "(UserID,PoliticalPartyCode,ElectionNo,SeatName,Created By,CreateDate,UpdateDate)"+
                         " VALUES ((?,?,?,?,?,?,?)";
            PreparedStatement prestate=conn.prepareStatement(Query);
            prestate.setInt(1, UserId);
            prestate.setInt(2, Electparty);
            prestate.setString(3, Seat);
            prestate.setString(5, ElectNo);
            prestate.setString(4, createdby);
            prestate.setString(5, createddate);
            prestate.setString(6, updatedby);
            prestate.setString(7, updateddate);
            
            int out=prestate.executeUpdate();
                if(out>0)
                {
                    LoginDetails userlogin=new LoginDetails();
                    boolean loginrslt=userlogin.insertlogindetl(UserId,UserName,Password, Post);
                    if(loginrslt==true)
                    {
                        rst=true;
                    }else
                    {
                        rst=false;
                    }
                    return rst;
                }
            }
            catch(Exception ex)
            { 
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            return rst;
            }
            finally
            {
                return rst;
            }
        } 
    
     public ResultSet GetCandiProPicture() {
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
