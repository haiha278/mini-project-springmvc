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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          crossorigin="anonymous">
    <title>Employee Management</title>
</head>
<body>
<div class="container">
    <form action="/employee-list" method="post" modelAttribute="filterEmployee">
        <input type="hidden" name="page" id="page" value="${currentPage}">
        <input type="hidden" name="size" id="size" value="${size}">

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
                <form id="deleteEmployee" action="/delete-employee" method="post">
                    <button type="submit">Delete</button>
                </form>
            </div>
        </div>

        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th></th>
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
                <c:forEach items="${employeeList}" var="employee" varStatus="status">
                    <tr>
                        <td><input class="input-checkbox" type="checkbox" name="employeeIds"
                                   value="${employee.employeeId}" id="" form="deleteEmployee"
                                   style="cursor: pointer"
                                   onchange="toggleRowBackground(this)">
                        </td>
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
                        <td><i onclick="updateEmployee(${employee.employeeId})" class="fa-regular fa-pen-to-square"
                               style="cursor: pointer"></i>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div id="popup" class="popup">
        <div class="popup-content" id="popup-content">
            <div id="popup-header">Message</div>
            <div id="popup-message"></div>
            <div id="close-popup">
                Confirm
            </div>
        </div>
    </div>
    <div class="pagination">
        <select id="page-size" name="size" onchange="updatePageSize()">
            <%--            <option value="5">5</option>--%>
            <%--            <option value="10">10</option>--%>
            <%--            <option value="20">20</option>--%>
            <option value="5" <c:if test="${size == 5}">selected</c:if>>5</option>
            <option value="10" <c:if test="${size == 10}">selected</c:if>>10</option>
            <option value="20" <c:if test="${size == 20}">selected</c:if>>20</option>
        </select>

        <div class="page-list">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <button onclick="goToPage(${i})" <c:if test="${i == currentPage}">class="active"</c:if>>${i}</button>
            </c:forEach>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/pagination.js"></script>
<script src="${pageContext.request.contextPath}/js/employee-list.js"></script>
<script src="${pageContext.request.contextPath}/js/add-employee.js"></script>
<script src="${pageContext.request.contextPath}/js/update-employee.js"></script>
</body>
</html>

