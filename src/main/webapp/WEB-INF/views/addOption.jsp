<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Add Option </title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
                <style>
                    body {
                        background-image: url('https://images.thequint.com/thequint/2023-05/73bf4c27-3ae1-45af-91ce-8c14e164a07f/parliamant_01__1_.jpg');
                        background-size: cover;
                        background-repeat: no-repeat;
                        background-position: center;
                        height: 100vh;
                        margin: 0;
                        padding-top: 50px;
                        display: flex;
                        justify-content: flex-end;
                        align-items: flex-start;
                    }

                    .container {
                        max-width: 800px;
                        margin: 0 auto;
                        margin-top: 20px;
                        background-color: rgba(255, 255, 255, 0.8);
                        padding: 20px;
                        border-radius: 10px;
                    }

                    h2 {
                        text-align: center;
                        margin-bottom: 30px;
                    }

                    .btn-primary {
                        background-color: #8C9177;
                        border-color: #8C9177;
                        margin-bottom: 10px;
                        display: block;
                        width: 100%;
                    }

                    .btn-primary:hover {
                        background-color: #6C7060;
                        border-color: #6C7060;
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
                    <h2 class="text-center mt-4">Add Option for ${poll.pollName}</h2>
                    <table class="table table-striped option-table">
                        <thead>
                            <tr>
                                <th>candidateName Name</th>
                                <th>Party Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${options}" var="option">
                                <tr>
                                    <td>${option.candidateName}</td>
                                    <td>${option.party}</td>
                                    <td>
                                        <a href="/Online-Voting-System/pollOption/delete/${poll.pollId}/${option.optionId}"
                                            class="btn btn-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br>
                    <form action="/Online-Voting-System/pollOption/handleForm" method="post">
                        <div class="form-group">
                            <label for="candidateName">Select Candidate:</label>
                            <select id="candidateName" name="candidateId" class="form-control" required>
                                <option value="">Select Candidate</option>
                                <c:forEach items="${candidates}" var="candidate">
                                    <option value="${candidate.candidateId}">${candidate.candidateName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="partyName">Select Party:</label>
                            <select id="partyName" name="partyId" class="form-control" required>
                                <option value="">Select Party</option>
                                <c:forEach items="${parties}" var="party">
                                    <option value="${party.partyId}">${party.partyName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="hidden" name="pollId" value="${poll.pollId}">
                        <button type="submit" class="btn btn-primary">Add</button>
                        <a href="/Online-Voting-System/admin/home" class="btn btn-outline-primary">Back</a>
                    </form>
                </div>

                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
            </body>

            </html>