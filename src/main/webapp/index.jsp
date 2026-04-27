<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Автопарк - Головна</title>
    <style>
        * {
            box-sizing: border-box;
            font-weight: normal !important;
            font-family: 'Segoe UI';
        }

        body {
            background-color: #f4f7f9;
            margin: 0;
            padding-top: 100px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            color: #333;
        }

        .container {
            text-align: center;
            background-color: white;
            width: 500px;
            padding: 50px;
            border-radius: 12px;
            box-shadow: 0 4px 12px #e0e0e0;
        }

        h1 {
            color: #2c3e50;
            margin-top: 0;
            margin-bottom: 20px;
            font-size: 28px;
        }

        p {
            color: #666666;
            margin-bottom: 30px;
            line-height: 1.6;
            font-size: 16px;
        }

        .btn {
            display: inline-block;
            padding: 15px 30px;
            font-size: 18px;
            color: white;
            background-color: #28a745;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s, transform 0.2s;
        }

        .btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Система Автопарку</h1>
    <p>Вітаємо! Тут ви можете керувати списком автомобілів та закріплювати їх за депо.</p>
    <a href="${pageContext.request.contextPath}/cars" class="btn">Переглянути автомобілі</a>
</div>

</body>
</html>