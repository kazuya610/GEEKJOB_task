/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author takahashi
 */
public class Data12 extends HttpServlet {

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
            out.println("<title>複合検索</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>複合検索</h1>");
            
            // セッション情報の文字化け対策
            request.setCharacterEncoding("UTF-8");
            
            // HTMLファイルの入力データを取得
            String data1 = request.getParameter("word");
            int int_data1 = Integer.parseInt(data1); // 整数に変換
            
            // データベースに接続
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // 1つ目はURL、2つ目はuser、3つ目はパスワード
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db", "root", "");
            
            // SQL文を渡す valuesの中はすべて?にする
            db_st = db_con.prepareStatement("select * from profiles where name = ? or age = ? or birthday = ?");
            // ?に値を代入
            db_st.setString(1,data1);
            db_st.setInt(2,int_data1);
            db_st.setString(3,data1);
            
            // SQL文の実行
            db_data = db_st.executeQuery();
            
            // 取得したデータの表示
            out.println("検索ワード<br>");
            out.println(data1 + "<br>");
            
            while(db_data.next()){
                // 5種類のデータを表示するための変数の定義
                int userID = db_data.getInt("profileID");
                String name = db_data.getString("name");
                String tell = db_data.getString("tell");
                int age = db_data.getInt("age");
                String birthday = db_data.getString("birthday");
                
                out.println("検索結果<br>");
                out.println("プロフィールID:" + userID + "<br>");
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
