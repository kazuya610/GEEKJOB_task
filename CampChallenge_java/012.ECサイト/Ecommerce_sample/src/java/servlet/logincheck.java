/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author takahashi
 */
public class logincheck extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        session.setAttribute("ac", (int) (Math.random() * 1000));
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
        
        try {
            request.setCharacterEncoding("UTF-8");
            
            // メモ：ログインフォームのパラメータを取得
            UserData ud = new UserData();
            ud.setMail(request.getParameter("mail"));
            ud.setPassword(request.getParameter("password"));
            
            // メモ：UserDataのデータをDTOに格納。
            UserDataDTO login_dto = new UserDataDTO();
            // メモ：Mappingメソッド呼び出し。ud格納データをマッピングしたデータがuserdataに格納される。
            ud.Mapping(login_dto);
            
            // メモ：DAOのloginメソッドでログインチェック
            UserDataDAO login_dao = new UserDataDAO();
            boolean chk = login_dao.login(login_dto);
            
            //------------------------------------------
            //ログインチェック後、リダイレクト先振り分け
            ServletContext sc = getServletContext();
            // チェック結果を元に画面振り分け
            if (chk == true){
                // trueだった場合、セッションにOKを保存
                session.setAttribute("login", "OK");
                // 本来のアクセス先へ飛ばす
                sc.getRequestDispatcher("index2.jsp").forward(request,response);
            }else{
                // 認証に失敗したら、ログイン画面に戻す
                session.setAttribute("status", "Not Auth");
                sc.getRequestDispatcher("login2.jsp").forward(request,response);
            }
            
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
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
