function addNewEmployee() {
    console.log("called")
    var popupContent = document.getElementById("popup-content")
    var popup = document.getElementById("popup");
    popupContent.innerHTML = `<form action="/add-employee" method="post" modelAttribute="addEmployeeDTO">
    <div class="container">
        <h2>Employee Information</h2>

        <div class="input-info">
            <div class="left-input-info">
                <div>
                    <label class="label" for="name">Name:</label>
                    <input type="text" id="name" name="name"/>
                </div>

                <div>
                    <label class="label" for="phone">Phone:</label>
                    <input type="text" id="phone" name="phoneNumber"/>
                </div>

                <div>
                    <label class="label" for="birthday">Birthday:</label>
                    <input type="date" id="birthday" name="dob"/>
                </div>

                <div>
                    <label for="team-select">Team:</label>
                    <div>
                        <select id="team-select" name="teamSelect">
                            <option value="1">System Operation Team</option>
                        </select>
                    </div>
                </div>

                <div class="status-input">
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
                    <input type="text" id="address" name="address"/>
                </div>

                <div>
                    <label class="label" for="start-date">Start Date:</label>
                    <input type="date" id="start-date" name="startDate"/>
                </div>

                <div>
                    <label for="project-select">Project:</label>
                    <div>
                        <select id="project-select" name="projectSelect">
                            <option value="1">Life Care</option>
                        </c:forEach>
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
</form>`
    popup.style.display = "block";
}

// function addNewEmployee() {
//     console.log("called");
//     var popupContent = document.getElementById("popup-content");
//     var popup = document.getElementById("popup");
//     popupContent.innerHTML = `<form>
//     <div class="container">
//         <h2>Employee Information</h2>
//
//         <div class="input-info">
//             <div class="left-input-info">
//                 <div>
//                     <label class="label" for="name">Name:</label>
//                     <input type="text" id="name" />
//                 </div>
//
//                 <div>
//                     <label class="label" for="phone">Phone:</label>
//                     <input type="text" id="phone" />
//                 </div>
//
//                 <div>
//                     <label class="label" for="birthday">Birthday:</label>
//                     <input type="date" id="birthday" />
//                 </div>
//
//                 <div>
//                     <label for="team-select">Team:</label>
//                     <div>
//                         <select id="team-select" name="teamSelect">
//                             <option>--All--</option>
//                         </select>
//                     </div>
//                 </div>
//
//                 <div class="status-input">
//                     <div>
//                         <label for="status">Status:(active/inactive)</label>
//                     </div>
//
//                     <div>
//                         <label class="switch">
//                             <input type="checkbox" id="status" name="status" />
//                             <span class="slider round"></span>
//                         </label>
//                     </div>
//                 </div>
//             </div>
//
//             <div class="right-input-info">
//                 <div>
//                     <label for="gender" class="select-label">Gender:</label>
//                     <select id="gender" name="gender">
//                         <option value="Male">Male</option>
//                         <option value="Female">Female</option>
//                     </select>
//                 </div>
//
//                 <div>
//                     <label class="label" for="address">Address:</label>
//                     <input type="text" id="address" />
//                 </div>
//
//                 <div>
//                     <label class="label" for="start-date">Start Date:</label>
//                     <input type="date" id="start-date" />
//                 </div>
//
//                 <div>
//                     <label for="project-select">Project:</label>
//                     <div>
//                         <select id="project-select" name="projectSelect">
//                             <option>--All--</option>
//                         </select>
//                     </div>
//                 </div>
//
//                 <div>
//                     <label class="label" for="email">Email:</label>
//                     <input type="email" id="email" name="email" />
//                 </div>
//             </div>
//         </div>
//         <div class="button">
//             <div class="submit">
//                 <button type="button" id="cancelButton">Cancel</button>
//             </div>
//             <div class="submit">
//                 <button type="submit">Save</button>
//             </div>
//         </div>
//     </div>
// </form>`;
//     popup.style.display = "block";
//
//     // Fetch teams and projects data
//     fetchTeams();
//     fetchProjects();
//
//     // Event listener for the Cancel button
//     document.getElementById('cancelButton').addEventListener('click', function () {
//         popup.style.display = "none";
//     });
// }
//
// // Function to fetch teams data and populate team-select using innerHTML
// function fetchTeams() {
//     fetch('http://localhost:8080/teams')  // Replace with your actual API endpoint for teams
//         .then(response => response.json())
//         .then(data => {
//             const teamSelect = document.getElementById('team-select');
//             let optionsHTML = '<option>--All--</option>';  // Start with default option
//             data.data.forEach(team => {
//                 optionsHTML += `<option value="${team.teamId}">${team.teamName}</option>`;
//             });
//             teamSelect.innerHTML = optionsHTML;  // Set innerHTML once all options are prepared
//         })
//         .catch(error => {
//             console.error('Error fetching teams:', error);
//         });
// }
//
// // Function to fetch projects data and populate project-select using innerHTML
// function fetchProjects() {
//     fetch('http://localhost:8080/projects')  // Replace with your actual API endpoint for projects
//         .then(response => response.json())
//         .then(data => {
//             const projectSelect = document.getElementById('project-select');
//             let optionsHTML = '<option>--All--</option>';  // Start with default option
//             data.data.forEach(project => {
//                 optionsHTML += `<option value="${project.projectId}">${project.projectName}</option>`;
//             });
//             projectSelect.innerHTML = optionsHTML;  // Set innerHTML once all options are prepared
//         })
//         .catch(error => {
//             console.error('Error fetching projects:', error);
//         });
// }

