<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<jsp:useBean id="test" class="javabean.JavabeanTest">
    <%
        // 内部测试
        test.InternalTest();

        // 外部测试
        test.ExternalTest();

        // 清理资源
        test.Cleanup();
    %>
</jsp:useBean>
</body>
</html>
