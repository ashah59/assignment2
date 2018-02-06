<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tlds/tag_library.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Product Maintenance</title>
        <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
    </head>
    <body>
        <h1>Product</h1>
        <p><tags:RequiredFieldTag fieldName="" /> marks required fields</p>
        <p><i>${message}</i></p>
        <form style="display: inline" action="ProdMaint?action=updateProd&prodId=${product.id}" method="post">
            <!-- You need to supply the required attributes -->
            <!-- You need to supply all required input types, including the 'Update Product' button -->
            <label class="pad_top">Code:</label>
            <input class="pad_top" type="text" name="code" value="${product.code}">
            <tags:RequiredFieldTag fieldName="${product.code}" />

            <label class="pad_top">Description:</label>
            <input class="pad_top" type="text" name="desc" value="${product.description}">
            <tags:RequiredFieldTag fieldName="${product.description}" />

            <label class="pad_top">Price:</label>
            <input class="pad_top" type="text" name="price" value="${product.price >= 0.0 ? product.price : ''}">
            <tags:RequiredFieldTag fieldName="${product.price >= 0.0 ? product.price : ''}" />

            <label class="pad_top">&nbsp;</label> <br>
            <input type="submit" value="Update Product"/>
        </form>
        <!-- Hint! You need to code a form for the 'View Product' button -->
        <form class="formInline" action="ProdMaint?action=displayProd" method="post">
            <input type="submit" value="View Products"/><br>
        </form>
    </body>
</html>