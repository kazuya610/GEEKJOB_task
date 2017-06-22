/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.arnx.jsonic.JSON;

/**
 *
 * @author takahashi
 */
public class Search extends HttpServlet {

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
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
        
            // メモ：検索フォーム入力データを取得
            String query = request.getParameter("query");
            // メモ：検索文字列がない場合はトップページへ戻る
            if(query.equals("")) {
                request.getRequestDispatcher("/index2.jsp").forward(request, response); 
            }
            // メモ：エンコードしてから格納
            String query4url = URLEncoder.encode(query, "UTF-8");
            
            // メモ：検索クエリを使ってjson取得URLを作成
            // メモ：Search_commonクラスからappidを呼び出し
            String url_str = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?"
                    + "appid=" + Search_common.appid + "&query=" + query4url;
            // メモ：URLからjsonを受けとってFileバッファに渡す
            URL url = new URL(url_str);
            BufferedReader reader = 
                    new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            StringBuffer responseBuffer = new StringBuffer();
            
            // メモ：URLのjsonからテキストを読み込む処理
            // メモ：readLineは1行ずつ読むので最終行になるまで繰り返す。
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                // 読み込んだ分を追加
                responseBuffer.append(line);
            }
            // メモ：使い終わったら必ず閉じる。
            reader.close();
            
            // メモ：取得したjsonテキストを文字列に変換
            String jsonText = responseBuffer.toString();
            
            // メモ：jsonテキストをjsonic使ってパース
            Map<String, Map<String, Object>> json = JSON.decode(jsonText);
            
            // メモ：検索ヒット数を格納
            int numberOfResult = 
                    Integer.parseInt(json.get("ResultSet").get("totalResultsReturned").toString());
            
            //
            Map<String, Object> result = 
                            ((Map<String, Object>) ((Map<String, Object>) json.get("ResultSet").get("0")).get("Result"));
            
            
            // メモ：セッションに検索条件を保存
            HttpSession session = request.getSession();
            session.setAttribute("query", query);
            session.setAttribute("RESULT", result);
            session.setAttribute("NumOfResults", numberOfResult);
            
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
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
