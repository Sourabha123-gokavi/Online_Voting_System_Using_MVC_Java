<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Voter</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://th.bing.com/th/id/OIP.NGwcZSACvgig8VV6202mogHaD4?rs=1&pid=ImgDetMain');
            background-size: cover; /* Cover the entire body with the background image */
            background-repeat: no-repeat;
            background-position: center;
            background-attachment: fixed; /* Keep the background fixed while scrolling */
            color: #fff; /* Set text color to white for better visibility */
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            margin-top: 100px;
            background-color: rgba(0, 0, 0, 0.5); /* Add semi-transparent black background to container */
            padding: 20px;
            border-radius: 10px;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
        }

        .form-group label {
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Add Voter</h2>
    <form action="/Online-Voting-System/voter/handleForm" method="post">
    <div class="form-group">
        <label for="voterName">Voter Name:</label>
        <input type="text" class="form-control" id="voterName" placeholder="Enter voter name" required name="voterName">
    </div>
    <div class="form-group">
        <label for="email">Email Address:</label>
        <input type="email" class="form-control" id="email" placeholder="Enter email" required name="email">
    </div>
    <div class="form-group">
        <label for="phone">Phone Number:</label>
        <input type="tel" class="form-control" id="phone" placeholder="Enter phone number" required name="phone">
    </div>
    <div class="form-group">
        <label for="region">Region:</label>
        <select class="form-control" id="region" required name="region">
            <option value="">Select Region</option>
            <option value="North">North</option>
            <option value="South">South</option>
            <option value="East">East</option>
            <option value="West">West</option>
        </select>
    </div>
        <button type="submit" class="btn btn-primary btn-block mt-3">Add Voter</button>
    </form>
    <a href="/Online-Voting-System/voter/displayAll" class="btn btn-outline-primary btn-block mt-3">Back</a>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
