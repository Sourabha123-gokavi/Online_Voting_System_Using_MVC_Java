<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://static.wixstatic.com/media/f64377_97a05f5aed264b86a7010dc169ca5bef~mv2_d_4640_3018_s_4_2.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: flex-end; /* Shift content to the right */
            align-items: center;
        }

        .container {
            width: 400px; /* Set container width */
            margin-right: 50px; /* Adjust margin to shift further right */
            background-color: rgba(255, 255, 255, 0.8);
            padding: 30px; /* Increase padding for larger container */
            border-radius: 10px;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 24px; /* Increase font size for header */
        }

        .form-group label {
            font-weight: bold;
        }

        .form-control {
            height: 50px; /* Increase input height */
            font-size: 16px; /* Increase font size for input */
        }

        .btn-primary {
            height: 50px; /* Increase button height */
            font-size: 18px; /* Increase font size for button */
        }

        .btn-block {
            margin-top: 20px; /* Add margin to button */
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Voter Login</h2>
        <form action="/Online-Voting-System/voter/handleLogin" method="post">
            <div class="form-group">
                <label for="voterName">Voter Name:</label>
                <input type="text" class="form-control" id="voterName" placeholder="Enter voter name" required
                    name="username">
            </div>
            <div class="form-group">
                <label for="voterPassword">Voter Password:</label>
                <input type="password" class="form-control" id="voterPassword"
                    placeholder="Enter voter password" required name="password">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
            <a href="/Online-Voting-System/admin/login" class="btn btn-primary btn-block">Login as Admin</a>
            <a href="/Online-Voting-System/voter/setPassword" class="btn btn-secondary btn-block">Set/Reset
                Password</a>
        </form>
    </div>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
