<%--
  Created by IntelliJ IDEA.
  User: LeiLei
  Date: 2019/5/12
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mm" class="javabean.JavabeanTest"/>
<jsp:setProperty property="*" name="mm"/>

<%

    out.println("ddddd");
    // 内部测试
    mm.InternalTest();
    mm.ExternalTest();
    mm.Cleanup();


    // out.println(i);
    System.out.println("ssseeess");
%>
<form action="Servlet" method="post" enctype="multipart/form-data" >
    文件描述：<input type="text" name="desc"size="20" maxlength="80"><br/>
    文件名称：<input type="file"name="file"size="20"maxlength="80"><br/>
    <input type="submit" value="上传">
    <%=request.getContextPath()%>
</form>