<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="add-employee.css" rel="stylesheet" />
    <title>Document</title>
</head>
<body>
<form>
    <div class="container">
        <h2>Employee Information</h2>

        <div class="input-info">
            <div class="left-input-info">
                <div>
                    <label class="label" for="name">Name:</label>
                    <input type="text" id="name" />
                </div>

                <div>
                    <label class="label" for="phone">Phone:</label>
                    <input type="text" id="phone" />
                </div>

                <div>
                    <label class="label" for="birthday">Birthday:</label>
                    <input type="date" id="birthday" />
                </div>

                <div>
                    <label for="team-select">Team:</label>
                    <div>
                        <select id="team-select" name="teamSelect">
                            <option>--All--</option>
                        </select>
                    </div>
                </div>

                <div>
                    <div>
                        <label for="status">Status:(active/inactive)</label>
                    </div>

                    <div>
                        <label class="switch">
                            <input type="checkbox" id="status" name="status" />
                            <span class="slider round"></span>
                        </label>
                    </div>
                </div>
            </div>

            <div class="right-input-info">
                <div>
                    <label for="gender" class="select-label">Gender:</label>
                    <select id="gender" name="gender">
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </div>

                <div>
                    <label class="label" for="address">Address:</label>
                    <input type="text" id="address" />
                </div>

                <div>
                    <label class="label" for="start-date">Start Date:</label>
                    <input type="date" id="start-date" />
                </div>

                <div>
                    <label for="project-select">Project:</label>
                    <div>
                        <select id="project-select" name="projectSelect">
                            <option>--All--</option>
                        </select>
                    </div>
                </div>

                <div>
                    <label class="label" for="email">Email:</label>
                    <input type="email" id="email" name="email" />
                </div>
            </div>
        </div>
        <div class="button">
            <div class="submit">
                <button>Cancel</button>
            </div>
            <div class="submit">
                <button type="submit">Save</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
