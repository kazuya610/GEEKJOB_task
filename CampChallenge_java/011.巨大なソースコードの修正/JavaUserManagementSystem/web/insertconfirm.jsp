<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="jums.JumsHelper" %>
<%@page import="jums.UserDataBeans" %>
<%
    HttpSession hs = request.getSession();
    UserDataBeans udb = (UserDataBeans)hs.getAttribute("UDB");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録確認画面</title>
    </head>
    <body>
        <%-- 第一段階タスク4 --%>
        <%-- フォームが空の時はエラー文を出すためのif文を用意 --%>
        <% if(!udb.getName().equals("") && !udb.getYear().equals("") && !udb.getMonth().equals("") && 
            !udb.getDay().equals("") && !(udb.getType()==null) && !udb.getTell().equals("") && 
            !udb.getComment().equals("")){%>
        
        <h1>登録確認</h1>
        名前:<%= udb.getName() %><br>
        生年月日:<%= udb.getYear()+"年"+udb.getMonth()+"月"+udb.getDay()+"日"%><br>
        種別:<%= udb.getType()%><br>
        電話番号:<%= udb.getTell()%><br>
        自己紹介:<%= udb.getComment()%><br>
        上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
            <%-- 【第一段階タスク2】直リンク防止 --%>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form>
        
        <% }else{ %>
            <h1>入力が不完全です</h1>
            <% if(udb.getName().equals("")) { %> 名前<br> <% } %>
            <% if(udb.getYear().equals("") || udb.getMonth().equals("")
                || udb.getDay().equals("")) { %> 生年月日<br> <% } %>
                <% if(udb.getType() == null) { %> 種別<br> <% } %>
                <% if(udb.getTell().equals("")) { %> 電話番号<br> <% } %>
                <% if(udb.getComment().equals("")) { %> 自己紹介<br> <% } %>
        <% } %>
        
        <form action="insert" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="no" value="登録画面に戻る">
        </form>
    
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
