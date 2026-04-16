<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>${not empty car.id ? 'Редагування' : 'Додавання'}</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f9; padding: 40px; display: flex; justify-content: center; }
        .container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 100%; max-width: 500px; }
        h2 { color: #2c3e50; margin-bottom: 25px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: 600; margin-bottom: 5px; color: #444; }
        input, select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 5px; box-sizing: border-box; }
        input:focus { border-color: #007bff; outline: none; }
        .error { color: #dc3545; font-size: 13px; margin-top: 5px; display: block; }
        .btn-submit { background-color: #007bff; color: white; border: none; padding: 12px 20px; border-radius: 5px; cursor: pointer; font-weight: bold; width: 100%; margin-top: 10px; }
        .btn-submit:hover { background-color: #0056b3; }
        .back-link { display: block; text-align: center; margin-top: 15px; color: #666; text-decoration: none; font-size: 14px; }
    </style>
</head>
<body>

<div class="container">
    <h2>${not empty car.id ? 'Редагувати авто' : 'Новий автомобіль'}</h2>

    <c:set var="action" value="${pageContext.request.contextPath}/cars/addCar"/>
    <c:if test="${not empty car.id}">
        <c:set var="action" value="${pageContext.request.contextPath}/cars/editCar"/>
    </c:if>

    <form action="${action}" method="post">
        <input type="hidden" name="id" value="${car.id}">

        <div class="form-group">
            <label>Бренд:</label>
            <input type="text" name="brand" value="<c:out value='${car.brand}'/>">
            <span class="error">${errors.getMessage("brand")}</span>
        </div>

        <div class="form-group">
            <label>Модель:</label>
            <input type="text" name="model" value="<c:out value='${car.model}'/>">
            <span class="error">${errors.getMessage("model")}</span>
        </div>

        <div class="form-group">
            <label>Колір:</label>
            <input type="text" name="color" value="<c:out value='${car.color}'/>">
            <span class="error">${errors.getMessage("color")}</span>
        </div>

        <div class="form-group">
            <label>Номерний знак:</label>
            <input type="text" name="licensePlate" value="<c:out value='${car.licensePlate}'/>">
            <span class="error">${errors.getMessage("licensePlate")}</span>
        </div>

        <div class="form-group">
            <label>Рік випуску:</label>
            <input type="number" name="year" value="<c:out value='${car.year}'/>">
            <span class="error">${errors.getMessage("year")}</span>
        </div>

        <div class="form-group">
            <label>Автопарк (ID Депо):</label>
            <select name="carParkId">
                <option value="">-- Оберіть парк --</option>
                <c:forEach var="park" items="${carParks}">
                    <option value="${park.id}" ${car.carPark.id == park.id ? 'selected' : ''}>
                            ${park.name} (ID: ${park.id})
                    </option>
                </c:forEach>
            </select>
            <span class="error">${errors.getMessage("carParkId")}</span>
        </div>

        <button type="submit" class="btn-submit">Зберегти дані</button>
        <a href="${pageContext.request.contextPath}/cars" class="back-link">← Повернутися до списку</a>
    </form>
</div>

</body>
</html>