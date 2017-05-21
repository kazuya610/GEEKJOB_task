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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author takahashi
 */
public class Data7 extends HttpServlet {

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
        ResultSet db_data = null;
        
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
            
            //データを更新するSQL文を宣言
            db_st = db_con.prepareStatement(
                    "update profiles set name = '松岡修造', age = 48, birthday = '1967-11-06' "
                            + "where profileID = 1");
            
            // SQL文の実行
            db_st.executeUpdate();
            
            // テーブルを表示するSQL文を宣言
            db_st = db_con.prepareStatement("select * from profiles");
            // SQL文の実行
            db_data = db_st.executeQuery();
            
            // 取得したデータの表示
            while(db_data.next()){
                // 5種類のデータを表示するための変数の定義
                int userID = db_data.getInt("profileID");
                String name = db_data.getString("name");
                String tell = db_data.getString("tell");
                int age = db_data.getInt("age");
                String birthday = db_data.getString("birthday");
                
                out.println("ユーザーID:" + userID + "<br>");
                out.println("名前:" + name + "<br>");
                out.println("電話番号:" + tell + "<br>");
                out.println("年齢:" + age + "<br>");
                out.println("生年月日:" + birthday + "<br>");
            }
            
            // 操作完了後、クローズ
            db_con.close();
            db_st.close();
            db_data.close();
            
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
