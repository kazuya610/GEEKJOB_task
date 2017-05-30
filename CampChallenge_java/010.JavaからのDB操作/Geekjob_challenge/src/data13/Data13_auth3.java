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
 * Servlet implementation class Data13_auth3
 */
public class Data13_auth3 extends HttpServlet {

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
            out.println("<title>商品一覧ページ</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>商品一覧</h1>");

            // 直リンク防止
            HttpSession session = request.getSession();
            Object login = session.getAttribute("login");
            if (login == null || !login.equals("OK")){
                session.setAttribute("login", null);
                response.sendRedirect("Data13_login.jsp");
            }


            // セッション情報の文字化け対策
            request.setCharacterEncoding("UTF-8");

            // データベースに接続
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // 1つ目はURL、2つ目はuser、3つ目はパスワード
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db", "root", "");

            // SQL文を宣言
            db_st = db_con.prepareStatement("select * from ec_product");

            // SQL文の実行
            db_data = db_st.executeQuery();

            // 各ページへのリンク
            out.println("<a href=\"data13_auth1.jsp\">商品登録</a>　");
            out.println("<a href=\"Data13_auth3\">商品一覧</a>　");
            out.println("<a href=\"Data13_logout\">ログアウト</a><br>");

            // 商品一覧の表示
            out.println("<p>現在登録されている商品情報の一覧です。</p><br>");

            while(db_data.next()){
                // 9種類のデータを表示するための変数の定義
                int productID = db_data.getInt("productID");
                String category = db_data.getString("category");
                String name = db_data.getString("name");
                int price = db_data.getInt("price");
                int delivery = db_data.getInt("delivery");
                String maker = db_data.getString("maker");
                String size = db_data.getString("size");
                String introduction = db_data.getString("introduction");
                int point = db_data.getInt("point");

                out.println("商品ID：" + productID + "<br>");
                out.println("商品カテゴリー：" + category + "<br>");
                out.println("商品名：" + name + "<br>");
                out.println("価格：" + price + "<br>");
                out.println("配送料：" + delivery + "<br>");
                out.println("メーカー：" + maker + "<br>");
                out.println("サイズ：" + size + "<br>");
                out.println("商品説明文：" + introduction + "<br>");
                out.println("ポイント数：" + point + "<br>");
                out.println("<br>");
                out.println("<br>");
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
