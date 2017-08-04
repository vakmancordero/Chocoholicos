<%-- 
    Document   : login
    Created on : 02-ago-2017, 23:52:43
    Author     : VakSF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
            
        <!-- Stylesheet -->
        <link href="<c:url value = "/lib/semantic/semantic.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value = "/css/home.css"/>" rel="stylesheet" rel="stylesheet" type="text/css">
        <link href="<c:url value = "/lib/sweet/sweetalert2.css"/>" rel="stylesheet" type="text/css">
        
        <title>.: Iniciar sesión :.</title>
    </head>
    <body>
        
        <%
            if (request.getSession().getAttribute("loggedUser") == null) 
                response.sendRedirect("login.jsp");
        %>
        <div ng-app="chocoApp"  ng-controller="ChocoController">
            <div class="ui fixed inverted menu">
                <div class="ui container">
                    <a href="#" class="header item">
                        <img class="logo" src="">
                        Chocoholicos
                    </a>
                    <a href="<c:url value = "/home.jsp"/>" class="item">Inicio</a>
                    <div class="ui dropdown item">
                        <i class="database icon"></i>Consultas <i class="dropdown icon"></i>
                        <div class="menu">
                            <a class="item" href="#">Ver consultas</a>
                            <div class="divider"></div>
                            <a class="item" href="#">Eliminar consultas</a>
                        </div>
                    </div>
                    <a href="#" class="item">Inventario</a>
                </div>
            </div>

            <div class="pusher" id="myContainer">

                <div class="ui vertical stripe segment">

                    <h1 class="ui header container">Bienvenido a <strong>Chocoholicos</strong>!</h1>

                    <div class="ui middle aligned piled segment container">
                        <div class="ui inverted segment">
                            <h4>Sin funcionalidad</h4>
                            <div class="ui inverted labeled icon menu">
                                <a class="item">
                                    <i class="add user icon"></i>
                                    Item 1
                                </a>
                                <a class="item">
                                    <i class="configure icon"></i>
                                    Item 2
                                </a>
                                <a class="item">
                                    <i class="edit icon"></i>
                                    Item 1
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="ui middle aligned piled segment stackable container">
                        <div class="ui three column grid ">
                            <div class="column">
                                <div class="ui segment special cards">
                                    <div class="card">
                                        <div class="blurring dimmable image">
                                            <div class="ui dimmer">
                                                <div class="content">
                                                    <div class="center">
                                                        <button class="ui inverted button" ng-click="openAddUserMl()">
                                                            Añadir usuario
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="<c:url value = "/img/matthew.png"/>">
                                        </div>
                                        <div class="content">
                                            <a class="header">Administrar usuarios</a>
                                            <div class="meta">
                                                <span class="date">Agregar nuevos miembros o proveedores</span>
                                            </div>
                                        </div>
                                        <div class="extra content">
                                            <a>
                                                <i class="user circle icon"></i>
                                                # miembros registrados
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="column">
                                <div class="ui segment special cards">
                                    <div class="card">
                                        <div class="blurring dimmable image">
                                            <div class="ui dimmer">
                                                <div class="content">
                                                    <div class="center">
                                                        <button class="ui inverted button" ng-click="openAddConsultationMl()">
                                                            Nueva consulta
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="<c:url value = "/img/consulta.png"/>">
                                        </div>
                                        <div class="content">
                                            <a class="header">Generar consultas</a>
                                            <div class="meta">
                                                <span class="date">Crear nuevas consultas para miembros</span>
                                            </div>
                                        </div>
                                        <div class="extra content">
                                            <a>
                                                <i class="doctor icon"></i>
                                                # Consultas registradas
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="column">
                                <div class="ui segment special cards">
                                    <div class="card">
                                        <div class="blurring dimmable image">
                                            <div class="ui dimmer">
                                                <div class="content">
                                                    <div class="center">
                                                        <div class="ui inverted button">Nuevo reporte</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <img src="<c:url value = "/img/report.png"/>">
                                        </div>
                                        <div class="content">
                                            <a class="header">Generar reportes</a>
                                            <div class="meta">
                                                <span class="date">Exportar información de registros</span>
                                            </div>
                                        </div>
                                        <div class="extra content">
                                            <a><i class="print icon"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                                        
            <!--Usuarios-->
            <div id="addUserMl" class="ui modal" >
                <i class="close icon"></i>
                <div class="header">
                    Nuevo usuario
                </div>
                <div class="image content">
                    <div class="description">
                        <form class="ui form" ng-submit="saveUser()" id="addUserForm">
                            <h4 class="ui dividing header">Información del usuario</h4>
                            <div class="field">
                                <label>Nombre:</label>
                                <input type="text" placeholder="Nombre" ng-model="user.name" required>
                            </div>
                            <div class="field">
                                <label>Dirección:</label>
                                <input type="text" placeholder="Dirección" ng-model="user.address" required>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <label>Código postal:</label>
                                    <input type="text"  placeholder="CP" ng-model="user.cp" required>
                                </div>
                                <div class="field">
                                    <label>Ciudad</label>
                                    <select class="ui fluid dropdown" ng-model="user.city" required id="cityCB">
                                        <option disabled selected value> -- Seleccionar -- </option>
                                        <option value="Tuxtla Gutierrez">Tuxtla Gutiérrez</option>
                                    </select>
                                </div>
                            </div>
                            <div class="field">
                                <div class="eight wide field">
                                    <label>Estado</label>
                                    <select class="ui fluid dropdown" ng-model="user.state" required id="stateCB">
                                        <option disabled selected value> -- Seleccionar -- </option>
                                        <option value="active">Activo</option>
                                        <option value="inactive">Inactivo</option>
                                    </select>
                                </div>
                            </div>
                            <button class="ui button" type="submit">Finalizar</button>
                        </form>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
            
            <!--Consultas-->
            <div id="addConsultationMl" class="ui modal" >
                <i class="close icon"></i>
                <div class="header">
                    Nueva consulta
                </div>
                <div class="image content">
                    <div class="description">
                        <form class="ui form" ng-submit="saveConsultation()" >
                            <h4 class="ui dividing header">Información de la consulta</h4>
                            <div class="field">
                                <label>Fecha:</label>
                                <input type="date" placeholder="Fecha" ng-model="consultation.date" required>
                            </div>
                            <div class="field">
                                <label>Servicio:</label>
                                <div class="ui search" id="search_service">
                                    <div class="ui fluid icon input">
                                        <input class="prompt" type="text" placeholder="Buscar servicio..."
                                                ng-model="consultation.service" required>
                                        <i class="search icon"></i>
                                    </div>
                                    <div class="results"></div>
                                </div>
                            </div>
                            <div class="field">
                                <label>Proveedor:</label>
                                <div class="ui search" id="search_provider">
                                    <div class="ui fluid icon input">
                                        <input class="prompt" type="text" placeholder="Buscar proveedor..."
                                               ng-model="consultation.provider" required>
                                        <i class="search icon"></i>
                                    </div>
                                    <div class="results"></div>
                                </div>
                            </div>
                            <div class="field">
                                <label>Miembro:</label>
                                <div class="ui search" id="search_member">
                                    <div class="ui fluid icon input">
                                        <input class="prompt" type="text" placeholder="Buscar miembro..."
                                               ng-model="consultation.member" required>
                                        <i class="search icon"></i>
                                    </div>
                                    <div class="results"></div>
                                </div>
                            </div>
                            <div class="field">
                                <label>Descripción del servicio:</label>
                                <textarea rows="3" ng-model="consultation.description"></textarea>
                            </div>
                            <div class="field">
                                <label>Comentarios:</label>
                                <textarea rows="3" ng-model="consultation.comment"></textarea>
                            </div>
                            <button class="ui button" type="submit">Finalizar</button>
                        </form>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>
<!--            <div id="addConsultationMl" class="ui modal">
                <i class="close icon"></i>
                <div class="header">
                    Nuevo usuario
                </div>
                <div class="image content">
                    <div class="ui medium image">
                        <img src="<c:url value = "/img/patrick.png"/>">
                    </div>
                    <div class="description">
                        <form class="ui form">
                            <h4 class="ui dividing header">Información del usuario</h4>
                            <div class="field">
                                <label>Nombre:</label>
                                <input type="text" placeholder="Nombre" ng-model="user.name">
                            </div>
                            <div class="field">
                                <label>Dirección:</label>
                                <div class="fields">
                                    <div class="twelve wide field">
                                        <input type="text" placeholder="Calle">
                                    </div>
                                    <div class="four wide field">
                                        <input type="text" placeholder="Número #">
                                    </div>
                                </div>
                            </div>
                            <div class="two fields">
                                <div class="field">
                                    <label>Estado</label>
                                    <select class="ui fluid dropdown">
                                        <option value="">Estado</option>
                                        <option value="AL">Chiapas</option>
                                        <option value="AK">Guadalajara</option>
                                        <option value="AZ">Veracruz</option>
                                        <option value="AZ">Estado de México</option>
                                        <option value="AZ">Monterrey</option>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Ciudad</label>
                                    <select class="ui fluid dropdown">
                                        <option value="">Ciudad</option>
                                        <option value="AL">Tuxtla Gutiérrez</option>
                                    </select>
                                </div>
                            </div>
                            <div class="field">
                                <div class="eight wide field">
                                    <label>Código postal:</label>
                                    <input type="text" placeholder="CP">
                                </div>
                            </div>
                            <div class="ui button" tabindex="0">Finalizar</div>
                        </form>
                    </div>
                </div>
                <div class="actions">
                    <div class="ui negative right labeled icon button">
                        Cerrar
                        <i class="checkmark icon"></i>
                    </div>
                </div>
            </div>-->
        </div>
                                    
        <script src="lib/jquery/jquery-3.2.1.min.js"></script>
        <script src="lib/angular/angular.min.js"></script>
        <script src="lib/semantic/semantic.min.js"></script>
        <script src="lib/sweet/sweetalert2.min.js"></script>
        <script src="js/home.js"></script>
    </body>
</html>
