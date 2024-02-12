<html xmlns:jsp="http://java.sun.com/JSP/Page">
<%@page import="beans.StudentBean"%>
<%@page import="java.util.ArrayList"%>
	<head>
		<title>Informatii student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Informatii student</h3>

		<!-- folosirea bean-ului pentru afisarea informatiilor -->
        <%
            ArrayList<StudentBean>studentBeans =  (ArrayList<StudentBean>) request.getAttribute("studentBeans");

            out.print("<table>\n\t<tr>\n\t<th>ID</th>\n\t<th>Nume</th>\n\t<th>Prenume</th>\n\t<th>Varsta</th></tr>\n ");
            for(int i=0; i < studentBeans.size(); i++){
                out.print("<tr>\n\t<td>" + studentBeans.get(i).getId() + "</td>\n");
                out.print("\t<td>" + studentBeans.get(i).getNume() + "</td>\n");
                out.print("\t<td>" + studentBeans.get(i).getPrenume() + "</td>\n");
                out.print("\t<td>" + studentBeans.get(i).getVarsta() + "</td>\n</tr>\n");
            }
            out.print("</table>");
        %>
        <p><strong>Sterge studenti:</strong></p>
		<form action="./delete-student" method="post">
			ID: <input type="number" name="id" />
            <br />
			<button type="submit" name="submit">Sterge</button>
		</form>

        <p><strong>Cauta studenti:</strong></p>
		<form action="./search-student" method="post">
			Nume: <input type="text" name="nume" />
			<br />
			Prenume: <input type="text" name="prenume" />
            <br />
			Varsta: <input type="number" name="varsta" />
            <br />
			<br />
			<button type="submit" name="submit">Cauta</button>
		</form>

        <p><strong>Actualizeaza studenti:</strong></p>
		<form action="./update-student" method="post">
		    ID: <input type="number" name="id" />
        	<br />
			Nume: <input type="text" name="nume" />
			<br />
			Prenume: <input type="text" name="prenume" />
            <br />
			Varsta: <input type="number" name="varsta" />
            <br />
			<br />
			<button type="submit" name="submit">Actualizeaza</button>
		</form>
	</body>
</html>