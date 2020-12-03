<%-- 
    Document   : index
    Created on : Oct 7, 2020, 7:28:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>YellowMoon - Mainpage</title>
        <link rel="stylesheet" type="text/css" href="public/css/bulma.min.css" />
        <link rel="stylesheet" type="text/css" href="public/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="public/css/index.css" />
        <script src="public/js/jquery/jquery-3.5.1.min.js"></script>
        <script src="public/js/pagination.js"></script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div id="role" hidden>${sessionScope.ACCOUNT.role}</div>
        
        <nav class="level" style="padding:1em;">
            <!-- Left side -->
            <div class="level-item">
                <div class="field has-addons">
                    <form class="control" id="search-field" action="searchCake">
                        <input type="hidden" name="searchBy" value="name"/>
                        <input class="input" name="txtSearch" type="text" placeholder="Find a cake">
                    </form>
                    <p class="control">
                        <button type="submit" form="search-field" value="Search" class="button">
                            Search
                        </button>
                    </p>
                    <div class="dropdown is-active">
                        <div class="dropdown-trigger">
                            <button class="button" aria-haspopup="true" aria-controls="dropdown-menu">
                                <span id="display-dropdown">By name</span>
                                <span class="icon is-small">
                                    <i class="fas fa fa-angle-down" aria-hidden="true"></i>
                                </span>
                            </button>
                        </div>
                        <div class="dropdown-menu" id="dropdown-menu" role="menu" style="display: none">
                            <div class="dropdown-content">
                                <a class="dropdown-item" id="search-by-name">
                                    By name
                                </a>
                                <a class="dropdown-item" id="search-by-price">
                                    By price
                                </a>
                                <a class="dropdown-item" id="search-by-category">
                                    By category
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>

    <section id="theGrid" style="width: 100%; margin-bottom: 5%">
        <div class="columns is-multiline" id="grid-container">            

        </div>
    </section>

    <script src="public/js/index.js"></script>
</body>
</html>
