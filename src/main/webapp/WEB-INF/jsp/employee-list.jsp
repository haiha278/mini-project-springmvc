<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="${pageContext.request.contextPath}/css/employee-list.css" rel="stylesheet" />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <title>Employee Management</title>
</head>
<body>
<form action="/employee-list" method="post">
    <div class="filter-box">
        <h1>Employee Management</h1>

        <div class="filter-input">
            <div class="select-container">
                <label for="team-select">Team:</label>
                <div class="custom-select">
                    <select id="team-select" name="teamSelect">
                        <option>--All--</option>
                        <c:forEach items="${teamList}" var="team">
                            <option value="${team.teamId}">${team.teamName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="select-container">
                <label for="project-select">Project:</label>
                <div class="custom-select">
                    <select id="project-select" name="projectSelect">
                        <option>--All--</option>
                        <c:forEach items="${projectList}" var="project">
                            <option value="${project.projectId}">
                                    ${project.projectName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="select-container">
                <label for="mySelect3">Status:</label>
                <div class="custom-select">
                    <select id="mySelect3" name="mySelect">
                        <option>--All--</option>
                        <option value="option1">Option 1</option>
                        <option value="option2">Option 2</option>
                        <option value="option3">Option 3</option>
                        <option value="option4">Option 4</option>
                    </select>
                </div>
            </div>

            <div class="select-container">
                <label class="label" for="start-date-input">Start Date:</label>
                <input type="date" id="start-date-input" />
            </div>

            <div class="select-container">
                <label class="label" for="end-date-input">End Date:</label>
                <input type="date" id="end-date-input" />
            </div>
        </div>

        <div class="search-container">
            <div class="input-for-search">
                <input type="text" class="input-with-icon" placeholder="Search" />
            </div>

            <div>
                <button type="submit" class="search-button">Search</button>
            </div>
        </div>
    </div>

    <div class="employee-list">
        <div class="total-and-button-to-add-or-delete">
            <div>
                <p>*Total:</p>
            </div>
            <div>
                <button>New</button>
                <button>Delete</button>
            </div>
        </div>

        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>STT</th>
                    <th>EmpID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Birthday</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Team</th>
                    <th>Project</th>
                    <th>Project Leader</th>
                    <th>Start Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach
                        items="${employeeList}"
                        var="employee"
                        varStatus="status"
                >
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${employee.employeeId}</td>
                        <td>${employee.employeeName}</td>
                        <td>${employee.gender}</td>
                        <td>${employee.dob}</td>
                        <td>${employee.phoneNumber}</td>
                        <td>${employee.email}</td>
                        <td>${employee.address}</td>
                        <td>${employee.teamName}</td>
                        <td>${employee.projectName}</td>
                        <td>${employee.projectLeaderName}</td>
                        <td>${employee.startDate}</td>
                        <td>${employee.status}</td>
                        <td><!-- Action buttons --></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <p id="teamList" style="display: none">${teamList}</p>
    <p id="projectList" style="display: none">${projectList}</p>
</form>
</body>
</html>

