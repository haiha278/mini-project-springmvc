package lg.cns.miniproject.controller;

import lg.cns.miniproject.dto.project.GetAllProjectDTO;
import lg.cns.miniproject.dto.response.BaseResponse;
import lg.cns.miniproject.dto.team.GetAllTeamDTO;
import lg.cns.miniproject.service.employee.EmployeeService;
import lg.cns.miniproject.service.project.ProjectService;
import lg.cns.miniproject.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private TeamService teamService;
    private ProjectService projectService;

    @Autowired
    public RestController(TeamService teamService, ProjectService projectService) {
        this.teamService = teamService;
        this.projectService = projectService;
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
}
