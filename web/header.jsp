<%-- 
    Document   : header
    Created on : Oct 11, 2020, 9:03:57 AM
    Author     : Admin
--%>

<link rel="stylesheet" type="text/css" href="public/css/bulma.min.css" />
<link rel="stylesheet" type="text/css" href="public/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="public/css/index.css" />
<meta name="google-signin-client_id" content="954882030954-9uul2j13tkf2aqp83jehllln2b1tj4e2.apps.googleusercontent.com">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${sessionScope.ACCOUNT == null}" var="isGuest" scope="page"/>
<c:set value="${sessionScope.ACCOUNT.role eq 'admin'}" var="isAdmin" scope="page"/>
<c:set value="${sessionScope.ACCOUNT.role eq 'member'}" var="isMember" scope="page"/>
<script>
    function onLoad() {
        gapi.load('auth2', function () {
            gapi.auth2.init();
        });
    }

    onLoad();
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }
</script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

<!-- Main container -->
<nav class="level" style="background-color: #f5f5f5; padding:2em;">
    <!-- Left side -->
    <div class="level-left">
        <div class="level-item">
            <a href="indexPage" class="title is-2">
                <strong>YellowMoon</strong>
            </a>
        </div>
    </div>

    <!-- Right side -->
    <div class="level-right">

        <c:if test="${pageScope.isGuest}">
            <p class="level-item"><a href="loginPage">Login</a></p>
        </c:if>

        <c:if test="${not pageScope.isGuest}">
            <p class="level-item">${sessionScope.ACCOUNT.email},</p>
            <p class="level-item"><a href="logout" onclick="signOut();">Log out</a></p>
        </c:if>

        <c:if test="${not pageScope.isAdmin}">
            <p class="level-item"><a href="viewCartPage" class="button is-success fa fa-shopping-bag"></a></p>
            </c:if>

        <c:if test="${pageScope.isAdmin}">
            <p class="level-item"><a href="addCakePage" class="button is-success">New cake</a></p>
        </c:if>

        <c:if   test="${pageScope.isMember}">
            <p class="level-item"><a href="trackOrder" class="button is-success">Track Order</a></p>
        </c:if>
    </div>
    <c:if test="${requestScope.MESSAGE != null}">
        <div class="columns has-text-centered" id="message">
            <div class="column">
                <div class="button is-medium is-3 disabled is-danger notification">
                    <button class="delete" id="delete-message"></button>${requestScope.MESSAGE}
                </div>
            </div>
        </div>
        <script>
            $('#delete-message').click(function () {
                $('#message').fadeOut(200);
            })
        </script>
    </c:if>
</nav>


