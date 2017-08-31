<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página en analisis</title>
<%@ page isELIgnored="false"%>
</head>
<body>
	<h2>Bienvenido!! <br> Analizando ${name}:</h2>
	<table>
<tr>
  <td><strong>Tipo Enlace</strong></td>
  <td><strong>Cantidad</strong></td>
</tr>
 
<tr>
  <td><strong>Imágenes</strong></td>
  <td>${numImg}</td>
</tr>
 
<tr>
  <td><strong>web</strong></td>
  <td>HTTP:${numHttp}       HTTPS:${numHttps}</td>
</tr>
 
<tr>
  <td><strong>Script</strong></td>
  <td>${numScript}</td>
  <td>-</td>
</tr>
</table>



	<c:forEach items="${enlaces}" var="url">
		<h4>${url}</h4>
	</c:forEach>


</body>
</html>