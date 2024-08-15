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
    <title>Minex managent</title>
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-6">
                    <img src="https://www.grupominex.com/imagenes/LOGO%20MINEX.png" alt="Minex Logo" width="100" height="auto" class="img-fluid">
                </div>
                <div class="col-md-6">
                    <h1>Minex</h1>
                </div>
            </div>
            <p>Ayuda no se diseñar</p>
            <p>
                <a class="btn btn-primary btn-lg" href="/admin" role="button">Acceso admin</a>
                <a class="btn btn-success btn-lg" href="/reports" role="button">Ver data</a>
            </p>
        </div>
    </div>
</body>
</html>