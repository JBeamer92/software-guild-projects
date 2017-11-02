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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/heroes">Heroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/orgs">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <div class="col-md-6">
                <h2>Edit Hero</h2>
                <form class="form-horizontal" role="form" method="POST"
                      action="editHero">
                    <div class="form-group">
                        <label for="heroName" class="col-md-4 control-label">
                            Term: 
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control"
                                   name="heroName" placeholder="Name"
                                   id="edit-name" value="${hero.name}" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="heroDescription" class="col-md-4 control-label">
                            Description: 
                        </label>
                        <div class="col-md-8">
                            <textarea class="form-control" rows="3"
                                      name="heroDescription" 
                                      placeholder="Description" 
                                      style="background-color: rgb(62,62,62);
                                      color: rgb(255,255,255);"
                                      id="edit-desc" required>${hero.description}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="heroPowers" class="col-md-4 control-label">
                            Powers:
                        </label>
                        <div class="col-md-6">
                            <select multiple style="width: 100%" 
                                    id="select-powers" name="heroPowers">
                                <c:forEach var="power" items="${powers}">
                                    <c:choose>
                                        <c:when test="${hero.powers.contains(power)}">
                                            <option value="${power.id}"
                                                    selected="selected">
                                                ${power.name}
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${power.id}">
                                                ${power.name}
                                            </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="hidden" name="heroId" value="${hero.id}"/>
                    </div>
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-8">
                        <button class="btn btn-default dark-button"
                                onclick="/heroes">
                            Back
                        </button>
                        <button type="reset" class="btn btn-default dark-button">
                            Reset Form
                        </button>
                        <button class="btn btn-default dark-button">
                            Edit Hero
                        </button>
                    </div>
                    <div class="col-md-4">


                    </div>
                </form>


            </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/heroes.js"></script>

    </body>
</html>
