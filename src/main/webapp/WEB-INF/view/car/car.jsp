<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>${(car != null && car.id != 0) ? 'Редагування' : 'Додавання'}</title>
    <style>
        * {
            box-sizing: border-box;
            font-weight: normal !important;
            font-family: 'Segoe UI';
        }

        body {
            background-color: #f4f7f9;
            margin: 0;
            padding-top: 50px;
            color: #333;
        }

        .container {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px #e0e0e0;
            width: 500px;
            margin: 0 auto;
        }

        h2 {
            color: #2c3e50;
            margin-top: 0;
            margin-bottom: 25px;
            text-align: center;
            font-size: 22px;
        }

        .form-group {
            margin-bottom: 15px;
            width: 100%;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #444444;
            font-size: 15px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            display: block;
            font-size: 14px;
        }

        input:focus {
            border-color: #007bff;
            outline: none;
        }

        .error {
            color: #dc3545;
            font-size: 13px;
            margin-top: 5px;
            display: block;
        }

        .btn-submit {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
            font-size: 16px;
            transition: background 0.2s;
        }

        .btn-submit:hover {
            background-color: #218838;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #666666;
            text-decoration: none;
            font-size: 14px;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>${(car != null && car.id != 0) ? 'Редагувати авто' : 'Новий автомобіль'}</h2>

    <c:set var="action" value="${pageContext.request.contextPath}/cars/addCar"/>
    <c:if test="${car != null && car.id != 0}">
        <c:set var="action" value="${pageContext.request.contextPath}/cars/editCar"/>
    </c:if>

    <form action="${action}" method="post">
        <input type="hidden" name="id" value="${car.id}">

        <div class="form-group">
            <label>Бренд:</label>
            <input type="text" name="brand" value="<c:out value='${car.brand}'/>">
            <c:if test="${not empty errors.messages['brand']}">
                <span class="error">${errors.messages['brand']}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label>Модель:</label>
            <input type="text" name="model" value="<c:out value='${car.model}'/>">
            <c:if test="${not empty errors.messages['model']}">
                <span class="error">${errors.messages['model']}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label>Колір:</label>
            <input type="text" name="color" value="<c:out value='${car.color}'/>">
            <c:if test="${not empty errors.messages['color']}">
                <span class="error">${errors.messages['color']}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label>Номерний знак:</label>
            <input type="text" name="licensePlate" value="<c:out value='${car.licensePlate}'/>">
            <c:if test="${not empty errors.messages['licensePlate']}">
                <span class="error">${errors.messages['licensePlate']}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label>Рік випуску:</label>
            <input type="number" name="year" value="<c:out value='${car.year}'/>">
            <c:if test="${not empty errors.messages['year']}">
                <span class="error">${errors.messages['year']}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label>Автопарк (ID Депо):</label>
            <select name="carParkId">
                <option value="">-- Оберіть парк --</option>
                <c:forEach var="park" items="${carParks}">
                    <option value="${park.id}" ${(car.carPark != null && car.carPark.id == park.id) ? 'selected' : ''}>
                            ${park.name} (ID: ${park.id})
                    </option>
                </c:forEach>
            </select>
            <c:if test="${not empty errors.messages['carParkId']}">
                <span class="error">${errors.messages['carParkId']}</span>
            </c:if>
        </div>

        <button type="submit" class="btn-submit">Зберегти дані</button>
        <a href="${pageContext.request.contextPath}/cars" class="back-link">← Повернутися до списку</a>
    </form>
</div>

</body>
</html>