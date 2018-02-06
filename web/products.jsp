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
        <h1>Products</h1>
        <table>
            <tr>
                <th>Code</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th></th>
                <th></th>
            </tr>
            <!-- Hint! Remember to code for the 'Edit' and 'Delete' links -->
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value="${product.code}" /></td>
                    <td><c:out value="${product.description}" /></td>
                    <td class="right"><c:out value="${product.price}" /></td>
                    <td><a href="ProdMaint?action=addProd&prodId=${product.id}">Edit</a></td>
                    <td><a href="ProdMaint?action=deleteProd&prodId=${product.id}">Delete</a></td>
                </tr>
            </c:forEach>    
        </table>
        <br />
        <!-- Hint! You need to code a form for the 'Add Product' button -->
        <form action="ProdMaint?action=addProd" method="post">
            <input type="submit" value="Add Product"/><br>
        </form>
    </body>
</html>