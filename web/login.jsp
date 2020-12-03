<%-- 
    Document   : login
    Created on : Oct 7, 2020, 7:32:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yellow Moon - Login</title>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="954882030954-9uul2j13tkf2aqp83jehllln2b1tj4e2.apps.googleusercontent.com">
        <script src="public/js/login.js"></script>
        <script src="public/js/jquery/jquery-3.5.1.min.js"></script>
    </head>
    <body>

        <%@include file="header.jsp"%>

        <div class="columns is-centered">
            <nav class="column is-3">
                <p class="panel-heading has-text-centered">
                    Login
                </p>
                <form action="login" class="mt-2" method="POST">
                    <div class="field">
                        <label class="label">Email</label>
                        <div class="control">
                            <input type="text" name="email" value="${requestScope.ACCOUNT.email}" class="input" placeholder="Email"/>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Password</label>
                        <div class="control">
                            <input type="password" name="password" class="input" placeholder="********"/>
                        </div>
                    </div>
                    <div class="field control is-grouped is-grouped-centered">
                        <input type="submit" class="button is-primary" value="Login"/>
                    </div>
                    <div class="field control is-grouped is-grouped-centered">
                        OR
                    </div>
                    <div class="field control is-grouped is-grouped-centered">
                        <div class="g-signin2" data-onsuccess="onSignIn"></div>
                    </div>
                </form>
            </nav>
        </div>

    </body>
</html>
