package lg.cns.miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @GetMapping("/employee-list")
    public String showEmployeeManagementPage() {
        return "employee-list";
    }

    @PostMapping("/employee-list")
    public String filterEmployeeManagementPage() {
        return "employee-list";
    }
}
