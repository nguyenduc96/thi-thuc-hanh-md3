<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 10/8/2021
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>List Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <style>
        a {
            text-decoration: none;
        }
        .btn-outline-primary :hover {
            color: #fff;
        }
        button {
            min-width: 100px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="product">Product management</a>
                </li>
            </ul>
            <form action="product?action=search" method="post" class="d-flex">
                <input type="text" placeholder="Enter name" name="search" value="" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">search</button>
            </form>
        </div>
    </div>
</nav>

<div class="container">
    <h3>Product list</h3>
    <button class="btn btn-outline-primary">
        <a href="product?action=create">Create new product</a>
    </button>
    <table class="table table-striped">
        <thead>
        <th>#</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Description</th>
        <th>Category</th>
        </thead>

        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td><ftm:formatNumber value="${product.price}"></ftm:formatNumber></td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.description}</td>
                <td>${product.category.getName()}</td>
                <td>
                    <button class="btn btn-outline-warning"><a href="product?action=edit&id=${product.id}">Edit</a></button>
                </td>
                <td>
                    <button class="btn btn-outline-danger"><a onclick="confirmDelete(${product.id})">DELETE</a></button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function confirmDelete(id) {
        let checkConfirm = confirm("Are you sure delete?");
        if (checkConfirm) {
            window.location.href = "product?action=delete&id=" + id;
            alert("Product was deleted!!!")
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
