<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Login</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <style>
    body {
      background-image: url('https://wallpaperaccess.com/full/4475187.jpg');
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
  <h2>Admin Login</h2>
  <form action="/Online-Voting-System/admin/handleForm" method="post">
    <div class="form-group">
      <label for="adminUsername">Username:</label>
      <input type="text" class="form-control" id="adminUsername" placeholder="Enter admin username" required name="username">
    </div>
    <div class="form-group">
      <label for="adminPassword">Admin Password:</label>
      <input type="password" class="form-control" id="adminPassword" placeholder="Enter admin password" required name="password">
    </div>
    <br>
    <button type="submit" class="btn btn-primary btn-block">Login</button>
    <a href="/Online-Voting-System/voter/login" class="btn btn-primary btn-block">Login as Voter</a>
  </form>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
