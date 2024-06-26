package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.EmployeeListDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListDTO> getEmployeeList();
}
