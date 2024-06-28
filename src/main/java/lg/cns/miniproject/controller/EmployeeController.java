package lg.cns.miniproject.controller;

import lg.cns.miniproject.dto.employee.AddEmployeeDTO;
import lg.cns.miniproject.dto.employee.EmployeeListDTO;
import lg.cns.miniproject.dto.employee.FilterEmployee;
import lg.cns.miniproject.dto.project.GetAllProjectDTO;
import lg.cns.miniproject.dto.team.GetAllTeamDTO;
import lg.cns.miniproject.exception.employee.*;
import lg.cns.miniproject.service.employee.EmployeeService;
import lg.cns.miniproject.service.project.ProjectService;
import lg.cns.miniproject.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    private TeamService teamService;
    private ProjectService projectService;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(TeamService teamService, ProjectService projectService, EmployeeService employeeService) {
        this.teamService = teamService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/employee-list")
    public String showEmployeeManagementPage(Model model) {
        List<GetAllTeamDTO> teamList = teamService.getAllTeam();
        model.addAttribute("teamList", teamList);

        List<GetAllProjectDTO> projectList = projectService.getAllProject();
        model.addAttribute("projectList", projectList);

        List<EmployeeListDTO> employeeList = employeeService.getEmployeeList();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("totalEmployee", employeeList.size());
        return "employee-list";
    }

    @PostMapping("/employee-list")
    public String filterEmployeeManagementPage(Model model, @ModelAttribute("filterEmployee") FilterEmployee filterEmployee) {
        List<GetAllTeamDTO> teamList = teamService.getAllTeam();
        model.addAttribute("teamList", teamList);

        List<GetAllProjectDTO> projectList = projectService.getAllProject();
        model.addAttribute("projectList", projectList);

        List<EmployeeListDTO> employeeList = employeeService.filterEmployeeList(filterEmployee);
        model.addAttribute("employeeList", employeeList);
        return "employee-list";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute("addEmployeeDTO") AddEmployeeDTO addEmployeeDTO, Model model) {
        try {
            int row_effected = employeeService.addEmployee(addEmployeeDTO);
            if (row_effected > 0) {
                return "redirect:/employee-list?create=true";
            }
        } catch (EmailExistException | InvalidDateException | InvalidEmailFormatException | NameFormatInvalidException |
                 PhoneExistException | PhoneFormatInvalidException e) {
        }
        return "redirect:/employee-list?create=false";
    }
}
