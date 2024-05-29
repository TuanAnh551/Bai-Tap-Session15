<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 5/22/2024
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<h1>Danh sách sản phẩm</h1>

<a href="/product?productAction=ADD" class="btn btn-light">Thêm mới sp</a><br>
<a href="/Category?categoryAction=LIST" class="btn btn-info">Quay lại</a><br>

<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Created</th>
        <th scope="col">Pages</th>
        <th scope="col">Author</th>
        <th scope="col">Status</th>
        <th scope="col" colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product" varStatus="loop">
        <tr>
            <th scope="row">${loop.count}</th>
            <td>${product.product_name}</td>
            <td>${product.product_price}</td>
            <td>${product.yearCreate}</td>
            <td>${product.totalPages}</td>
            <td>${product.product_author}</td>
            <td>${product.product_status}</td>
            <td><a href="/product?productAction=EDIT&id=${product.product_id}" class="btn btn-warning">Edit</a></td>
            <td><a href="/product?productAction=DELETE&id=${product.product_id}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xoa ko')">Delete</a></td>
        </tr>
    </c:forEach>


    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>
