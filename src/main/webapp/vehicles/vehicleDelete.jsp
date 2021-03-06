<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: milosz
  Date: 06.07.18
  Time: 01:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="/fragments/header.jsp"  %>
<form method='post' action=''>
    <div> <label>Delete vehicle.</label></div>
    <input type='text' name='id' min=1 required/>
    <input type='submit' value='Delete.'/>
</form>
<h3>Vehicles list:</h3>
<div>
    <ul>
        <c:forEach var="vehicle" items="${allVehicles}">
            <li>
                <h6>Id: ${vehicle.id},
                    Car: ${vehicle.brand} ${vehicle.model},
                    Registration number: ${vehicle.registrationNumber},
                    Car owner's id: ${vehicle.client_id}

                </h6>

            </li>
        </c:forEach>
    </ul>
</div>

<%@ include file="/fragments/footer.jsp"  %>
</body>
</html>
