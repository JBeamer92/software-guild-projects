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
            <div class="col-md-8">
                <h2>Powers</h2>
                <table class="table">
                    <tr>
                        <th width="25%">Name</th>
                        <th width="65%">Description</th>
                        <th width="5%"></th>
                        <th width="5%"></th>
                    </tr>
                    <c:forEach var="power" items="${powers}">
                        <tr>
                            <td>
                                <c:out value="${power.name}"/>
                            </td>
                            <td>
                                <c:out value="${power.description}"/>
                            </td>
                            <td>
                                <a onclick="displayEditForm(${power.id})">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deletePower?powerId=${power.id}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="col-md-4">
                <div id="add-power-form">
                    <h2>Add Power</h2>

                    <form class="form-horizontal" role="form" method="POST" 
                          action="createPower">
                        <div class="form-group">
                            <label for="powerName" class="col-md-4 control-label">
                                Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="powerName" placeholder="Name"
                                       required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="powerDesc" class="col-md-4 control-label">
                                Description:
                            </label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3"
                                          name="powerDesc" 
                                          placeholder="Description" 
                                          style="background-color: rgb(62,62,62);
                                          color: rgb(255,255,255);"
                                          required></textarea>
                            </div>
                        </div>
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-3">
                            <button class="btn btn-default dark-button" id="add-power">
                                Add Power
                            </button>
                        </div>
                    </form>
                </div>
                
                <div id="edit-power-form" style="display: none">
                    <h2>Edit Power</h2>

                    <form class="form-horizontal" role="form" method="POST" 
                          action="editPower">
                        <div class="form-group">
                            <label for="powerName" class="col-md-4 control-label">
                                Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="powerName" placeholder="Name"
                                       id="editPowerName" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="powerDesc" class="col-md-4 control-label">
                                Description:
                            </label>
                            <div class="col-md-8">
                                <textarea class="form-control" rows="3"
                                          name="powerDesc" 
                                          placeholder="Description" 
                                          id="editPowerDesc" required></textarea>
                            </div>
                        </div>
                        <input type="hidden" name="powerId" id="editPowerId"/>
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-3">
                            <button onclick="edit-power-cancel" type="button"
                                    class="btn btn-default dark-button">
                                Back
                            </button>
                        </div>
                        <div class="col-md-3">
                            <button class="btn btn-default dark-button" id="edit-power">
                                Edit Power
                            </button>
                        </div>
                    </form>
                </div>
                
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/powers.js"></script>

    </body>
</html>