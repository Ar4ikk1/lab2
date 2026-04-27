<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Автопарк - Список</title>
    <style>
        * {
            box-sizing: border-box;
            font-weight: normal !important;
            font-family: 'Segoe UI';
        }

        body {
            background-color: #f4f7f9;
            margin: 0;
            padding-top: 40px;
            color: #333;
        }

        .container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px #e0e0e0;
            width: 1000px;
            margin: 0 auto;
        }

        h1 {
            color: #2c3e50;
            margin-top: 0;
            margin-bottom: 20px;
            font-size: 24px;
        }

        .top-bar {
            margin-bottom: 20px;
            overflow: hidden;
        }

        .btn-add {
            float: left;
            display: inline-block;
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn-add:hover { background-color: #218838; }

        .search-container {
            float: right;
            text-align: right;
        }

        .search-box {
            display: inline-block;
            background: #fff;
            border: 1px solid #cccccc;
            border-right: none;
            border-radius: 5px 0 0 5px;
            padding: 7px 15px;
            width: 250px;
            vertical-align: top;
        }

        .search-box input {
            border: none;
            outline: none;
            width: 100%;
            font-size: 14px;
        }

        .btn-search {
            display: inline-block;
            background-color: #28a745;
            color: white;
            border: 1px solid #28a745;
            padding: 8px 20px;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            vertical-align: top;
        }
        .btn-search:hover { background-color: #218838; }

        .clear-btn {
            display: inline-block;
            margin-left: 10px;
            color: #dc3545;
            text-decoration: none;
            padding-top: 8px;
            vertical-align: top;
        }

        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { text-align: left; padding: 12px; border-bottom: 1px solid #eeeeee; }
        th {
            background-color: #f8f9fa;
            color: #555555;
            text-transform: uppercase;
            font-size: 11px;
            letter-spacing: 0.5px;
        }
        tr:hover { background-color: #fafafa; }

        .depot-badge {
            color: #495057;
        }

        .actions a { color: #007bff; text-decoration: none; margin-right: 10px; font-size: 14px; }
        .actions button {
            color: #dc3545;
            border: none;
            background: none;
            cursor: pointer;
            font-size: 14px;
            padding: 0;
        }
        .actions a:hover, .actions button:hover { text-decoration: underline; }
    </style>
</head>
<body>

<div class="container">
    <h1>Список автомобілів</h1>

    <div class="top-bar">
        <a href="${pageContext.request.contextPath}/cars/addCar" class="btn-add"> + Додати авто</a>

        <form action="${pageContext.request.contextPath}/cars" method="get" class="search-container">
            <div class="search-box">
                <input type="text" name="brand" value="${param.brand}" placeholder="Шукати за брендом">
            </div><button type="submit" class="btn-search">Пошук</button>

            <c:if test="${not empty param.brand}">
                <a href="${pageContext.request.contextPath}/cars" class="clear-btn">✕</a>
            </c:if>
        </form>
    </div>

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
                <td>${car.brand}</td>
                <td>${car.model}</td>
                <td>${car.licensePlate}</td>
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