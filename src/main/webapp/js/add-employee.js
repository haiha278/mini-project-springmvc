function addNewEmployee() {
    var popupContent = document.getElementById("popup-content");
    var popup = document.getElementById("popup");
    popupContent.innerHTML = `<form action="/add-employee" method="post" modelAttribute="addEmployeeDTO">
    <div class="container">
        <h2>Employee Information</h2>

        <div class="input-info">
            <div class="left-input-info">
                <div>
                    <label class="label" for="name">Name:</label>
                    <input type="text" id="name" name="name" required/>
                </div>

                <div>
                    <label class="label" for="phone">Phone:</label>
                    <input type="text" id="phone" name="phoneNumber" required/>
                </div>

                <div>
                    <label class="label" for="birthday">Birthday:</label>
                    <input type="date" id="birthday" name="dob" required/>
                </div>

                <div>
                    <label for="team-select2">Team:</label>
                    <div>
                        <select id="team-select-for-popup" name="teamSelect">
                            <option value="">--All--</option>
                        </select>
                    </div>
                </div>

                <div class="status-input">
                    <div>
                        <label for="status">Status:(active/inactive)</label>
                    </div>

                    <div>
                        <label class="switch">
                            <input type="checkbox" id="status" name="status" checked/>
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
                    <input type="text" id="address" name="address" required/>
                </div>

                <div>
                    <label class="label" for="start-date">Start Date:</label>
                    <input type="date" id="start-date" name="startDate" required/>
                </div>

                <div>
                    <label for="project-select-for-popup">Project:</label>
                    <div>
                        <select id="project-select-for-popup" name="projectSelect">
                            <option value="">--All--</option>
                        </select>
                    </div>
                </div>

                <div>
                    <label class="label" for="email">Email:</label>
                    <input type="email" id="email" name="email" required/>
                </div>
            </div>
        </div>
        <div class="button">
            <div>
                <button class="submit" type="button" id="cancelButton">Cancel</button>
            </div>
            <div>
                <button class="submit" type="submit">Save</button>
            </div>
        </div>
    </div>
</form>`;
    popup.style.display = "block";

    fetchTeamsForAddEmployee();
    fetchProjectsAddEmployee();

    document.getElementById('cancelButton').addEventListener('click', function () {
        popup.style.display = "none";
    });
}

function fetchTeamsForAddEmployee() {
    fetch('http://localhost:8080/teams')
        .then(response => response.json())
        .then(data => {
            const teamSelect = document.getElementById('team-select-for-popup');
            let optionsHTML = '<option value="">--All--</option>';
            data.data.forEach(team => {
                optionsHTML += `<option value="${team.teamId}">${team.teamName}</option>`;
            });
            teamSelect.innerHTML = optionsHTML;
        })
        .catch(error => {
            console.error('Error fetching teams:', error);
        });
}

function fetchProjectsAddEmployee() {
    fetch('http://localhost:8080/projects')
        .then(response => response.json())
        .then(data => {
            const projectSelect = document.getElementById('project-select-for-popup');
            let optionsHTML = '<option value="">--All--</option>'
            data.data.forEach(project => {
                optionsHTML += `<option value="${project.projectId}">${project.projectName}</option>`;
            });
            projectSelect.innerHTML = optionsHTML;
        })
        .catch(error => {
            console.error('Error fetching projects:', error);
        });
}
