<html xmlns:jsp="http://java.sun.com/JSP/Page">
<%@page import="org.json.simple.JSONObject"%>
	<head>
		<title>Informatii student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Informatii student JSON</h3>

		<%
            JSONObject jsonObject=(JSONObject) request.getAttribute("jsonObject");
            out.print(jsonObject);
        %>

	</body>
</html>