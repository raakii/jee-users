<%@page import="beans.Utilisateur, java.util.ArrayList"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<Utilisateur> utilisateurs = (ArrayList<Utilisateur>) request.getAttribute("utilisateurs"); 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste des utilisateurs</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="text-dark">
    <div class="container">
        <c:import url="inc/menu.jsp"/>
        <div id="corps" class="mt-4">
            <h1 id="titre-principal" class="text-center text-dark">Liste des utilisateurs</h1>
            <c:choose>
                <c:when test="${empty utilisateurs}">
                    <p class="alert alert-warning text-dark">La liste des utilisateurs est pour le moment vide !</p>
                </c:when>
                <c:otherwise>
                    <table class="table table-striped table-bordered text-dark">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Prénom</th>
                                <th>Nom</th>
                                <th>Login</th>
                                <th>Password</th>
                                <th colspan="2">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="utilisateur" items="${requestScope.utilisateurs}">
                                <tr>
                                    <td class="text-dark"><c:out value="${utilisateur.id}"/></td>
                                    <td  class="text-dark"><c:out value="${utilisateur.prenom}"/></td>
                                    <td  class="text-dark"><c:out value="${utilisateur.nom}"/></td>
                                    <td  class="text-dark"><c:out value="${utilisateur.login}"/></td>
                                    <td  class="text-dark"><c:out value="${utilisateur.password}"/></td>
                                    <td  class="text-dark"><a href="<c:url value='/update?id=${utilisateur.id}'/>" class="btn btn-primary">Modifier</a></td>
                                    <td  class="text-dark"><a href="<c:url value='/delete?id=${utilisateur.id}'/>" class="btn btn-danger" onclick="return confirmSuppression()">Supprimer</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
