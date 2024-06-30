package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.*;
import lg.cns.miniproject.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeService {
    List<EmployeeListDTO> getEmployeeListByPaging(int page, int size);

    List<EmployeeListDTO> getEmployeeList();

    List<EmployeeListDTO> filterEmployeeList(FilterEmployee filterEmployee, int page, int size);

    int addEmployee(AddEmployeeDTO addEmployeeDTO);

    EmployeeInfomationDTO getEmployeeById(long employeeId);

    int updateEmployee(UpdateEmployeeDTO updateEmployeeDTO);

    int deleteEmployee(List<Long> employeeIds);

    int countAllEmployees();

    int countFilteredEmployees(FilterEmployee filterEmployee);
}
