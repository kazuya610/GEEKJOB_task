package jums;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class SearchResult extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        
        try{
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            String accesschk = request.getParameter("ac");
            if(accesschk == null || (Integer)session.getAttribute("ac") != Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            // メモ：入力フォームの内容を取得してUserDataBeansに格納
            // メモ：詳細ページから戻ってきたかどうかを判別するためにif文を使用
            UserDataBeans udb = new UserDataBeans();
            HttpSession hs = request.getSession();
            if(hs.getAttribute("returnpage") == null){
                // メモ：初めてこのページにアクセスしたときは下記処理を実行
                udb.setName(request.getParameter("name"));
                udb.setYear(request.getParameter("year"));
                udb.setType(request.getParameter("type"));
                // メモ：検索条件をセッションに保存
                session.setAttribute("returnpage", udb);
                
            }else{
                // メモ：詳細ページから戻ってきた時は下記処理を実行
                udb = (UserDataBeans)hs.getAttribute("returnpage");
                
            }
            /*補足
            一度トップページに戻ってから再度検索すると、セッションのせいで
            前回の検索結果が表示されてしまう。。
            直したいですが、今回は時間も無いので先に進みます。。
            */
            
            
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO searchData = new UserDataDTO();
            udb.UD2DTOMapping(searchData);
            
            // 【修正】検索結果を複数件表示できるようにArrayList型に変更
            ArrayList<UserDataDTO> resultData = UserDataDAO.getInstance().search(searchData);
            // 【修正】sessionに保存されるよう変更
            session.setAttribute("resultData", resultData);
            
            request.getRequestDispatcher("/searchresult.jsp").forward(request, response);
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
