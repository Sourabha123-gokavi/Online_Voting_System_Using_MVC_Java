<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>
                <head>
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
                    <h2>Candidate List</h2>
                    <form action="/Online-Voting-System/candidate/handleForm" method="post">
                        <div class="form-group">
                            <label for="voterId">Select Candidate:</label>
                            <select class="form-control" id="voterId" required name="voterId">
                                <option value="">Select Candidate</option>
                                <c:forEach items="${voters}" var="voter">
                                    <option value="${voter.voterId}">${voter.voterName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block mt-3">Add Candidate</button>
                    </form>
                    <a class="btn btn-outline-primary" href="/Online-Voting-System/admin/home">Back</a>
                </div>

                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
            </body>
</html>