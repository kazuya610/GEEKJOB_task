<!-- ログイン後のトップページ -->
<%@page import="javax.servlet.http.HttpSession" 
        import="servlet.UserData" 
        import="java.util.ArrayList" 
        import="servlet.Search_common"
        import="servlet.HtmlSpecialchars" 
        import="servlet.UserDataDTO"
        import="java.util.Map" %>
<%
    HttpSession hs = request.getSession();
    HtmlSpecialchars htmls = new HtmlSpecialchars();
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>トップページ</title>
    </head>
    <body>
    <h1>トップページ</h1>
    <!--ログインしている場合はユーザ名表示と会員情報へのリンク-->
    
    
    
    
    <!-- 新規会員登録 -->
    <a href="Registration">会員登録</a><br>
    
    <!-- キーワード検索 -->
    <form action="Search" method="GET" class="Search">
        
        
        
        
        <!--検索キーワード入力フォーム-->
        <input type="text" name="query" value=""/>
        <!--送信ボタン-->
        <input type="submit" value="Yahooショッピングで検索"/>
    </form>
    </body>
    
<!-- Begin Yahoo! JAPAN Web Services Attribution Snippet -->
<a href="http://developer.yahoo.co.jp/about">
<img src="http://i.yimg.jp/images/yjdn/yjdn_attbtn2_105_17.gif" width="105" height="17" title="Webサービス by Yahoo! JAPAN" alt="Webサービス by Yahoo! JAPAN" border="0" style="margin:15px 15px 15px 15px"></a>
<!-- End Yahoo! JAPAN Web Services Attribution Snippet -->
</html>
