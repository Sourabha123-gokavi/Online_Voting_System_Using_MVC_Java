<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Admin Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://akm-img-a-in.tosshub.com/indiatoday/images/story/202305/bypoll_1-sixteen_nine.jpg?VersionId=qK8EAmc1OuPFReZ0DeXC0tTobtdHQFX.&size=690:388');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            height: 100vh;
            margin: 0;
            padding-top: 50px; /* Adjust padding-top to keep content below the navbar */
            display: flex;
            justify-content: flex-end; /* Align content to the right */
            align-items: flex-start; /* Align content to the top */
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            margin-top: 20px; /* Adjust margin to create space between content and right edge */
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
        }

        .btn-primary {
            background-color: #8C9177; /* Sampled color from the background image */
            border-color: #8C9177; /* Sampled color from the background image */
            margin-bottom: 10px; /* Add margin between buttons */
            display: block; /* Make buttons appear one below the other */
            width: 100%; /* Make buttons fill the container width */
        }

        .btn-primary:hover {
            background-color: #6C7060; /* Darker shade of the sampled color */
            border-color: #6C7060; /* Darker shade of the sampled color */
        }

        .table th,
        .table td {
            vertical-align: middle;
        }

        .table th:last-child,
        .table td:last-child {
            text-align: center;
        }

        .table .btn {
            margin-right: 5px;
            padding: 0.375rem 0.75rem;
            font-size: 14px;
        }

        .add-polls-button {
            margin-top: 20px;
            float: right;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Set Your Password</h2>
        <form action="/Online-Voting-System/voter/handleSetPassword" method="post">
            <div class="form-group">
                <label for="voterName">Voter Name:</label>
                <input type="text" class="form-control" id="voterName" placeholder="Enter voter name" required
                    name="username">
            </div>
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" class="form-control" id="newPassword" placeholder="Enter new password"
                    required name="newPassword">
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm new password"
                    required name="confirmPassword">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Set Password</button>
        </form>
    </div>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
