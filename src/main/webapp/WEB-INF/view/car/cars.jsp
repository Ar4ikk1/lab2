<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Автопарк - Список</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f9; padding: 40px; display: flex; justify-content: center; }
        .container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 100%; max-width: 900px; }
        h1 { color: #2c3e50; margin-bottom: 20px; }
        .btn-add {
            background-color: #28a745; color: white; padding: 10px 20px;
            text-decoration: none; border-radius: 5px; font-weight: bold; display: inline-block; margin-bottom: 20px;
        }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { text-align: left; padding: 12px; border-bottom: 1px solid #eee; }
        th { background-color: #f8f9fa; color: #555; text-transform: uppercase; font-size: 12px; }
        tr:hover { background-color: #fafafa; }
        .depot-badge { background: #e9ecef; padding: 4px 8px; border-radius: 4px; font-weight: bold; color: #495057; }
        .actions a { color: #007bff; text-decoration: none; margin-right: 10px; font-size: 14px; }
        .actions button { color: #dc3545; border: none; background: none; cursor: pointer; font-size: 14px; padding: 0; }
        .actions a:hover, .actions button:hover { text-decoration: underline; }
    </style>
</head>
<body>

<div class="container">
    <h1>Список автомобілів</h1>
    <a href="${pageContext.request.contextPath}/cars/addCar" class="btn-add"> + Додати авто</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Бренд</th>
            <th>Модель</th>
            <th>Номер</th>
            <th>ID Депо</th>
            <th>Дії</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td>${car.id}</td>
                <td><strong>${car.brand}</strong></td>
                <td>${car.model}</td>
                <td><code>${car.licensePlate}</code></td>
                <td><span class="depot-badge">${car.carPark.id}</span></td>
                <td class="actions">
                    <a href="${pageContext.request.contextPath}/cars/editCar/${car.id}">Редагувати</a>
                    <form action="${pageContext.request.contextPath}/cars/delete/${car.id}" method="post" style="display:inline;">
                        <button type="submit">Видалити</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>