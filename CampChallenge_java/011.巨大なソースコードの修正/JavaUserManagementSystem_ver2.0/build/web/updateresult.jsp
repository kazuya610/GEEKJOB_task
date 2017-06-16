<%@page import = "jums.JumsHelper"
        import = "java.util.Calendar" 
        import = "jums.UserDataDTO" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)request.getAttribute("resultData");
    // メモ：更新する際になぜか文字化けするので追記
    request.setCharacterEncoding("UTF-8");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS更新結果画面</title>
    </head>
    <body>
        <h1>変更結果</h1><br>
        名前:<%= udd.getName()%><br>
        <% System.out.println(udd.getName()); %>
        生年月日:<%= udd.getBirthday()%><br>
        種別:<%= udd.getType()%><br>
        電話番号:<%= udd.getTell()%><br>
        自己紹介:<%= udd.getComment()%><br>
        以上の内容で登録しました。<br>
    </body>
    <form action="ResultDetail" method="POST">
        <input type="submit" name="NO" value="詳細画面に戻る">
        <input type="hidden" name="id"  value="<%= udd.getUserID() %>">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac") %>">
    </form>
    <%= jh.home() %>
</html>
