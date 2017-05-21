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

/**
 *
 * @author takahashi
 */
public class QueriesString1 extends HttpServlet {

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
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QueriesString1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QueriesString1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            
            // 総額
            String str1 = request.getParameter("total");
            Integer totalprice = Integer.parseInt(str1);
            // 個数
            String str2 = request.getParameter("count");
            Integer num = Integer.parseInt(str2);
            // 商品種別
            String str3 = request.getParameter("type");
            Integer name = Integer.parseInt(str3);
            
            String product;
            double point; //ポイント数
            
            switch (name) {
                case 1:
                    product = "雑貨";
                    break;
                case 2:
                    product = "生鮮食品";
                    break;
                default:
                    product = "その他";
                    break;
            }

            if(totalprice > 5000){
        	point = totalprice * 0.05;
            }else {
        	point = totalprice * 0.04;
            }

            out.println("URLから取得した値は以下のとおりです。<br>");
            out.println("お買い上げ商品の種別は　" + product + "　です。<br>");
            out.println("お買い上げ点数は　" + num + "　個です。<br>");
            out.println("お買い上げ金額は合計　" + totalprice + "　円です。<br>");
            out.println("一つ当たりのお値段は　" + (totalprice / num) + "　円です。<br>");
            out.println("今回のポイントは　" + point + "　ポイントです。<br>");
            
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
