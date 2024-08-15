<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Minex login</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h1>Registro</h1>
                <form:form action="${pageContext.request.contextPath}/usuario/registro" method="post" modelAttribute="usuario">
                    <div class="form-group">
                        <form:label path="nombre">Nombre</form:label>
                        <form:input path="nombre" class="form-control" />
                    </div>
                    <div class="form-group form-group">
                        <form:label path="email">Email</form:label>
                        <form:input path="email" class="form-control" />
                    </div>
                    <div class="form-group form-group">
                        <form:label path="password">Contraseña</form:label>
                        <form:input path="password" class="form-control" />
                    </div>
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>