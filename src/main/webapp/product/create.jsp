<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 10/8/2021
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <style>
        a {
            text-decoration: none;
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
    <h3>Create product</h3>
    <hr>
    <form method="post">
        <div class="mb-3">
            <label>Product name : </label>
            <input class="form-control" type="text" placeholder="Enter product name" name="name">
        </div>
        <div class="mb-3">
            <label>Product price : </label>
            <input class="form-control" type="number" placeholder="Enter product price" name="price" value="0">
        </div>
        <div class="mb-3">
            <label>Product quantity : </label>
            <input class="form-control" type="text" placeholder="Enter product quantity" name="quantity" value="0">
        </div>
        <div class="mb-3">
            <label>Product color : </label>
            <input class="form-control" type="text" placeholder="Enter product color" name="color">
        </div>
        <div class="mb-3">
            <label>Product description : </label>
            <input class="form-control" type="text" placeholder="Enter product description" name="description">
        </div>
        <div class="mb-3">
            <label>Category : </label>
            <select name="category" class="form-select" >
                <option value="1">Dien thoai</option>
                <option value="2">Lap top</option>
            </select>
        </div>
        <p>${message}</p>
        <button class="btn btn-outline-primary">Create</button>
    </form>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
            crossorigin="anonymous"></script>
</body>
</html>
