/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author takahashi
 */
public class Session2 extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Session2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>登録情報</h1>");
            
            // セッション情報の文字化け対策
            request.setCharacterEncoding("UTF-8");
            
            // セッションを宣言
            HttpSession session1 = request.getSession();
            HttpSession session2 = request.getSession();
            HttpSession session3 = request.getSession();
            
            // jspファイルの入力データを取得
            String name = request.getParameter("textName");  // 名前
            String sex = request.getParameter("rdoSex");  // 性別
            String hobby = request.getParameter("mulHobby");  // 趣味
            
            // 入力データをセッションに保存
            session1.setAttribute("date1", name);
            session2.setAttribute("date2", sex);
            session3.setAttribute("date3", hobby);
            
            // 保存したセッションを呼び出し
            String profile1 = (String)session1.getAttribute("date1");
            String profile2 = (String)session2.getAttribute("date2");
            String profile3 = (String)session3.getAttribute("date3");
            
            // 呼び出したデータを表示
            out.println("入力されたデータは以下の通りです。<br>");
            out.println("名前：" + profile1 + "<br>");
            out.println("性別：" + profile2 + "<br>");
            out.println("趣味：" + profile3 + "<br>");
            
            out.println("</body>");
            out.println("</html>");
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
