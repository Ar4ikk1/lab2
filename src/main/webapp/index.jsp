<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Автопарк - Головна</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background: white;
            padding: 50px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            max-width: 500px;
        }
        h1 { color: #2c3e50; margin-bottom: 20px; }
        p { color: #666; margin-bottom: 30px; line-height: 1.6; }
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
            transform: translateY(-2px);
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