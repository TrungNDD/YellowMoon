<%-- 
    Document   : newjsp
    Created on : Oct 14, 2020, 8:21:10 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>YellowPage - Track Order</title>
        <script src="public/js/jquery/jquery-3.5.1.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <fmt:setLocale value="vi_VN"/>

        <c:if test="${requestScope.ORDERS != null}" var="isEmpty">
            <nav class="columns" style="padding:1em;">
                <div class="column is-3 is-offset-5 is-centered field has-addons">
                    <input class="input" id="searchOrder" type="text" placeholder="Order Id">
                    <p class="control">
                        <button type="submit" value="Search" class="button">
                            Search
                        </button>
                    </p>
                </div>
            </nav>

            <table class="table is-fullwidth is-hoverable" id="order-table">
                <thead style="background-color: #f5f5f5">
                    <tr>
                        <th>OrderID</th>
                        <th>Email</th>
                        <th>Order Date</th>
                        <th>Fullname</th>
                        <th>Phone</th>
                        <th>ShipAddress</th>
                        <th>Payment method</th>
                        <th>Payment Status</th>
                        <th>Order status</th>
                        <th>Total</th>
                    </tr>
                </thead>

                <c:forEach var="order" items="${requestScope.ORDERS}">
                    <tr class="order-row" id="${order.idOrder}" style="border-top: 1px solid #dbdbdb">
                        <td>${order.idOrder}</td>
                        <td>${order.email.email}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.fullname}</td>
                        <td>${order.phone}</td>
                        <td>${order.shipAddress}</td>
                        <td>${order.paymentMethod}</td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.orderStatus}</td>
                        <td><fmt:formatNumber value="${order.total}" type="currency"  currencySymbol="&#8363"/></td>
                    </tr>
                    <c:forEach var="details" items="${order.orderDetailsCollection}" varStatus="counter">
                        <tr class="${order.idOrder}" style="display: none; border: none">
                            <td colspan="4" style="border:none"></td>
                            <td><b>Item#${counter.count}</b></td>
                            <td style="font-style: bold">${details.cake.nameCake} (x${details.quantityOrder})</td>
                            <td>
                                <fmt:formatNumber value="${details.price * details.quantityOrder}" type="currency" currencySymbol="&#8363"/>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${not isEmpty}">
            No order has been made
        </c:if>
    </body>
    <script>
        $('#searchOrder').on('keyup', function () {
            var value = $(this).val().toLowerCase();
            console.log(value);
            $("#order-table .order-row").filter(function () {
                var id = $(this).attr('id');
                $(this).toggle(id.toLowerCase().indexOf(value) > -1)
            });
        })

        $('.order-row').click(function () {
            if ($(this).hasClass("is-selected")) {
                $(this).removeClass('is-selected');
            } else {
                $(this).addClass("is-selected");
            }
            var idOrder = "." + $(this).attr("id");
            $(idOrder).toggle();
        })
    </script>
</html>
