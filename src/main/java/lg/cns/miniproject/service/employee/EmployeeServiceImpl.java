package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.EmployeeListDTO;
import lg.cns.miniproject.entity.Employee;
import lg.cns.miniproject.entity.Project;
import lg.cns.miniproject.entity.Team;
import lg.cns.miniproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeListDTO> getEmployeeList() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeListDTO> employeeListDTOList = new ArrayList<>();
        if (employeeList != null && employeeList.size() > 0) {
            for (Employee employee : employeeList) {
                EmployeeListDTO employeeListDTO = new EmployeeListDTO();
                employeeListDTO.setEmployeeId(employee.getEmployeeId());
                employeeListDTO.setEmployeeName(employee.getEmployeeName());
                employeeListDTO.setGender(employee.getGender());
                employeeListDTO.setDob(employee.getDob());
                employeeListDTO.setPhoneNumber(employee.getPhoneNumber());
                employeeListDTO.setEmail(employee.getEmail());
                employeeListDTO.setAddress(employee.getAddress());

                Team team = employee.getTeam();
                Project project = employee.getProject();

                employeeListDTO.setTeamName(team.getTeamName());
                employeeListDTO.setProjectName(project.getProjectName());
                employeeListDTO.setProjectLeaderName(employee.getNameLead());

                employeeListDTO.setStartDate(employee.getStartDate());
                employeeListDTO.setStatus(employee.getStatus());
                employeeListDTOList.add(employeeListDTO);
            }
        }
        return employeeListDTOList;
    }
}
