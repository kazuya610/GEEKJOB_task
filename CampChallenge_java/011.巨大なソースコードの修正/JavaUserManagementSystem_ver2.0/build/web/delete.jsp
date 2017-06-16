<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" 
        import="java.util.ArrayList" 
        import="javax.servlet.http.HttpSession" %>
<%
    // 【修正】requestではなくセッションから
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("resultData");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1>削除確認</h1>
    以下の内容を削除します。よろしいですか？
    名前:<%= udd.getName()%><br>
    生年月日:<%= udd.getBirthday()%><br>
    種別:<%= udd.getType()%><br>
    電話番号:<%= udd.getTell()%><br>
    自己紹介:<%= udd.getComment()%><br>
    登録日時:<%= udd.getNewDate()%><br>
    
    <form action="DeleteResult" method="POST">
      <input type="submit" name="YES" value="はい"style="width:100px">
      <!-- 【修正】userIDをサーブレットに渡すために下記1文を追記 -->
<!--      <input type="hidden" name="deletedata"  value="<%=udd.getUserID()%>">-->
      <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
    </form><br>
    <form action="ResultDetail" method="POST">
      <input type="submit" name="NO" value="詳細画面に戻る"style="width:100px">
      <!-- 【修正】userIDをサーブレットに渡すために下記1文を追記 -->
<!--      <input type="hidden" name="id"  value="<%= udd.getUserID() %>">-->
      <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
    </form>
    <br>
        <%=jh.home()%>
    </body>
</html>
