<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.JumsHelper"
        import="jums.UserData" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    // メモ：インスタンスud生成。InsertConfirm.javaでセッションに格納した値を呼び出してudに格納する。
    UserData ud = (UserData)hs.getAttribute("ud");
    // メモ：未入力チェックを行うchkpropertiesメソッド呼び出し。
    // メモ：正しく入力されていればchkListは空になる。UserDataBeans.java参照。
    ArrayList<String> chkList = ud.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員登録 確認画面</title>
    </head>
    <body>
    <!--メモ：sizeメソッド＝ArrayListクラスで用意されているメソッド。現在格納されている要素数を確認できる。-->
    <!--メモ：正しく入力されていればchkList.size()==0になる。UserDataのgetを実行。-->
    <% if(chkList.size()==0){ %>
        <h1>登録確認</h1>
        名前:<%= ud.getName()%><br>
        メールアドレス:<%= ud.getMail()%><br>
        パスワード:<%= ud.getPassword()%><br>
        住所:<%= ud.getAddress()%><br>
        上記の内容で登録します。よろしいですか？
        <form action="Registrationcomplete" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form>
    <% }else{ %>
        <h1>入力が不完全です</h1>
        <%=jh.chkinput(chkList) %>
    <% } %>
        <form action="Registration" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
            <input type="hidden" name="mode" value="REINPUT">
        </form>
        <%=jh.home()%>
    </body>
</html>
