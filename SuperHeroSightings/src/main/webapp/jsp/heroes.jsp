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
                    <h2>Heroes</h2>
                    <table id="heroTable" class="table">
                        <tr>
                            <th width="40%">Hero</th>
                            <th width="40%">Powers</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                        </tr>
                        <c:forEach var="hero" items="${heroes}">
                            <tr>
                                <td>
                                    <a onclick="toggleDescription(${hero.id})">
                                        <div>
                                            <c:out value="${hero.name}"/>
                                        </div>
                                    </a>
                                </td>
                                <td>
                                    
                                    <c:forEach var="currentPower" items="${hero.powers}">
                                        <c:out value="${currentPower.name}"/>, 
                                    </c:forEach>
                                    
                                </td>
                                <td>
                                    <a href="displayEditHero?heroId=${hero.id}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteHero?heroId=${hero.id}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                            <tr id="desc-${hero.id}" class="description" 
                                style="display: none">
                                <td colspan="4">
                                    <c:out value="${hero.description}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-4" id="add-hero-div">
                    <h2>Add Hero</h2>
                    <form class="form-horizontal" role="form" method="POST"
                          action="createHero">
                        <div class="form-group">
                            <label for="heroName" class="col-md-3 control-label">
                                Name: 
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control"
                                       name="heroName" placeholder="Name"
                                       required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="heroDescription" class="col-md-3 control-label">
                                Description: 
                            </label>
                            <div class="col-md-9">
                                <textarea class="form-control" rows="3"
                                          name="heroDescription" 
                                          placeholder="Description" 
                                          style="background-color: rgb(62,62,62);
                                          color: rgb(255,255,255);"
                                          required></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="heroPowers" class="col-md-3 control-label">
                                Powers:
                            </label>
                            <div class="col-md-9">
                                <select multiple style="width: 100%" 
                                        id="select-powers" name="heroPowers">
                                    <c:forEach var="power" items="${powers}">
                                        <option value="${power.id}">
                                            ${power.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                            <button class="btn btn-default dark-button">
                                Add Hero
                            </button>
                        </div>
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-3">
                                <a href="displayEditPowers">Manage Powers</a>
                            </div>
                    </form>
                </div>
                
            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/heroes.js"></script>

    </body>
</html>
