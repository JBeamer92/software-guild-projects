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
            <div id="org-list-div">
                <div class="row">
                    <div class="col-md-8">
                        <h2>Organizations</h2>
                    </div>
                    <div class="col-md-4 text-right">
                        <button class="btn btn-default dark-button" 
                                id="add-org-button">
                            Add Organization
                        </button>
                    </div>
                </div>
                <table id="org-table" class="table">
                    <tr>
                        <th width="25%">Name</th>
                        <th width="15">Location</th>
                        <th width="30%">Members</th>
                        <th width="20%">Phone</th>
                        <th width="5%"></th>
                        <th width="5%"></th>
                    </tr>
                    <c:forEach var="org" items="${orgs}">
                        <tr>
                            <td>
                                <a href="#" onclick="toggleDescription(${org.id})">
                                    <c:out value="${org.name}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${org.location.name}"/>
                            </td>
                            <td>
                                <c:forEach var="hero" items="${org.heroes}">
                                    <c:out value="${hero.name}"/>,
                                </c:forEach>
                            </td>
                            <td>
                                <c:out value="${org.phoneNumber}"/>
                            </td>
                            <td>
                                <a href="editOrgForm?orgId=${org.id}">
                                    Edit
                                </a>
                            </td>
                            <td>
                                <a href="deleteOrg?orgId=${org.id}">
                                    Delete
                                </a>
                            </td>
                        </tr>
                        <tr id="desc-${org.id}" style="display: none">
                            <td colspan="6">
                                ${org.description}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="add-org-div" style="display: none">
                <div class="col-md-6">
                    <h2>Add Organization</h2>
                    <form class="form-horizontal" role="form" method="POST"
                          action="createOrg">
                        <div class="form-group">
                            <label for="orgName" class="col-md-4 control-label">
                                Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="orgName" placeholder="Name"
                                       id="orgName" required/>
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
                                          id="orgDesc" required></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="orgLocation" class="col-md-4 control-label">
                                Location:
                            </label>
                            <div class="col-md-8">
                                <select name="orgLocation" id="orgLocation">
                                    <c:forEach var="loc" items="${locs}">
                                        <option value="${loc.id}">
                                            ${loc.name} - ${loc.city}
                                        </option>
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
                                       id="orgPhoneNum" required/>
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
                            <button type="button" id="add-org-cancel" 
                                    class="btn btn-default dark-button">
                                Cancel
                            </button>
                        </div>
                        <div class="col-md-4">
                            <button id="add-org" class="btn btn-default dark-button">
                                Add Organization
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

