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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                </ul>    
            </div>
            <div id="loc-add-head">
                <div class="col-md-8">
                    <h2>Locations</h2>
                </div>
                <div class="col-md-4 text-right">
                    <button class="btn btn-default dark-button" 
                            id="add-loc-button">
                        Add Location
                    </button>
                </div>
            </div>
            <div id="loc-list-div" class="scroll-table col-md-12">

                <table id="loc-table" class="table">
                    <tr>
                        <th width="20%">Name</th>
                        <th width="15%">Address</th>
                        <th width="15%">City</th>
                        <th width="10%">Region</th>
                        <th width="10%">Country</th>
                        <th width="10%">Latitude</th>
                        <th width="10%">Longitude</th>
                        <th width="5%"></th>
                        <th width="5%"></th>
                    </tr>
                    <div id="location-rows">
                        <c:forEach var="loc" items="${locs}">
                        <tr>
                            <td>
                                <a onclick="displayCoordInMap(${loc.latitude},
                                   ${loc.longitude})">
                                    <c:out value="${loc.name}"/>
                                </a>
                                <div class="text-right" style="display: inline;
                                     float: right;">
                                    <a onclick="toggleDescription(${loc.id})">
                                        &#9432
                                    </a>
                                </div>
                            </td>
                            <td>
                                <c:out value="${loc.address}"/>
                            </td>
                            <td>
                                <c:out value="${loc.city}"/>
                            </td>
                            <td>
                                <c:out value="${loc.region}"/>
                            </td>
                            <td>
                                <c:out value="${loc.country}"/>
                            </td>
                            <td>
                                <c:out value="${loc.latitude}"/>
                            </td>
                            <td>
                                <c:out value="${loc.longitude}"/>
                            </td>
                            <td>
                                <a href="editLocationForm?locId=${loc.id}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteLocation?locId=${loc.id}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                        <tr id="desc-${loc.id}" style="display: none">
                            <td colspan="9">
                                <c:out value="${loc.description}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </div>
                </table>
            </div>
            <div class="col-md-8 col-md-offset-2">
                <div id="map">

                </div>
            </div>
            <div id="add-loc-div" style="display: none">
                <div class="col-md-6">
                    <form class="form-horizontal" role="form" method="POST" 
                          action="createLocation">
                        <div class="form-group">
                            <label for="locName" class="col-md-4 control-label">
                                Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locName" placeholder="Name"
                                       id="locName" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locDesc" class="col-md-4 control-label">
                                Description:
                            </label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3"
                                          name="locDesc" id="locDesc"
                                          placeholder="Description" 
                                          style="background-color: rgb(62,62,62);
                                          color: rgb(255,255,255);"
                                          required></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locAddress" class="col-md-4 control-label">
                                Address:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locAddress" placeholder="Address"
                                       id="locAddress"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locCity" class="col-md-4 control-label">
                                City:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locCity" placeholder="City"
                                       id="locCity" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locRegion" class="col-md-4 control-label">
                                Region:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locRegion" placeholder="Region"
                                       id="locRegion" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locCountry" class="col-md-4 control-label">
                                Country:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locCountry" placeholder="Country"
                                       id="locCountry" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locLat" class="col-md-4 control-label">
                                Latitude:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locLat" placeholder="Latitude"
                                       id="locLat" step="0.0001"
                                       readonly="readonly" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locLong" class="col-md-4 control-label">
                                Longitude:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locLong" placeholder="Longitude"
                                       id="locLong" step="0.0001" 
                                       readonly="readonly" required/>
                            </div>
                        </div>

                        <div class="col-md-4">
                            
                        </div>
                        <div class="col-md-8">
                            <button type="button" id="add-loc-cancel" 
                                    class="btn btn-default dark-button">
                                Cancel
                            </button>
                            <button id="get-lat-lon" onclick="getLatAndLng()" 
                                    class="btn btn-default dark-button" type="button">
                                Complete form
                            </button>
                            <button id="add-loc" class="btn btn-default dark-button"
                                    style="display: none;">
                                Add Location
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/locations.js"></script>
        <script src="${pageContext.request.contextPath}/js/map.js"></script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBki0RpBXjz40ZkOBaip0t_BrDTstzAZjc&callback=initMap">
        </script>
    </body>
</html>
