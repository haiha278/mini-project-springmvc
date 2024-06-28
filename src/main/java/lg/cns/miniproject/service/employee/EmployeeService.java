package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.*;
import lg.cns.miniproject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListDTO> getEmployeeList();

    List<EmployeeListDTO> filterEmployeeList(FilterEmployee filterEmployee);

    int addEmployee(AddEmployeeDTO addEmployeeDTO);

    EmployeeInfomationDTO getEmployeeById(long employeeId);

    int updateEmployee(UpdateEmployeeDTO updateEmployeeDTO);
}
