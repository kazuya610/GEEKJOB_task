<%@page import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserData" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData ud = null;
    // メモ：確認画面から戻ってきた時、入力内容を保持。
    // メモ：確認画面でhiddenでmodeとREINPUTを送っている。
    boolean reinput = false;
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
        reinput = true;
        ud = (UserData)hs.getAttribute("ud");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規会員登録</title>
    </head>
    <body>
    <form action="Registrationconfirm" method="POST">
        名前:
        <input type="text" name="name" value="<% if(reinput){out.print(ud.getName());}%>">
        <br><br>

        メールアドレス:
        <input type="text" name="mail" value="<% if(reinput){out.print(ud.getMail());}%>">
        <br><br>
        
        パスワード:
        <input type="text" name="password" value="<% if(reinput){out.print(ud.getPassword());}%>">
        <br><br>
        
        住所：
        <br>
        <textarea name="address" rows=10 cols=50 style="resize:none" wrap="hard"><% if(reinput){out.print(ud.getAddress());}%></textarea><br><br>
        
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
