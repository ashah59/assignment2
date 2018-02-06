<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Product Maintenance</title>
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
    </head>
    <body>
        <h1>Are you sure you want to delete this product?</h1>

        <label>Code:</label><span>${product.code}</span>
        <br>
        <label>Description:</label><span>${product.description}</span>
        <br>
        <label>Price:</label><span>${product.price}</span>
        <br>
        
        <!-- Hint! You need to code a form for the 'Yes' button -->
        <form class="formInline" action="ProdMaint?action=confDelProd&prodId=${product.id}" method="post">
            <input type="submit" value="Yes"/>
        </form>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <!-- Hint! You need to code a form for the 'No Product' button -->
        <form class="formInline" action="ProdMaint?action=displayProd" method="post">
            <input type="submit" value="No"/>
        </form>
    </body>
</html>