<%-- 
    Document   : viewCart
    Created on : Oct 12, 2020, 4:31:32 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>YellowMoon - View cart</title>
        <link rel="stylesheet" type="text/css" href="public/css/bulma.min.css" />
        <link rel="stylesheet" type="text/css" href="public/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="public/css/index.css" />
        <script src="public/js/jquery/jquery-3.5.1.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp"%>

        <c:set var="cart" value="${sessionScope.CART}"/>
        <fmt:setLocale value="vi_VN"/>

        <section id="theGrid" style="width: 100%; margin-bottom: 5%">
            <div class="tile is-ancestor">
                <div class="tile is-10"> 
                    <div class="columns is-multiline">
                        <c:if test="${cart != null}" var="hasCart">
                            <c:if test="${not empty cart}" var="isNotEmpty">
                                <c:forEach var="entry" items="${cart}">
                                    <c:set var="cake" value="${entry.value}"/>
                                    <div class="column is-5 ml-4 mr-2">
                                        <div class="tile is-ancestor box">
                                            <div class="tile is-4 image">
                                                <img style="object-fit: fill" src="${cake.imageCake}"/>
                                            </div>
                                            <div class="tile is-parent is-vertical pl-5">
                                                <div class="tile is-child">
                                                    <p class="title">${cake.nameCake}</p>
                                                    <p class="subtitle">${cake.idCategory.nameCate}</p>
                                                    <p>Price: <fmt:formatNumber value="${cake.price}" type="currency"  currencySymbol="&#8363"/></p>
                                                </div>
                                                <div class="tile is-child">
                                                    <form action="shoppingCart" method="GET" class="field has-addons">
                                                        <input type="hidden" name="idCake" value="${cake.idCake}"/>
                                                        <input type="number" name="quantity" min="1" max="10" class="input" value="${cake.quantity}"/>
                                                        <input type="submit" name="action" class="button is-primary is-outlined" value="Update"/>
                                                        <a href="shoppingCart?action=remove&idCake=${cake.idCake}" class="button is-danger is-outlined remove-cake">Remove</a>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </c:if>
                        <c:if test="${not hasCart or not isNotEmpty}">
                            <div class='tile column is-full subtitle content has-text-centered' style="margin-left: 230%; margin-top: 10%">
                                No cake in this shopping cart yet.
                            </div>
                        </c:if>

                        <!-- to avoid 1 item view defect -->
                        <c:if test="${cart.size() == 1 or cart.size() == 4 or cart.size() == 3 }">
                            <div class="column is-5 ml-4 mr-2 my-2" style="visibility: hidden">
                                <div class="tile is-ancestor box">
                                    <div class="tile is-4 image">
                                        <img style="object-fit: fill" src="${cake.imageCake}"/>
                                    </div>
                                </div>
                            </div> 
                            <div class="column is-5 ml-4 mr-2 my-2" style="visibility: hidden">
                                <div class="tile is-ancestor box">
                                    <div class="tile is-4 image">
                                        <img style="object-fit: fill" src="${cake.imageCake}"/>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <c:if test="${isNotEmpty}">
                    <c:set var="account" value="${sessionScope.ACCOUNT}"/>
                    <div class="box is-parent order-detail">
                        <div class="tile is-child">
                            <div class="field has-text-centered title" style="font-size: 30px">
                                Billing Information
                            </div>
                            <form action="saveCart" method="POST">
                                <div class="field">
                                    <label class="label">Name</label>
                                    <div class="control">
                                        <input class="input" type="text" placeholder="Name" name="fullname" value="${account.fullname}" required maxlength="100">
                                    </div>
                                </div>
                                <div class="field">
                                    <label class="label">Phone</label>
                                    <div class="control">
                                        <input class="input" type="text" placeholder="Phone (xxxxxxxxxx)" name="phone" value="${account.phone}" required pattern="[0-9]{10,11}">
                                    </div>
                                </div>
                                <div class="field">
                                    <label class="label">Address</label>
                                    <div class="control">
                                        <textarea class="textarea" type="text" placeholder="Address" name="address" rows="2" required maxlength="1000">${account.address}</textarea>
                                    </div>
                                </div>

                                <label class="label">Payment method</label>
                                <div class="select field">
                                    <select name="payment">
                                        <option value="by cash">By cash upon delivery</option>
                                        <option value="by google pay">By Google Pay</option>
                                    </select>
                                </div>
                                <c:forEach var="entry" items="${cart}">
                                    <c:set var="cake" value="${entry.value}"/>
                                    <div class="field" style="font-size: 20px">
                                        <span>${cake.nameCake} (x${cake.quantity})</span> 
                                        <span style="float: right"><fmt:formatNumber value="${cake.price * cake.quantity}" type="currency" currencySymbol="&#8363"/></span>
                                    </div>
                                </c:forEach>
                                <div class="field has-text-centered" style="font-size: 30px">
                                    <b>Total:</b> <fmt:formatNumber value="${cart.getTotal()}" type="currency" currencySymbol="&#8363"/>
                                </div>
                                <div class="field has-text-centered">
                                    <input class="button is-success is-outlined" type="submit" value="Submit"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:if>
            </div>
        </section>
        <script>
            // confirm before remove cake
            $(".remove-cake").click(function () {
                return confirm("Are you sure to remove this cake from cart?")
            });
        </script>
    </body>
</html>
