<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Ajout d'un utilisateur</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS for Error Messages -->
    <style>
        .erreur {
            color: black;
        }
        .success {
            color: green;
        }
        .error {
            color: red;
        }
    </style>
    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="text-dark">
    <div class="container">
        <c:import url="inc/menu.jsp"/>	 
        <div id="corps" class="mt-4">
            <h1 id="titre-principal" class="text-center text-dark">Ajout d'un utilisateur</h1>
            <p id="statusMessage" class="${status ? 'success' : 'error'}">${statusMessage}</p>
            <form method="post" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="nom">Nom</label>
                    <input type="text" class="form-control" id="nom" name="nom" value="${utilisateur.nom}" required>
                    <div class="invalid-feedback erreur">${erreurs.nom}</div>
                </div>
                <div class="form-group">
                    <label for="prenom">Prénom</label>
                    <input type="text" class="form-control" id="prenom" name="prenom" value="${utilisateur.prenom}" required>
                    <div class="invalid-feedback erreur">${erreurs.prenom}</div>
                </div>
                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" id="login" name="login" value="${utilisateur.login}" required>
                    <div class="invalid-feedback erreur">${erreurs.login}</div>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <div class="invalid-feedback erreur">${erreurs.password}</div>
                </div>
                <div class="form-group">
                    <label for="passwordBis">Password Confirmation</label>
                    <input type="password" class="form-control" id="passwordBis" name="passwordBis" required>
                    <div class="invalid-feedback erreur">${erreurs.passwordBis}</div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Optional JavaScript for Bootstrap Validation -->
    <script>
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                var forms = document.getElementsByClassName('needs-validation');
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</body>
</html>
