/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.*;
import javax.crypto.*;
import java.security.*;
import org.apache.catalina.util.Base64;

/**
 *
 * @author User
 */
public class Testmd extends Dbconnection{
    
  java.sql.Connection conn=Createconnection();
  private static String algorithm = "DESede";
  private static Key key = null;
  private static Cipher cipher = null;
  
public void insertimage(byte[] b) {
        boolean f=false;
    
     try
     {
        String sqlImageInsertDatabase="insert into upload_image (bImage) values(?)"; 
       
        java.sql.PreparedStatement prestate=conn.prepareStatement(sqlImageInsertDatabase);
        prestate.setBytes(1, b);
        prestate.executeUpdate(); 
      
     }
     catch(Exception ex)
     {
        throw new UnsupportedOperationException("Not yet implemented");
     }
    }

public ResultSet getimages() {
        try
        {
            ResultSet rs=null;
            String slectqry="SELECT bImage from upload_image";
            java.sql.PreparedStatement ps=conn.prepareStatement(slectqry);
            rs = ps.executeQuery(slectqry);  
                        
            return rs;
        }
        catch(Exception ex){
        throw new UnsupportedOperationException("Not yet implemented");
        }
    }
    
  public String addpas(String input)
  {
      String passw=null;
      try
      {
          if(input!=null)
          {
            key = KeyGenerator.getInstance(algorithm).generateKey();
            cipher = Cipher.getInstance(algorithm);
            byte[] encryptionBytes = encrypt(input);
            passw=new String(encryptionBytes);
           return passw;
           //insertpass(passw);
          }
      }
      catch(Exception x)
      {
          x.toString();
      }
      finally
      {
           return passw;
      }
     
  }
  
  public boolean  insertpass(String passw) throws SQLException
  {
       boolean f=false;
       String sqlinsert="insert into upload_image (bImage) values(?)";
       java.sql.PreparedStatement prepare=conn.prepareStatement(sqlinsert);
       prepare.setString(1, passw);
       return f;
  }
  
 private static byte[] encrypt(String input)throws Exception {
       
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] inputBytes = input.getBytes();
            return cipher.doFinal(inputBytes);
                 
        }
 
 private static String decrypt(byte[] encryptionBytes)throws Exception {
            cipher.init(Cipher.DECRYPT_MODE, key);
            /*String recoveredBytes = encryptionBytes.toString();
            String recovered = new String(recoveredBytes);
            return recovered;*/
            byte[] recoveredBytes =  cipher.doFinal(encryptionBytes);
            String recovered =  new String(recoveredBytes.toString());
            return recovered;
          }
 
 public String ConvrtPasss(String Pass) throws Exception
 {

       key = KeyGenerator.getInstance(algorithm).generateKey();
      cipher = Cipher.getInstance(algorithm);
       String abc=decrypt(Pass.getBytes()).toString();
     return abc;
 }

    public ResultSet chekpass() {
        ResultSet rs=null;
        try
        {
            Statement st=con.createStatement();
            rs=st.executeQuery("Select * from upload_image where iImageID='1'");
            return  rs;
       }
       catch(Exception ex)
       {
           ex.toString();
       }
        finally
        {
            return  rs;
        }
    }
            
}
