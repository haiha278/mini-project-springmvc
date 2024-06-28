package lg.cns.miniproject.controller;

import lg.cns.miniproject.dto.employee.AddEmployeeDTO;
import lg.cns.miniproject.dto.employee.EmployeeInfomationDTO;
import lg.cns.miniproject.dto.project.GetAllProjectDTO;
import lg.cns.miniproject.dto.response.BaseResponse;
import lg.cns.miniproject.dto.team.GetAllTeamDTO;
import lg.cns.miniproject.entity.Employee;
import lg.cns.miniproject.service.employee.EmployeeService;
import lg.cns.miniproject.service.project.ProjectService;
import lg.cns.miniproject.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private TeamService teamService;
    private ProjectService projectService;
    private EmployeeService employeeService;

    @Autowired
    public RestController(TeamService teamService, ProjectService projectService, EmployeeService employeeService) {
        this.teamService = teamService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/teams")
    public ResponseEntity getAllTeam() {
        List<GetAllTeamDTO> teamList = teamService.getAllTeam();
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), teamList), HttpStatus.OK);
    }

    @GetMapping("/projects")
    public ResponseEntity getAllPrject() {
        List<GetAllProjectDTO> projectList = projectService.getAllProject();
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), projectList), HttpStatus.OK);
    }

    @GetMapping("/employee-info")
    public ResponseEntity getEmployeeById(@RequestParam long id){
        EmployeeInfomationDTO employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), employee), HttpStatus.OK);
    }
}
