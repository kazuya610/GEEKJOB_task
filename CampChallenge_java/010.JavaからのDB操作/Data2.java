/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author takahashi
 */
public class Data2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection db_con = null;
        PreparedStatement db_st = null;
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Data1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>データベースへのアクセス</h1>");
            
            // データベースに接続
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // 1つ目はURL、2つ目はuser、3つ目はパスワード
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db", "root", "");
            
            // SQL文を渡す valuesの中はすべて?にする
            db_st = db_con.prepareStatement("insert into profiles values(?,?,?,?,?)");
            // ?に値を代入
            db_st.setInt(1,3);
            db_st.setString(2,"中畑清");
            db_st.setString(3,"0120-444-444");
            db_st.setInt(4,65);
            db_st.setString(5,"1975-05-23");
            
            // SQL文の実行
            db_st.executeUpdate();
            
            // 操作完了後、クローズ
            db_con.close();
            db_st.close();
            
        }catch(SQLException e_sql){
            out.println("接続時にエラーが発生しました：" + e_sql.toString());
        }catch(Exception e){
            out.println("接続時にエラーが発生しました：" + e.toString());
        }finally{
            if(db_con != null){
                try{
                    db_con.close();
                }catch(SQLException e_con){
                    System.out.println(e_con.getMessage());
                }
            }
        }
            out.println("</body>");
            out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
