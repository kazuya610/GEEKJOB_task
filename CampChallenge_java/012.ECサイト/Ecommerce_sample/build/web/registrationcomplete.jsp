<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserData" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    UserData ud = (UserData)request.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1>登録結果</h1><br>
        名前:<%= ud.getName()%><br>
        メールアドレス:<%= ud.getMail()%><br>
        パスワード:<%= ud.getPassword()%><br>
        住所:<%= ud.getAddress()%><br>
        以上の内容で登録しました。<br>
        
        <%=jh.home()%>
    </body>
</html>
