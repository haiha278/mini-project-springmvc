<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link href="${pageContext.request.contextPath}/css/employee-list.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/add-employee.css" rel="stylesheet"/>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
    />
    <title>Employee Management</title>
</head>
<body>
<form action="/employee-list" method="post" modelAttribute="filterEmployee">
    <div class="filter-box">
        <h1>Employee Management</h1>

        <div class="filter-input">
            <div class="select-container">
                <label for="team-select">Team:</label>
                <div class="custom-select">
                    <select id="team-select" name="teamSelect">
                        <option value=" ">--All--</option>
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
                        <option value=" ">--All--</option>
                        <c:forEach items="${projectList}" var="project">
                            <option value="${project.projectId}">
                                    ${project.projectName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="select-container">
                <label for="status">Status:</label>
                <div class="custom-select">
                    <select id="status" name="status">
                        <option value="">--All--</option>
                        <option value="Active">Active</option>
                        <option value="Inactive">Inactive</option>
                    </select>
                </div>
            </div>

            <div class="select-container">
                <label class="label" for="start-date-input">Start Date:</label>
                <input type="date" id="start-date-input" name="fromDate"/>
            </div>

            <div class="select-container">
                <label class="label" for="end-date-input">End Date:</label>
                <input type="date" id="end-date-input" name="endDate"/>
            </div>
        </div>

        <div class="search-container">
            <div class="input-for-search">
                <input type="text" class="input-with-icon" name="searchInput" placeholder="Search"/>
            </div>

            <div>
                <button type="submit" class="search-button">Search</button>
            </div>
        </div>
    </div>
</form>

<div class="employee-list">
    <div class="total-and-button-to-add-or-delete">
        <div>
            <p style="color: red">*Total: ${totalEmployee}</p>
        </div>
        <div>
            <button onclick="addNewEmployee()">New</button>
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
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div id="popup" class="popup">
    <div class="popup-content" id="popup-content">
    </div>
</div>
<p id="teamList" style="display: none">${teamList}</p>
<p id="projectList" style="display: none">${projectList}</p>

<script src="${pageContext.request.contextPath}/js/employee-list.js"></script>
</body>
</html>

