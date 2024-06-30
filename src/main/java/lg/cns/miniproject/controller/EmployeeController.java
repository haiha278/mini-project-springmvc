package lg.cns.miniproject.controller;

import jakarta.servlet.http.HttpSession;
import lg.cns.miniproject.dto.employee.AddEmployeeDTO;
import lg.cns.miniproject.dto.employee.EmployeeListDTO;
import lg.cns.miniproject.dto.employee.FilterEmployee;
import lg.cns.miniproject.dto.employee.UpdateEmployeeDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showEmployeeManagementPage(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        List<GetAllTeamDTO> teamList = teamService.getAllTeam();
        model.addAttribute("teamList", teamList);

        List<GetAllProjectDTO> projectList = projectService.getAllProject();
        model.addAttribute("projectList", projectList);

        FilterEmployee filterEmployee = (FilterEmployee) session.getAttribute("filterEmployee");

        if (filterEmployee != null) {
            List<EmployeeListDTO> employeeList = employeeService.filterEmployeeList(filterEmployee, page, size);
            int totalRecords = employeeService.countFilteredEmployees(filterEmployee);
            int totalPages = (int) Math.ceil((double) totalRecords / size);
            model.addAttribute("totalEmployee", totalRecords);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("employeeList", employeeList);
            model.addAttribute("totalEmployee", totalRecords);
        } else {
            List<EmployeeListDTO> employeeList = employeeService.getEmployeeListByPaging(page, size);
            int totalRecords = employeeService.countAllEmployees();
            int totalPages = (int) Math.ceil((double) totalRecords / size);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("employeeList", employeeList);
            model.addAttribute("totalEmployee", totalRecords);
            model.addAttribute("totalEmployee", totalRecords);
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "employee-list";
    }

    @PostMapping("/employee-list")
    public String filterEmployeeManagementPage(HttpSession session, Model model, @ModelAttribute("filterEmployee") FilterEmployee filterEmployee,
                                               @RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "5") int size) {
        List<GetAllTeamDTO> teamList = teamService.getAllTeam();
        model.addAttribute("teamList", teamList);

        List<GetAllProjectDTO> projectList = projectService.getAllProject();
        model.addAttribute("projectList", projectList);

        List<EmployeeListDTO> employeeList = employeeService.filterEmployeeList(filterEmployee, page, size);
        model.addAttribute("employeeList", employeeList);

        session.setAttribute("filterEmployee", filterEmployee);

        int totalRecords = employeeService.countFilteredEmployees(filterEmployee);
        int totalPages = (int) Math.ceil((double) totalRecords / size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalEmployee", totalRecords);
        model.addAttribute("size", size);
        return "employee-list";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute("addEmployeeDTO") AddEmployeeDTO addEmployeeDTO) {
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

    @PostMapping("/update-employee")
    public String updateEmployee(@ModelAttribute("updateEmployeeDTO") UpdateEmployeeDTO updateEmployeeDTO) {
        try {
            int row_effected = employeeService.updateEmployee(updateEmployeeDTO);
            if (row_effected > 0) {
                return "redirect:/employee-list?update=true";
            }
        } catch (EmailExistException | InvalidDateException | InvalidEmailFormatException | NameFormatInvalidException |
                 PhoneExistException | PhoneFormatInvalidException e) {

        }
        return "redirect:/employee-list?update=false";
    }

    @PostMapping("/delete-employee")
    public String deleteEmployee(@RequestParam List<Long> employeeIds) {
        System.out.println("Employee IDs to delete: " + employeeIds);
        int row_effected = employeeService.deleteEmployee(employeeIds);
        if (row_effected > 0) {
            return "redirect:/employee-list?delete=true";
        }
        return "redirect:/employee-list?delete=false";
    }
}
