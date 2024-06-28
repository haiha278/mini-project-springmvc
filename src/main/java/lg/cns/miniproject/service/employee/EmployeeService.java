package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.AddEmployeeDTO;
import lg.cns.miniproject.dto.employee.EmployeeInfomationDTO;
import lg.cns.miniproject.dto.employee.EmployeeListDTO;
import lg.cns.miniproject.dto.employee.FilterEmployee;
import lg.cns.miniproject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListDTO> getEmployeeList();

    List<EmployeeListDTO> filterEmployeeList(FilterEmployee filterEmployee);

    int addEmployee(AddEmployeeDTO addEmployeeDTO);

    EmployeeInfomationDTO getEmployeeById(long employeeId);
}
