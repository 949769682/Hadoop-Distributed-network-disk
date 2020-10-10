<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="club.hggzs.domain.FileInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 朱世豪
  Date: 2020/3/21
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件列表</title>
    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.form.js"></script>
    <script type="text/javascript" src="../js/FileList.js"></script>
    <script src="../js/message.js"></script>
    <link rel="stylesheet" href="../css/message.css">
    <link rel="stylesheet" href="../css/table.css">
    <link rel="stylesheet" href="../css/buttons.css">
</head>
<body>
<%
    List<FileInfo> files = (List<FileInfo>) session.getAttribute("files");
    if(files == null){
        request.getRequestDispatcher("/pages/login-not.jsp").forward(request,response);
    }
%>
<div align="center" >
    <table id="tab" cellpadding="10">
        <tr>
            <th>文件名</th>
            <th>文件大小</th>
            <th>修改时间</th>
            <th></th>
            <th></th>
        </tr>
        <%
            for(FileInfo file : files){
                out.print("        <tr>\n" +
                        "            <td class='filename'>"+file.getFilename()+"</th>\n" +
                        "            <td>"+file.getFilesize()+"</th>\n" +
                        "            <td>"+file.getFiletime() +"</th>\n" +
                        "<td><input type='button' class='button' onclick='Delete(this)' class='delete' value='删除'/></td>" +
                        "<td><input type='button' class='button' onclick='Download(this)' class='download' value='下载'/></td>" +
                        "<td><input type='button' class='button' onclick='Play(this)' class='play' value='播放'/></td>" +
                        "        </tr>");
            }
        %>
    </table>
    <form id="form" action="/User/upload" enctype = "multipart/form-data" method="POST" >
        <div class="div">
            <input type="file" id="file" name="f" style="display:none;" onchange="file_name(this)"/>
            <input class="button" type="button" value="选择文件" onclick="return file_onclick()"><span id="file_name"></span>
            <input class="button" type="submit" onclick="return Upload()" value="上传"/>
        </div>
    </form>
</div>

</body>
</html>
