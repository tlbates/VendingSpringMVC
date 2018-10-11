<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <style>
        .well{
            border-style: inset;
            border-width: thick;
        }
        
        hr {
            border-style: dotted;
        }
        
        body {
            background-color: #888;
            color: white;
        }
        .container{
            font-family: monospace;
        }
        
        #itemDiv{
            background-color: #269abc;
            font-family: monospace;
            color: white;
        }
    </style>
    <body>
        <div class="container">
            <h1>Vending Machine</h1>
            <hr/>
            <div class="row">
                <div class="col-md-9" id="item-container">
                    <c:forEach var="item" items="${itemList}">
                        <div class="col-md-3 well" id="itemDiv">
                            <div class="row text-center">
                                <c:out value="[${item.id}]"></c:out><br>
                                <c:out value="${item.name}"></c:out><br>
                                <c:out value="Cost: ${item.cost}"></c:out><br>
                                <c:out value="Stock: ${item.stock}"></c:out>
                                    <form method="post" action="select">
                                        <button type="submit" class="btn btn-default" name="item" value="${item.id}">
                                        Select Item
                                    </button>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-1"></div>
                    </c:forEach>
                </div>
                <div class="col-md-3 text-center">
                    <form method="post" action="addMoney">
                        <div class="row">
                            <div class="col-md-12" id="total-in">
                                <h2>Total $ In</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12" id="total">
                                <div class="col-md-12" id="total-money">
                                    <input type="text" class="form-group alert alert-info" name="total" 
                                           value="<c:out value='${total}'></c:out>" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group" >
                                <div class="col-md-6 ">
                                    <button type="submit"
                                            name="money"
                                            class="addMoney btn btn-primary"
                                            value="1">
                                        $1</button>
                                </div>
                                <div class="col-md-6">
                                    <button type="submit"
                                            name="money"
                                            class="addMoney btn btn-primary"
                                            value=".25">
                                        .25</button>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-6">
                                    <button type="submit"
                                            name="money"
                                            class="addMoney btn btn-primary"
                                            value=".10">
                                        .10</button>
                                </div>
                                <div class="col-md-6">
                                    <button type="submit"
                                            name="money"
                                            class="addMoney btn btn-primary"
                                            value=".05">
                                        .05</button>
                                </div>
                            </div>
                        </form>
                        <hr/>
                        <div class="row">
                            <div class="col-md-12" id="messages">
                                <h2>Messages</h2>
                            </div>
                        </div>
                        <div class="row">
                            <input form="text"
                                   id="goodMessage"
                                   class="form-group col-md-12 alert alert-warning text-center" value="<c:out value="${message}"></c:out>" readonly>
                        </input>
                        </div>
                        <div class="row">
                            <div class="col-md-4 form-group" id="itemLabel">
                                <h4>Item:</h4>
                            </div>
                            <div class="col-md-8" id="selected-id">
                                <input form="text"
                                       placeholder="None Selected"
                                       id="selected"
                                       class="form-group col-md-12 alert alert-info text-center"
                            value="<c:out value='${pickedId}'></c:out>"></input>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <form method="post" action="makePurchase">
                                    <button type="submit"
                                            id="purchase"
                                            class="btn btn-primary">
                                        Purchase</button>
                                </form>
                            </div>
                        </div>
                        <hr/>
                        <div class="row">
                            <div class="col-md-12" id="changeLabel">
                                <h3>Change</h3>
                            </div>
                        </div>
                    <c:if test="${changeList[0] > 0}">
                        <div class="row" id="quarter">
                            <div class="col-md-8 form-group" id="quarterLabel">
                                <h4>No. of Quarters:</h4>
                            </div>
                            <div class="col-md-4 form-group" id="quarterNum">
                                <h4>${changeList[0]}</h4>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${changeList[1] > 0}">
                        <div class="row" id="dime">
                            <div class="col-md-8 form-group" id="dimeLabel">
                                <h4>No. of Dimes:</h4>
                            </div>
                            <div class="col-md-4 form-group" id="dimeNum">
                                <h4>${changeList[1]}</h4>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${changeList[2] > 0}">
                        <div class="row" id="nickel">
                            <div class="col-md-8 form-group" id="nickelLabel">
                                <h4>No. of Nickels:</h4>
                            </div>
                            <div class="col-md-4 form-group" id="nickelNum">
                                <h4>${changeList[2]}</h4>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${changeList[3] > 0}">
                        <div class="row" id="penny">
                            <div class="col-md-8 form-group" id="pennyLabel">
                                <h4>No. of Pennies:</h4>
                            </div>
                            <div class="col-md-4 form-group" id="pennyNum">
                                <h4>0</h4>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-md-4 col-md-offset-3">
                            <form method="post" action="getChange">
                                <button id="changeButton"
                                        type="submit"
                                        class="btn btn-primary">
                                    Give Change</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>


