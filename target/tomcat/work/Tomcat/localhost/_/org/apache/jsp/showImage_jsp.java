/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-05-08 17:15:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class showImage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>今日校园图片保存页面</title>\r\n");
      out.write("</head>\r\n");
      out.write("<script src=\"https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js\"></script>\r\n");
      out.write("<style>\r\n");
      out.write("\r\n");
      out.write("    * {\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        font-family: 微软雅黑;\r\n");
      out.write("        box-sizing: border-box;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    body {\r\n");
      out.write("        min-height: 100vh;\r\n");
      out.write("        background-image: linear-gradient(120deg, #7ed6df, #4834d4);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .login-form {\r\n");
      out.write("        width: 540px;\r\n");
      out.write("        background: #f1f1f1;\r\n");
      out.write("        padding: 30px 40px;\r\n");
      out.write("        height: 500px;\r\n");
      out.write("        border-radius: 10px;\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        left: 50%;\r\n");
      out.write("        top: 50%;\r\n");
      out.write("        transform: translate(-50%, -50%);\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .login-form h2 {\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        margin-bottom: 40px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .txtb {\r\n");
      out.write("        border-bottom: 2px solid #adadad;\r\n");
      out.write("        position: relative;\r\n");
      out.write("        margin: 30px 0;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .txtb input {\r\n");
      out.write("        font-size: 15px;\r\n");
      out.write("        color: #333;\r\n");
      out.write("        border: none;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        outline: none;\r\n");
      out.write("        background: none;\r\n");
      out.write("        padding: 0 5px;\r\n");
      out.write("        height: 40px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .txtb span::before {\r\n");
      out.write("        content: attr(data-placeholder);\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        top: 50%;\r\n");
      out.write("        left: 5px;\r\n");
      out.write("        color: #adadad;\r\n");
      out.write("        transform: translateY(-50%);\r\n");
      out.write("        z-index: -1;\r\n");
      out.write("        transition: .5s;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .txtb span::after {\r\n");
      out.write("        content: '';\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 2px;\r\n");
      out.write("        background-image: linear-gradient(120deg, #7ed6df, #4834d4);\r\n");
      out.write("        left: 0;\r\n");
      out.write("        bottom: -2px;\r\n");
      out.write("        transition: .5s;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .focus + span::before {\r\n");
      out.write("        top: -5px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .focus + span::after {\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        bottom: -2px;\r\n");
      out.write("        left: 0;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .logbtn {\r\n");
      out.write("\r\n");
      out.write("        display: block;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 50px;\r\n");
      out.write("        border: none;\r\n");
      out.write("        background-image: linear-gradient(120deg, #4834d4, #7ed6df, #4834d4);\r\n");
      out.write("        background-size: 200%;\r\n");
      out.write("        color: #fff;\r\n");
      out.write("        transition: .5s;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .logbtn:hover {\r\n");
      out.write("        background-position: right;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    a {\r\n");
      out.write("        color: #747d8c;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    img {\r\n");
      out.write("\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 300px;\r\n");
      out.write("        border: 1px dashed #ccc;\r\n");
      out.write("        display: table-cell;\r\n");
      out.write("    / / 主要是这个属性 vertical-align: middle;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        margin-bottom: 20px;\r\n");
      out.write("    }\r\n");
      out.write("</style>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div class=\"login-form\">\r\n");
      out.write("    <h2>");
      out.print(session.getAttribute("username"));
      out.write(" 你好！ 这是你要的图片~</h2>\r\n");
      out.write("    <img id=\"image\" src=\"data:image/jpg;base64,");
      out.print(session.getAttribute("base64"));
      out.write("\">\r\n");
      out.write("    <input type=\"submit\" onclick=\"download()\" class=\"logbtn\" value=\"保存\">\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");

        if (session.getAttribute("base64")==null || session.getAttribute("base64")==""){
            response.sendRedirect("/picture.html");
        }
    
      out.write("\r\n");
      out.write("    let base64Image = \"data:image/jpg;base64,");
      out.print(session.getAttribute("base64"));
      out.write("\";\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        $(\".txtb input\").on(\"focus\", function () {\r\n");
      out.write("            $(this).addClass(\"focus\");\r\n");
      out.write("        });\r\n");
      out.write("        $(\".txtb input\").on(\"blur\", function () {\r\n");
      out.write("            if ($(this).val() == \"\")\r\n");
      out.write("                $(this).removeClass(\"focus\");\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        function download() {\r\n");
      out.write("            this.downloadFile('image.jpg', base64Image);\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        //下载\r\n");
      out.write("        function downloadFile(fileName, content) {\r\n");
      out.write("            let aLink = document.createElement('a');\r\n");
      out.write("            let blob = this.base64ToBlob(content); //new Blob([content]);\r\n");
      out.write("\r\n");
      out.write("            let evt = document.createEvent(\"HTMLEvents\");\r\n");
      out.write("            evt.initEvent(\"click\", true, true);//initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为\r\n");
      out.write("            aLink.download = fileName;\r\n");
      out.write("            aLink.href = URL.createObjectURL(blob);\r\n");
      out.write("\r\n");
      out.write("            // aLink.dispatchEvent(evt);\r\n");
      out.write("            aLink.click()\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        //base64转blob\r\n");
      out.write("        function base64ToBlob(code) {\r\n");
      out.write("            let parts = code.split(';base64,');\r\n");
      out.write("            let contentType = parts[0].split(':')[1];\r\n");
      out.write("            let raw = window.atob(parts[1]);\r\n");
      out.write("            let rawLength = raw.length;\r\n");
      out.write("\r\n");
      out.write("            let uInt8Array = new Uint8Array(rawLength);\r\n");
      out.write("\r\n");
      out.write("            for (let i = 0; i < rawLength; ++i) {\r\n");
      out.write("                uInt8Array[i] = raw.charCodeAt(i);\r\n");
      out.write("            }\r\n");
      out.write("            return new Blob([uInt8Array], {type: contentType});\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}