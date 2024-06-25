package lg.cns.miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    @GetMapping("/employee-list")
    public String showEmployeeManagementPage() {
        return "employee-list";
    }
}
