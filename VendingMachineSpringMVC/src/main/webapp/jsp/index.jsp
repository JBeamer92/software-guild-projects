<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/vendStyle.css" rel="stylesheet"> 
    </head>
    <body>
        <div class='container'>
            <h1 class='text-center'>Vending Machine</h1>
            <hr>
            <ul id='errorMsgs' class='list-group'></ul>
            <div class='row'>
                <div class='col-md-8' id="divLeft">
                    <c:forEach var="item" items="${items}">
                        <div id="item-div-${item.id}" onclick="selectItem(${item.id})"
                             class="col-md-3 col-md-offset-1 text-center vm-item-div">
                            <p class="text-left">${item.id}</p>
                            <p>${item.name}</p>
                            <p>$${item.price}</p>
                            <p>Quantity: ${item.quantity}</p>
                        </div>
                    </c:forEach>
                </div>
                <div class='col-md-4' id="divRight">
                    <form action="makePurchase" method="POST">
                        <div class='inputDiv'>
                            <h3 class='text-center'>Total $ In</h3>

                            <div class='col-md-6 col-md-offset-3'>
                                <input id='total-box' type='number' name="balance" value="${balance}" readonly="readonly">
                            </div>
                            <div class='row'>
                                <button id='add-dollar-btn' type="button"
                                        class='col-md-4 col-md-offset-1 vm-right-div-element btn btn-default'>
                                    Add Dollar
                                </button>
                                <button id='add-quarter-btn' type="button"
                                        class='col-md-offset-2 col-md-4 vm-right-div-element btn btn-default'>
                                    Add Quarter
                                </button>
                            </div>
                            <div class='row'>
                                <button id='add-dime-btn' type="button"
                                        class='col-md-4 col-md-offset-1 vm-right-div-element btn btn-default'>
                                    Add Dime
                                </button>
                                <button id='add-nickel-btn' type="button"
                                        class='col-md-offset-2 col-md-4 vm-right-div-element btn btn-default'>
                                    Add Nickel
                                </button>
                            </div>
                        </div>
                        <div class='outputDiv'>
                            <h3 class='text-center'>Messages</h3>
                            <div class='col-md-8 col-md-offset-2'>
                                <input id='message-box' type='text' style='width:100%' 
                                       name ="message" readonly="readonly"
                                       value="${currentMessage}">

                            </div>
                            <div class='row'>
                                <div class='col-md-8 col-md-offset-2'>
                                    <div class='col-md-4 vm-right-div-element'>
                                        <strong>Item:</strong>
                                    </div>
                                    <div class='col-md-8 vm-right-div-element'>
                                        <input type='number' id='item-num-box' 
                                               style='width:100%' name="item"
                                               value="${currentItem}" readonly="readonly"/>
                                    </div>
                                </div>
                            </div>

                            <div class='row'>
                                <div class='col-md-6 col-md-offset-3 vm-right-div-element'>
                                    <button style='width:100%' id='make-purchase-btn' 
                                            class='btn btn-default' type="submit">
                                        Make Purchase
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>

                    <div class='changeDiv'>
                        <h3 class='text-center'>Change</h3>
                        <div class='col-md-8 col-md-offset-2'>
                            <input id='change-box' type='text' style='width:100%'
                                   value="${changeReturn}" readonly="readonly">
                        </div>
                        <div class='row'>
                            <div class='col-md-6 col-md-offset-3 vm-right-div-element'>
                                <button style='width:100%' id='change-return-btn' class='btn btn-default'>Change Return</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vendScript.js"></script>

    </body>
</html>

