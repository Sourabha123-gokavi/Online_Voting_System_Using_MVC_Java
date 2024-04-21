<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://images.thequint.com/thequint/2023-05/73bf4c27-3ae1-45af-91ce-8c14e164a07f/parliamant_01__1_.jpg');
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
            margin-top:2px;
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
    <h2>Polls</h2>
    <table class="table table-striped" style="margin-bottom: 30px;">
        <thead>
        <tr>
            <th>Poll Name</th>
            <th>Status</th>
            <th>Winner</th>
            <th>Region</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${polls}" var="poll">
            <tr>
                <td>${poll.pollName}</td>
                <td>
                    <c:choose>
                        <c:when test="${poll.status}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Inactive
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${poll.winner}</td>
                <td>${poll.region}</td> <!-- Display the region -->
                <td>
                    <c:choose>
    <c:when test="${poll.status}">
        <div class="d-inline-block">
            <a class="btn btn-primary btn-sm add-pollOption-btn" href="/Online-Voting-System/pollOption/add/${poll.pollId}">Add Options</a>
        </div>
        <div class="d-inline-block">
            <a class="btn btn-primary btn-sm" href="/Online-Voting-System/voteCount/result/${poll.pollId}">Calculate Result and Inactivate</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="d-inline-block">
            <a class="btn btn-primary btn-sm disabled" href="#" disabled="disabled">Add Options</a>
        </div>
        <div class="d-inline-block">
            <a class="btn btn-primary btn-sm disabled" href="#" disabled="disabled">Calculate Result and Inactivate</a>
        </div>
    </c:otherwise>
</c:choose>
<a class="btn btn-danger btn-sm" href="/Online-Voting-System/poll/deletePoll/${poll.pollId}">Delete</a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/Online-Voting-System/poll/add" class="btn btn-primary add-polls-button mb-3">Add More Polls</a><br>
    <a href="/Online-Voting-System/admin/login" class="btn btn-primary backbutton">Back</a>


</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
