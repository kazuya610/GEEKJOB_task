<%@page import="servlet.UserDataDTO"
        import="java.util.ArrayList" 
        import="javax.servlet.http.HttpSession" %>
<%
    // JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            // HttpSession session = request.getSession(true);

            /* 認証失敗から呼び出されたのかどうか */
            Object status = session.getAttribute("status");
            
            if (status != null){
                out.println("<p>認証に失敗しました</p>");
                out.println("<p>再度ユーザー名とパスワードを入力して下さい</p>");
                
                session.setAttribute("status", null);
            }
        %>
        
    <form method="POST" action="logincheck" name="loginform">
        <table>
        <tr>
            <td>メールアドレス</td>
            <td><input type="text" name="mail"></td>
        </tr>
        <tr>
            <td>パスワード</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="login"></td>
            <td><input type="reset" value="reset"></td>
        </tr>
        </table>
    </form>
    </body>
</html>
