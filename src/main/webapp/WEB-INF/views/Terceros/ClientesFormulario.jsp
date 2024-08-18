<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar cliente</title>
    <!-- Boostrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<!-- Estilos sydebar -->
	<link rel="stylesheet" href="/css/sidebars.css">
</head>
<body>
	<svg xmlns="http://www.w3.org/2000/svg" class="d-none">
		<symbol id="bootstrap" viewBox="0 0 118 94">
		  <title>Bootstrap</title>
		  <path fill-rule="evenodd" clip-rule="evenodd" d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z"></path>
		</symbol>
		<symbol id="home" viewBox="0 0 16 16">
		  <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"></path>
		</symbol>
		<symbol id="speedometer2" viewBox="0 0 16 16">
		  <path d="M8 4a.5.5 0 0 1 .5.5V6a.5.5 0 0 1-1 0V4.5A.5.5 0 0 1 8 4zM3.732 5.732a.5.5 0 0 1 .707 0l.915.914a.5.5 0 1 1-.708.708l-.914-.915a.5.5 0 0 1 0-.707zM2 10a.5.5 0 0 1 .5-.5h1.586a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 10zm9.5 0a.5.5 0 0 1 .5-.5h1.5a.5.5 0 0 1 0 1H12a.5.5 0 0 1-.5-.5zm.754-4.246a.389.389 0 0 0-.527-.02L7.547 9.31a.91.91 0 1 0 1.302 1.258l3.434-4.297a.389.389 0 0 0-.029-.518z"></path>
		  <path fill-rule="evenodd" d="M0 10a8 8 0 1 1 15.547 2.661c-.442 1.253-1.845 1.602-2.932 1.25C11.309 13.488 9.475 13 8 13c-1.474 0-3.31.488-4.615.911-1.087.352-2.49.003-2.932-1.25A7.988 7.988 0 0 1 0 10zm8-7a7 7 0 0 0-6.603 9.329c.203.575.923.876 1.68.63C4.397 12.533 6.358 12 8 12s3.604.532 4.923.96c.757.245 1.477-.056 1.68-.631A7 7 0 0 0 8 3z"></path>
		</symbol>
		<symbol id="table" viewBox="0 0 16 16">
		  <path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm15 2h-4v3h4V4zm0 4h-4v3h4V8zm0 4h-4v3h3a1 1 0 0 0 1-1v-2zm-5 3v-3H6v3h4zm-5 0v-3H1v2a1 1 0 0 0 1 1h3zm-4-4h4V8H1v3zm0-4h4V4H1v3zm5-3v3h4V4H6zm4 4H6v3h4V8z"></path>
		</symbol>
		<symbol id="people-circle" viewBox="0 0 16 16">
		  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
		  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
		</symbol>
		<symbol id="grid" viewBox="0 0 16 16">
		  <path d="M1 2.5A1.5 1.5 0 0 1 2.5 1h3A1.5 1.5 0 0 1 7 2.5v3A1.5 1.5 0 0 1 5.5 7h-3A1.5 1.5 0 0 1 1 5.5v-3zM2.5 2a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 1h3A1.5 1.5 0 0 1 15 2.5v3A1.5 1.5 0 0 1 13.5 7h-3A1.5 1.5 0 0 1 9 5.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zM1 10.5A1.5 1.5 0 0 1 2.5 9h3A1.5 1.5 0 0 1 7 10.5v3A1.5 1.5 0 0 1 5.5 15h-3A1.5 1.5 0 0 1 1 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 9h3a1.5 1.5 0 0 1 1.5 1.5v3a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3z"></path>
		</symbol>
		<symbol id="clipboard" viewBox = "2 2 22 22">
			<path d="M8 5.00005C7.01165 5.00082 6.49359 5.01338 6.09202 5.21799C5.71569 5.40973 5.40973 5.71569 5.21799 6.09202C5 6.51984 5 7.07989 5 8.2V17.8C5 18.9201 5 19.4802 5.21799 19.908C5.40973 20.2843 5.71569 20.5903 6.09202 20.782C6.51984 21 7.07989 21 8.2 21H15.8C16.9201 21 17.4802 21 17.908 20.782C18.2843 20.5903 18.5903 20.2843 18.782 19.908C19 19.4802 19 18.9201 19 17.8V8.2C19 7.07989 19 6.51984 18.782 6.09202C18.5903 5.71569 18.2843 5.40973 17.908 5.21799C17.5064 5.01338 16.9884 5.00082 16 5.00005M8 5.00005V7H16V5.00005M8 5.00005V4.70711C8 4.25435 8.17986 3.82014 8.5 3.5C8.82014 3.17986 9.25435 3 9.70711 3H14.2929C14.7456 3 15.1799 3.17986 15.5 3.5C15.8201 3.82014 16 4.25435 16 4.70711V5.00005M16 11H14M16 16H14M8 11L9 12L11 10M8 16L9 17L11 15" stroke="" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>	
		</symbol>
		<symbol id="truck" viewBox = "0 0 24 24">
			<path xmlns="http://www.w3.org/2000/svg" d="M18.5 18C18.5 19.1046 17.6046 20 16.5 20C15.3954 20 14.5 19.1046 14.5 18M18.5 18C18.5 16.8954 17.6046 16 16.5 16C15.3954 16 14.5 16.8954 14.5 18M18.5 18H21.5M14.5 18H13.5M8.5 18C8.5 19.1046 7.60457 20 6.5 20C5.39543 20 4.5 19.1046 4.5 18M8.5 18C8.5 16.8954 7.60457 16 6.5 16C5.39543 16 4.5 16.8954 4.5 18M8.5 18H13.5M4.5 18C3.39543 18 2.5 17.1046 2.5 16V7.2C2.5 6.0799 2.5 5.51984 2.71799 5.09202C2.90973 4.71569 3.21569 4.40973 3.59202 4.21799C4.01984 4 4.5799 4 5.7 4H10.3C11.4201 4 11.9802 4 12.408 4.21799C12.7843 4.40973 13.0903 4.71569 13.282 5.09202C13.5 5.51984 13.5 6.0799 13.5 7.2V18M13.5 18V8H17.5L20.5 12M20.5 12V18M20.5 12H13.5" stroke="#00000" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"/>
		</symbol>
		<symbol id="driver" viewBox = "0 0 24 24">
			<path fill-rule="evenodd" clip-rule="evenodd" d="M3.5 7V17C3.5 18.1046 4.39543 19 5.5 19H19.5C20.6046 19 21.5 18.1046 21.5 17V7C21.5 5.89543 20.6046 5 19.5 5H5.5C4.39543 5 3.5 5.89543 3.5 7Z" stroke="" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/> <path d="M15.5 10H18.5" stroke="" stroke-width="1.5" stroke-linecap="round"/> <path d="M15.5 13H18.5" stroke="" stroke-width="1.5" stroke-linecap="round"/> <path fill-rule="evenodd" clip-rule="evenodd" d="M11.5 10C11.5 11.1046 10.6046 12 9.5 12C8.39543 12 7.5 11.1046 7.5 10C7.5 8.89543 8.39543 8 9.5 8C10.0304 8 10.5391 8.21071 10.9142 8.58579C11.2893 8.96086 11.5 9.46957 11.5 10Z" stroke="" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/> <path d="M5.5 16C8.283 12.863 11.552 13.849 13.5 16" stroke="" stroke-width="1.5" stroke-linecap="round"/>
		</symbol>
	</svg>
	
	<div class="container-fluid">
	<div class="row">
	<!-- Sidebar  -->
    <div class="sidebar d-flex flex-column flex-shrink-0 p-3 text-bg-dark col-md-3 col-lg-2 vh-100" >
		<a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
			<svg class="bi pe-none me-2" width="40" height="32">
				<use xlink:href="#bootstrap"></use>
			</svg>
			<span class="fs-4">Minex</span>
		</a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item">
				<a href="/" class="nav-link text-white d-flex align-items-center">
					<svg class="bi pe-none me-2" width="16" height="16">
						<use xlink:href="#home"></use>
					</svg>
					Home
				</a>
			</li>
			<li class="nav-item">
				<a href="/despachos" class="nav-link text-white d-flex align-items-center">
					<svg class="bi pe-none me-2" width="16" height="16">
						<use xlink:href="#table"></use>
					</svg>
					Despachos
				</a>
			</li>
			<li>
				<a href="/productores" class="nav-link text-white d-flex align-items-center">
					<svg class="bi pe-none me-2" width="16" height="16">
						<use xlink:href="#grid"></use>
					</svg>
					Productores
				</a>
			</li>
			<li>
				<a href="/choferes" class="nav-link text-white d-flex align-items-center">
					<svg class="pe-none me-2" width="16" height="16" stroke="#ffffff" fill="none">
						<use xlink:href="#driver"></use>
					</svg>
					Choferes
				</a>
			</li>
			<li>
				<a href="/camiones" class="nav-link text-white d-flex align-items-center">
					<svg class="pe-none me-2" width="16" height="16" stroke="#ffffff" fill="none">
						<use xlink:href="#truck"></use>
					</svg>
					Camiones
				</a>
			</li>
			<li>
				<a href="/clientes" class="nav-link text-white d-flex align-items-center active" aria-current="page">
					<svg class="bi pe-none me-2" width="16" height="16">
						<use xlink:href="#people-circle"></use>
					</svg>
					Clientes
				</a>
			</li>
		</ul>
		<hr>
		<div class="nav-pills d-flex flex-column text-center">
			<strong>${usuario.nombre}</strong>
			<a class="nav-link text-white text-center" href="/logout">Cerrar sesión</a>
		</div>	
	</div>
	
	<main class="col-md-9 ms-sm-auto col-lg-10 align-items-center">
		<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2 mb-3">Añadir nuevo cliente</h1>

		</div>

		<div class="d-flex justify-content-center align-items-center">
			<form:form class="col-lg-9 col-sm-12 col-md-12 center-block" method="POST" action="/clientes/add" modelAttribute="cliente">
				<div class="form-floating mb-3">
					<form:input path="rut" class="form-control" id="rut" name="rut" placeholder="rut"/>
					<form:label path="rut" for="rut">Rut</form:label>
                    <small class="text-muted">Ejemplo: 12345678-9</small>
				<form:errors class="text text-danger fw-bold m-2" path="rut"/>

				</div>
				
				<div class="form-floating mb-3">
					<form:input path="razonSocial" class="form-control" id="razonSocial" name="razonSocial" placeholder="razonSocial"/>
					<form:label path="razonSocial" for="razonSocial">Razón social</form:label>
    				<form:errors class="text text-danger fw-bold m-2" path="razonSocial"/>
				</div>

                <div class="row">
                    <div class="col">
                        <div class="form-floating mb-3">
                            <form:input path="ciudadActual" class="form-control" id="ciudadActual" name="ciudadActual" placeholder="ciudadActual"/>
                            <form:label path="ciudadActual" for="ciudadActual">Ciudad</form:label>
                            <form:errors class="text text-danger fw-bold m-2" path="ciudadActual"/>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-floating mb-3 col">
                            <form:input path="comunaActual" class="form-control" id="comunaActual" name="comunaActual" placeholder="comunaActual"/>
                            <form:label path="comunaActual" for="comunaActual">Comuna</form:label>
                            <form:errors class="text text-danger fw-bold m-2" path="comunaActual"/>
                        </div>
                    </div>
                </div>

                <div class="form-floating mb-3">
                    <form:input path="direccionActual" class="form-control" id="floatingInput" name="direccionActual" placeholder="direccionActual"/>
                    <form:label path="direccionActual" for="floatingInput">Dirección</form:label>
                    <form:errors class="text text-danger fw-bold m-2" path="direccionActual"/>
                </div>


				<button class="w-100 btn btn-outline-primary btn-lg" type="submit">Añadir</button>

			</form:form>
		</div>

	</main>
</div>
</div>

<!-- Boostrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="/js/dataTableConfig.js"></script>
<script src="/js/rutFormat.js"></script>

</html>