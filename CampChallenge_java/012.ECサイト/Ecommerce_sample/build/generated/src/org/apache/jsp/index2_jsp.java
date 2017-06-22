package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.HttpSession;
import servlet.UserData;
import java.util.ArrayList;
import servlet.Search_common;
import servlet.HtmlSpecialchars;
import servlet.UserDataDTO;
import java.util.Map;

public final class index2_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!-- ログイン後のトップページ -->\n");
      out.write("\n");

    HttpSession hs = request.getSession();
    HtmlSpecialchars htmls = new HtmlSpecialchars();
    

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>トップページ</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <h1>トップページ</h1>\n");
      out.write("    <!--ログインしている場合はユーザ名表示と会員情報へのリンク-->\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    <!-- 新規会員登録 -->\n");
      out.write("    <a href=\"Registration\">会員登録</a><br>\n");
      out.write("    \n");
      out.write("    <!-- キーワード検索 -->\n");
      out.write("    <form action=\"Search\" method=\"GET\" class=\"Search\">\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <!--検索キーワード入力フォーム-->\n");
      out.write("        <input type=\"text\" name=\"query\" value=\"\"/>\n");
      out.write("        <!--送信ボタン-->\n");
      out.write("        <input type=\"submit\" value=\"Yahooショッピングで検索\"/>\n");
      out.write("    </form>\n");
      out.write("    </body>\n");
      out.write("    \n");
      out.write("<!-- Begin Yahoo! JAPAN Web Services Attribution Snippet -->\n");
      out.write("<a href=\"http://developer.yahoo.co.jp/about\">\n");
      out.write("<img src=\"http://i.yimg.jp/images/yjdn/yjdn_attbtn2_105_17.gif\" width=\"105\" height=\"17\" title=\"Webサービス by Yahoo! JAPAN\" alt=\"Webサービス by Yahoo! JAPAN\" border=\"0\" style=\"margin:15px 15px 15px 15px\"></a>\n");
      out.write("<!-- End Yahoo! JAPAN Web Services Attribution Snippet -->\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
