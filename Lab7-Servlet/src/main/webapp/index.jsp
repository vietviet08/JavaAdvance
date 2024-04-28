<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Students</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>

<style>
    .group-action i {
        font-size: 18px;
        margin-right: 10px;
    }
</style>

<body>

<div class="container">
    <h1 class="text-center"><%= "Students" %>
    </h1>

    <div class="account">
        <p>
        </p>
        <a href="/logout">Logout</a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Birth Date</th>
            <th scope="col">GPA</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <th scope="row"><c:out value="${student.id}" /></th>
                <td><c:out value="${student.name}" /></td>
                <td><c:out value="${student.birthDay}" /></td>
                <td><c:out value="${student.gpa}" /></td>
                <td>
                    <div class="group-action d-flex align-items-center">
                        <a href="edit?id=<c:out value='${student.id}' />">
                            <i class="fa-regular fa-pen-to-square text-primary"></i>
                        </a>
                        <a href="delete?id=<c:out value='${student.id}' />">
                            <i class="fa-regular fa-trash-can text-danger"></i>
                        </a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>