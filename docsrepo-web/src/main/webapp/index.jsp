<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strona główna</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.4.1/sketchy/bootstrap.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css" />
        <link rel="icon" href="images/logo.png">
    </head>
    <body>
        <jsp:include page="IndexServlet"/>
        <header></header>
        <div class="container mt-5 mb-5">
            <div class="table-responsive">
                <h1>Dokumenty</h1>
                <table class="table table-hover">
                    <thead>
                    </thead>
                    <tbody>
                        <c:forEach var="document" items="${documents}">
                            <tr>
                                <td>
                                    <i class="fas fa-file-word fa-2x"></i>
                                    <a class="btn btn-link" href="PreviewServlet?id=${document.id}" style="font-size: 180%">
                                        ${document.name}
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            fetch("./header.html")
                    .then(response => {
                        return response.text();
                    })
                    .then(data => {
                        document.querySelector("header").innerHTML = data;
                    });
        </script>
    </body>
</html>
