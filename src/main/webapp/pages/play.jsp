<%--
  Created by IntelliJ IDEA.
  User: 朱世豪
  Date: 2020/5/3
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Play video</title>
</head>
<body>
<%
    String filename = request.getParameter("filename");
    String f_type = filename.substring(filename.lastIndexOf(".")+1);
    out.print("<video controls src=\"/User/download?filename="+filename+"\" type=\"video/"+f_type+"\">" +
            "</video" +
            ">");
%>

</body>
</html>
