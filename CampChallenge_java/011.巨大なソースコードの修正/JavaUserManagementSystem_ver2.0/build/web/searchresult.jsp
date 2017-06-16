<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.util.ArrayList" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    // 【修正】 セッションから受け取るよう変更
    HttpSession hs = request.getSession();
    ArrayList<UserDataDTO> udd = (ArrayList<UserDataDTO>)hs.getAttribute("resultData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <%--【修正・追記】検索結果がゼロだった時の表示を追加　--%>
        <% if(udd.size()==0){ %> 該当なし <%}%>
        <% for(int i=0;i<udd.size();i++){ %>
        <%-- メモ：検索結果が複数だったとき表示できるようにforを使う（udd.size回数分）--%>
        <%-- メモ：表示するときはget()を入れる　--%>
        <table border=1>
            <tr>
                <th>名前</th>
                <th>生年</th>
                <th>種別</th>
                <th>登録日時</th>
            </tr>
            <tr>
                <%--【修正・追記】生年月日が正しく表示されるように表示　--%>
                <%-- メモ：名前をクリックしたときにURLにidを付けてresultdetailに遷移　--%>
                <%-- メモ：検索結果の表示にはUserDataBeansではなく、DTOのデータを使用　--%>
                <td><a href="ResultDetail?id=<%= udd.get(i).getUserID()%>&ac=<%= hs.getAttribute("ac") %>">
                        <%= udd.get(i).getName()%></a></td>
                <td><%= udd.get(i).getBirthday()%></td>
                <td><%= udd.get(i).getType()%></td>
                <td><%= udd.get(i).getNewDate()%></td>
            </tr>
        </table>
        <%}%>
        <!--<input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"> -->
    </body>
    <%=jh.home()%>
</html>
