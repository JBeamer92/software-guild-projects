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
                <div id="sight-add-head">
                    <div class="col-md-8">
                        <h2>Sightings</h2>
                    </div>
                    <div class="col-md-4 text-right">
                        <button class="btn btn-default dark-button" 
                                id="add-sight-button">
                            Add Sighting
                        </button>
                    </div>
                </div>

            <div id="sight-list-div" class="scroll-table col-md-12">

                <table id="sight-table" class="table">
                    <tr>
                        <th width="15%">Date</th>
                        <th width="25%">Location</th>
                        <th width="50%">Heroes</th>
                        <th width="5%"></th>
                        <th width="5%"></th>
                    </tr>
                    <c:forEach var="sight" items="${sights}">
                        <tr>
                            <td>
                                <c:out value="${sight.date.toString()}"/>
                            </td>
                            <td>
                                <a onclick="displayCoordInMap(${sight.location.latitude}, ${sight.location.longitude})">
                                    <c:out value="${sight.location.name} - ${sight.location.city}"/>
                                </a>
                            </td>
                            <td>
                                <c:forEach var="hero" items="${sight.heroes}">
                                    <c:out value="${hero.name}"/>,
                                </c:forEach>
                            </td>
                            <td>
                                <a href="editSightForm?sightId=${sight.id}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteSighting?sightId=${sight.id}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
            <div class="col-md-8 col-md-offset-2">
                <div id="map">

                </div>
            </div>
            <div id="add-sight-div" style="display: none">
                <div class="col-md-6">
                    <h2>Add Sighting</h2>
                    <form class="form-horizontal" role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <label for="sightDate" class="col-md-4 control-label">
                                Date:
                            </label>
                            <div class="col-md-8">
                                <input type="date" class="form-control"
                                       name="sightDate" id="sightDate"
                                       placeholder="Date" required/>
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
                                        <option value="${loc.id}">
                                            ${loc.name} - ${loc.city}
                                        </option>
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
                                        <option value="${hero.id}">
                                            ${hero.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <button type="button" id="add-sight-cancel" 
                                    class="btn btn-default dark-button">
                                Cancel
                            </button>
                        </div>
                        <div class="col-md-4">
                            <button id="add-sight" class="btn btn-default dark-button">
                                Add Sighting
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
        <script src="${pageContext.request.contextPath}/js/map.js"></script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBki0RpBXjz40ZkOBaip0t_BrDTstzAZjc&callback=initMap">
        </script>

    </body>
</html>