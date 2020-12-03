<%-- 
    Document   : addCake
    Created on : Oct 8, 2020, 8:30:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>YellowMoon - Add new Cake</title>
        <script src="public/js/jquery/jquery-3.5.1.min.js"></script>
        <script src="public/js/jquery/jquery.validate.min.js"></script>
        <script src="public/js/jquery/additional-methods.min.js"></script>
        <link rel="stylesheet" type="text/css" href="public/css/bulma.min.css" />
        <link rel="stylesheet" type="text/css" href="public/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="public/css/index.css" />
    </head>
    <body>

        <%@include file="header.jsp"%>

        <div class="columns is-centered">
            <nav class="column is-6">
                <p class="panel-heading has-text-centered">
                    Add new cake
                </p>
                <br>
                <form action="addCake" method="POST" enctype="multipart/form-data" id="add-cake-form">
                    <div class="field is-horizontal">
                        <div class="field-body">
                            <div class="field">
                                <label class="label">Id Cake</label>
                                <div class="control">
                                    <input type="text" name="idCake" id="idCake" class="input" placeholder="Cake-xxx" 
                                           value="${requestScope.CAKE.idCake}" required minlength="4" maxlength="10" pattern="Cake-[0-9]{3}">
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Name cake</label>
                                <div class="control">
                                    <input type="text" name="nameCake" class="input" placeholder="Name cake" 
                                           value="${requestScope.CAKE.nameCake}" required minlength="2" maxlenght="100">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="field is-horizontal">
                        <div class="field-body">
                            <div class="field">
                                <label class="label">Price</label>
                                <div class="control">
                                    <input type="number" name="price" class="input" placeholder="Price" 
                                           value="${requestScope.CAKE.price}" required min="0">
                                </div>
                            </div>
                            <div class="field">
                                <label class="label">Quantity</label>
                                <div class="control">
                                    <input type="number" class="input" name="quantity" value="${requestScope.CAKE.quantity}" required min="1"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Description</label>
                        <div class="control">
                            <textarea type="text" name="descCake" class="textarea"
                                      rows="2" placeholder="Description" maxlength="1000">${requestScope.CAKE.descCake}</textarea>
                        </div>
                    </div>

                    <div class="field is-horizontal">
                        <div class="field-body">
                            <div class="field">
                                <label class="label">Create Date</label>
                                <div class="control">
                                    <input type="date" name="createDate" class="input" 
                                           value="${requestScope.CAKE.createDate}" required/>
                                </div>
                            </div>

                            <div class="field">
                                <label class="label">Expiration Date</label>
                                <div class="control">
                                    <input type="date" name="expirationDate" class="input" value="${requestScope.CAKE.expirationDate}" required/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="field is-horizontal">
                        <div class="field-body">
                            <div class="field has-addons">
                                <div class="control is-expanded">
                                    <label class="label">Category</label>
                                    <div class="select is-fullwidth">
                                        <select name="idCategory" id="categories">
                                        </select><br/>
                                    </div>
                                </div>
                            </div>

                            <div class="field">
                                <label class="label">Image</label>
                                <div class="file has-name">
                                    <label class="file-label">
                                        <span class="file-cta">
                                            <span class="file-icon">
                                                <i class="fa fa-upload"></i>
                                            </span>
                                            <span class="file-label">
                                                Choose a file…
                                            </span>
                                        </span>
                                        <span class="file-name">
                                            No file choosen
                                        </span>
                                    </label>
                                    <input class="file-input" type="file" name="imageCake" accept="image/png, image/jpeg" required>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="field control">
                        <input type="submit" class="button is-primary" value="Add Cake"/>
                    </div>
                </form>

            </nav>
        </div>

        <script src="public/js/addCake.js"></script>
    </body>
</html>
