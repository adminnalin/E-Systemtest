/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.AdminRegister;
import Model.CandiRegister;
import Model.CreateUserID;
import Model.UserRegister;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.jasper.tagplugins.jstl.core.Catch;

/**
 *
 * @author User
 */
@WebServlet(name = "AdminRegister_Servlet", urlPatterns = {"/AdminRegister_Servlet"})
public class AdminRegister_Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        AdminRegister adminReg=new AdminRegister();
        UserRegister userreg=new UserRegister();
        CandiRegister candiReg=new CandiRegister();
        byte[] image=null; 

        try {
            if(request.getParameter("adminregbtn")!=null)
                {
                    DiskFileItemFactory factory = new DiskFileItemFactory(); 
                    ServletFileUpload sfu = new ServletFileUpload(factory); 
                    List items = sfu.parseRequest(request); 
                    Iterator iter = items.iterator();
                    //SimpleDateFormat convertDate = new SimpleDateFormat("MMM dd yyyy"); 
                    
                    String userCode="usercode";//request.getParameter("usercode");
                    String usertype="Administrator";
                    String fistname="fname";//request.getParameter("fname");
                    String lstname="lname";//request.getParameter("lname");
                    String gender="gndr";//request.getParameter("gndr");
                    String birthdy="birthday";//request.getParameter("birthday");
                    String mbno="123";//request.getParameter("mbno");
                    String lndno="123";//request.getParameter("lndno");
                    String nic="54687";//request.getParameter("nic");
                    //java.util.Date BithDate = convertDate.parse(birthdy); 
                    String email="adsad@";//request.getParameter("e_mail");
                    //String image=request.getParameter("propic");
                    while (iter.hasNext()) { 
                        FileItem item = (FileItem) iter.next(); 
                            if (!item.isFormField()) { 
                                image = item.get(); 
                            }   
                    } 
                    String createdby="xxx";
                    String createddate="xxx"; 
                    String updatedby=null;
                    String updateddate=null;

                    String usernm="username";//request.getParameter("username");
                    String pass="password";//request.getParameter("password");
                    
                    boolean rslt=userreg.insertUserdetails(userCode,usertype,fistname, lstname,gender,birthdy,mbno, lndno, nic, email,image,createdby,createddate,updatedby, updateddate);
                    if(rslt==true)
                    {
                        ResultSet rs=userreg.GetuserdetailsbyID(userCode,usertype);
                        if(rs.next())
                        {
                            int UserId=rs.getInt(1);
                            String post=rs.getString(2);
                            
                            if(post.equals("Administrator"))
                              {
                                String location="loc";//request.getParameter("loc");
                                boolean boolrs1=adminReg.insertAdminDetail(UserId,location,createdby,createddate,updatedby, updateddate,usernm,pass,post);
                                if(boolrs1==true)
                                {
                                     HttpSession session=request.getSession(true);
                                     session.setAttribute("Register", "Sucess");
                                     response.sendRedirect("~/JspPages/Adminregister.jsp");
                                }
                              }
                            if(post.equals("Candidate"))
                            {
                                 int party=Integer.parseInt(request.getParameter("politicalparty"));
                                 String seat=request.getParameter("seat");
                                 String electNo=request.getParameter("electno");
                                 boolean boolrs1=candiReg.insertCandiDetail(UserId,party,seat,electNo,createdby,createddate,updatedby, updateddate,usernm,pass,post);
                                 if(boolrs1==true)
                                {
                                     HttpSession session=request.getSession(true);
                                     session.setAttribute("Register", "Sucess");
                                     response.sendRedirect("~/JspPages/Adminregister.jsp");
                                }
                            }
                        }
                        else
                        {
                             HttpSession session=request.getSession(true);
                             session.setAttribute("RegisterError", "Error");
                             response.sendRedirect("~/JspPages/Adminregister.jsp");
                        }
                    } 
                    else 
                    {
                         HttpSession session=request.getSession(true);
                         session.setAttribute("RegisterError", "Error");
                         response.sendRedirect("~/JspPages/Adminregister.jsp");
                    }                  
                }
        } 
        catch(Exception ex)
        {
            System.out.print(ex);
        }
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
