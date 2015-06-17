<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <title>Cooking miam miam : le site de cuisine à manger</title>

    <link rel="stylesheet" href="/lib/bootstrap/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Cooking Miam Miam</a>
            </div>

            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/recettes">Toutes les recette</a></li>
                    <li><a href="/recette-du-moment">Recette du moment</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <h1>Une recette de saison aléatoire</h1>

        <div class="row">
            <div class="col-xs-12 col-sm-4">
                <div class="thumbnail">
                    <img src="/image/${recipe.imageId}" alt="${fn:escapeXml(recipe.title)}">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8">
                <%-- BUG : edit button when admin --%>
                <h1>${fn:escapeXml(recipe.title)}</h1>
                <p>${fn:escapeXml(recipe.intro)}</p>
                <%-- BUG : format tags with for each --%>
                <span class="label label-primary">${fn:escapeXml(recipe.tags)}</span>
                <%-- BUG : format date --%>
                <p>${recipe.date}</p>
                <c:if test="${not empty recipe.ingredients}">
                    <ul>
                        <c:forEach var="ingredient" items="${recipe.ingredients}">
                            <li>${fn:escapeXml(ingredient.name)} : ${fn:escapeXml(ingredient.quantity)} ${fn:escapeXml(ingredient.unit)}</li>
                        </c:forEach>
                    </ul>
                </c:if>
                <%-- BUG : format text with function --%>
                <p>${fn:escapeXml(recipe.text)}</p>
            </div>
        </div>
    </div>

    <fragments:footer />

    <script src="/lib/jquery/dist/jquery.min.js"></script>
    <script src="/lib/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>