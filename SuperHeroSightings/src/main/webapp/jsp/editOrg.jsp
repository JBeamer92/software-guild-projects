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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/orgs">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <div id="edit-org-div">
                <div class="col-md-6">
                    <h2>Edit Organization</h2>
                    <form class="form-horizontal" role="form" method="POST"
                          action="editOrg">
                        <div class="form-group">
                            <label for="orgName" class="col-md-4 control-label">
                                Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="orgName" placeholder="Name"
                                       id="orgName" value="${org.name}" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="orgDesc" class="col-md-4 control-label">
                                Description:
                            </label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3"
                                          name="orgDesc" 
                                          placeholder="Description" 
                                          style="background-color: rgb(62,62,62);
                                          color: rgb(255,255,255);"
                                          id="orgDesc" required>${org.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="orgLocation" class="col-md-4 control-label">
                                Location:
                            </label>
                            <div class="col-md-8">
                                <select name="orgLocation" id="orgLocation">
                                    <c:forEach var="loc" items="${locs}">
                                        <c:choose>
                                            <c:when test="${org.location == loc}">
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
                            <label for="orgPhoneType" class="col-md-4 control-label">
                                Phone Type:
                            </label>
                            <div class="col-md-8">
                                <select name="orgPhoneType" id="orgPhoneType">
                                    <option>
                                        Office
                                    </option>
                                    <option>
                                        Home
                                    </option>
                                    <option>
                                        Cell
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="orgPhoneNum" class="col-md-4 control-label">
                                Phone #:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="orgPhoneNum" placeholder="Phone #"
                                       id="orgPhoneNum" value="${org.phoneNumber}" 
                                       required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="orgHeroes" class="col-md-4 control-label">
                                Members:
                            </label>
                            <div class="col-md-8">
                                <select multiple name="orgHeroes" 
                                        id="orgHeroes" style="width: 100%" 
                                        required>
                                    <c:forEach var="hero" items="${heroes}">
                                        <c:choose>
                                            <c:when test="${org.heroes.contains(hero)}">
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
                        <input type="hidden" name="orgId" readonly="readonly"
                               value="${org.id}"/>
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <button type="reset" class="btn btn-default dark-button">
                                Reset
                            </button>
                        </div>
                        <div class="col-md-4">
                            <button id="edit-org" class="btn btn-default dark-button">
                                Edit Organization
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/orgs.js"></script>

    </body>
</html>

