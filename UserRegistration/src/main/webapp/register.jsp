<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<h2>User Registration Form</h2>

<form action="registration" method="get">
    Name: <input type="text" name="name"><br><br>
    Address: <input type="text" name="address"><br><br>
    Age: <input type="number" name="age"><br><br>

    Gender:
    <input type="radio" name="gender" value="Male">Male
    <input type="radio" name="gender" value="Female">Female
    <br><br>

    City:
    <select name="city">
        <option value="">Select</option>
        <option>Delhi</option>
        <option>Mumbai</option>
        <option>Bangalore</option>
    </select>
    <br><br>

    <input type="submit" value="Register">
</form>

<font color="red">
    <%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %>
</font>

<font color="green">
    <%= request.getAttribute("success") == null ? "" : request.getAttribute("success") %>
</font>

</body>
</html>