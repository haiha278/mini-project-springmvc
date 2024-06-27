package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.EmployeeListDTO;
import lg.cns.miniproject.dto.employee.FilterEmployee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListDTO> getEmployeeList();

    List<EmployeeListDTO> filterEmployeeList(FilterEmployee filterEmployee);
}
