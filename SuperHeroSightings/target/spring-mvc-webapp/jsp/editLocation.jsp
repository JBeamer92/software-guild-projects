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
            <div id="edit-loc-div">
                <div class="col-md-6">
                    <h2>Edit Location</h2>
                    <form class="form-horizontal" role="form" method="POST" 
                        action="editLocation">
                        <div class="form-group">
                            <label for="locName" class="col-md-4 control-label">
                                Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locName" placeholder="Name"
                                       id="locName" value="${loc.name}" required/>
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
                                          required>${loc.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locAddress" class="col-md-4 control-label">
                                Address:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locAddress" placeholder="Address"
                                       id="locAddress" 
                                       value="${loc.address}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locCity" class="col-md-4 control-label">
                                City:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locCity" placeholder="City"
                                       id="locCity" value="${loc.city}" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locRegion" class="col-md-4 control-label">
                                Region:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locRegion" placeholder="Region"
                                       id="locRegion" value="${loc.region}" 
                                       required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locCountry" class="col-md-4 control-label">
                                Country:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="locCountry" placeholder="Country"
                                       id="locCountry" value="${loc.country}" 
                                       required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locLat" class="col-md-4 control-label">
                                Latitude:
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       name="locLat" placeholder="Latitude"
                                       id="locLat" step="0.0001" 
                                       value="${loc.latitude}" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="locLong" class="col-md-4 control-label">
                                Longitude:
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       name="locLong" placeholder="Longitude"
                                       id="locLong" step="0.0001" 
                                       value="${loc.longitude}" required/>
                            </div>
                        </div>
                            <input type="hidden" value="${loc.id}" name="locId"/>
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <button type="reset" id="add-loc-cancel" 
                                    class="btn btn-default dark-button">
                                Reset
                            </button>
                        </div>
                        <div class="col-md-4">
                            <button id="add-loc" class="btn btn-default dark-button">
                                Edit Location
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
    </body>
</html>
