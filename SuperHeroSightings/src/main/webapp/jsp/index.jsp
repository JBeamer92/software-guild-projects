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
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/heroes">Heroes</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/orgs">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <h2>Recent Sightings</h2>
            <div class="col-md-10 col-md-offset-1">
                <table id="recent-sights-table" class="table table-condensed">
                    <tr>
                        <th width="15%">Date</th>
                        <th width="25%">Location</th>
                        <th width="60%">Heroes</th>
                    </tr>
                    <c:forEach var="sight" items="${sights}">
                        <tr>
                            <td>
                                <c:out value="${sight.date}"/>
                            </td>
                            <td>
                                <c:out value="${sight.location.name}"/>
                            </td>
                            <td>
                                <c:forEach var="hero" items="${sight.heroes}">
                                    <c:out value="${hero.name}"/>, 
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="col-md-8 col-md-offset-2">
                <div id="map">
                    
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/map.js"></script>
        <script src="${pageContext.request.contextPath}/js/index.js"></script>
        <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBki0RpBXjz40ZkOBaip0t_BrDTstzAZjc&callback=initMap">
    </script>
        

    </body>
</html>

