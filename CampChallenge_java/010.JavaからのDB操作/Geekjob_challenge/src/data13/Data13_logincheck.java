package data13;

import static java.lang.System.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Data13_logincheck
 */
public class Data13_logincheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection db_con = null;
    PreparedStatement db_st = null;
    ResultSet db_data = null;
    String url;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            request.setCharacterEncoding("UTF-8");
            // ログインフォームのパラメータを取得
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            HttpSession session = request.getSession(true);

            // データベースに接続
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db", "root", "");

            // SQL文を宣言
            db_st = db_con.prepareStatement("select * from ec_user where userID = ? && password = ?");
            db_st.setString(1, user);
            db_st.setString(2, pass);

            // SQL文の実行
            db_data = db_st.executeQuery();

            // チェック結果を元に画面振り分け
            if (db_data.next() == true){
                // trueだった場合、セッションにOKを保存
                session.setAttribute("login", "OK");
                // 本来のアクセス先へ飛ばす
                response.sendRedirect("data13_auth1.jsp");
            }else{
                // 認証に失敗したら、ログイン画面に戻す
                session.setAttribute("status", "Not Auth");
                response.sendRedirect("Data13_login");
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

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
