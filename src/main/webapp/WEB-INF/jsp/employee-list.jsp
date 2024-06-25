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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <title>Employee Management</title>
</head>
<body>
<form>
    <div class="filter-box">
        <h1>Employee Management</h1>

        <div class="filter-input">
            <div class="select-container">
                <label for="mySelect1" class="select-label">Team:</label>
                <div class="custom-select">
                    <select id="mySelect1" name="mySelect">
                        <option>--All--</option>
                        <option value="option1">Option 1</option>
                        <option value="option2">Option 2</option>
                        <option value="option3">Option 3</option>
                        <option value="option4">Option 4</option>
                    </select>
                </div>
            </div>

            <div class="select-container">
                <label for="mySelect2" class="select-label">Project:</label>
                <div class="custom-select">
                    <select id="mySelect2" name="mySelect">
                        <option>--All--</option>
                        <option value="option1">Option 1</option>
                        <option value="option2">Option 2</option>
                        <option value="option3">Option 3</option>
                        <option value="option4">Option 4</option>
                    </select>
                </div>
            </div>

            <div class="select-container">
                <label for="mySelect3" class="select-label">Status:</label>
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
                <input type="date" id="start-date-input"/>
            </div>

            <div class="select-container">
                <label class="label" for="end-date-input">End Date:</label>
                <input type="date" id="end-date-input"/>
            </div>
        </div>

        <div class="search-container">
            <div class="input-for-search">
                <input type="text" class="input-with-icon" placeholder="Search"/>
            </div>

            <div>
                <button type="submit" class="search-button">Search</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
