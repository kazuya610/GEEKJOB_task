<%@page import="jums.JumsHelper" 
        import="jums.UserDataDTO" 
        import="javax.servlet.http.HttpSession" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    // 【修正】 セッションから受け取るよう変更
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("resultData");
    request.setCharacterEncoding("UTF-8");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMSユーザー情報詳細画面</title>
    </head>
    <body>
        <h1>詳細情報</h1>
        名前:<%= udd.getName()%><br>
        生年月日:<%= udd.getBirthday()%><br>
        種別:<%= udd.getType()%><br>
        電話番号:<%= udd.getTell()%><br>
        自己紹介:<%= udd.getComment()%><br>
        登録日時:<%= udd.getNewDate()%><br>
        <form action="Update" method="POST">
        <input type="submit" name="update" value="変更"style="width:100px">
        <!-- 【修正・追記】update.javaにuserIDを渡すために追記 -->
        <input type="hidden" name="id"  value="<%= udd.getUserID() %>">
        <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
        </form>
        <form action="Delete" method="POST">
        <input type="submit" name="delete" value="削除"style="width:100px">
        <!-- 【修正・追記】delete.javaにuserIDを渡すために追記 -->
        <input type="hidden" name="id" value="<%= udd.getUserID() %>">
        <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
        </form>
        <!-- 【修正・追記】検索結果に戻るリンク追記 -->
        <form action="SearchResult" method="POST">
        <input type="submit" name="searchresult" value="検索結果一覧へ戻る">
        <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
        </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
