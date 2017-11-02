<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <title>SuperHero Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/shs_styles.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>SuperHero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/heroes">Heroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/orgs">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>

            <div id="edit-sight-div">
                <div class="col-md-6">
                    <h2>Edit Sighting</h2>
                    <form class="form-horizontal" role="form" method="POST" 
                          action="editSighting">
                        <div class="form-group">
                            <label for="sightDate" class="col-md-4 control-label">
                                Date:
                            </label>
                            <div class="col-md-8">
                                <input type="date" class="form-control"
                                       name="sightDate" id="sightDate"
                                       placeholder="Date" 
                                       value="${sight.date.toString()}" 
                                       required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sightLocation" 
                                   class="col-md-4 control-label">
                                Location:
                            </label>
                            <div class="col-md-8">
                                <select name="sightLocation" id="sightLocation">
                                    <c:forEach var="loc" items="${locs}">
                                        <c:choose>
                                            <c:when test="${sight.location == loc}">
                                                <option value="${loc.id}"
                                                        selected="selected">
                                                    ${loc.name} - ${loc.city}
                                                </option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${loc.id}">
                                                    ${loc.name} - ${loc.city}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sightHeroes" class="col-md-4 control-label">
                                Members:
                            </label>
                            <div class="col-md-8">
                                <select multiple name="sightHeroes" 
                                        id="sightHeroes" style="width: 100%" 
                                        required>
                                    <c:forEach var="hero" items="${heroes}">
                                        <c:choose>
                                            <c:when test="${sight.heroes.contains(hero)}">
                                                <option value="${hero.id}"
                                                        selected="selected">
                                                    ${hero.name}
                                                </option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${hero.id}">
                                                    ${hero.name}
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <input type="hidden" name="sightId" value="${sight.id}"/>
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <button type="reset" id="add-sight-cancel" 
                                    class="btn btn-default dark-button">
                                Reset
                            </button>
                        </div>
                        <div class="col-md-4">
                            <button id="add-sight" class="btn btn-default dark-button">
                                Edit Sighting
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/sightings.js"></script>

    </body>
</html>
