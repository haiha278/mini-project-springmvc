package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.AddEmployeeDTO;
import lg.cns.miniproject.dto.employee.EmployeeListDTO;
import lg.cns.miniproject.dto.employee.FilterEmployee;
import lg.cns.miniproject.entity.Employee;
import lg.cns.miniproject.entity.Project;
import lg.cns.miniproject.entity.Team;
import lg.cns.miniproject.modelMapper.Mapper;
import lg.cns.miniproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private Mapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Mapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EmployeeListDTO> getEmployeeList() {
        List<Employee> employeeList = employeeRepository.findAll();
        int totalEmployee = employeeList.size();
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

    @Override
    public List<EmployeeListDTO> filterEmployeeList(FilterEmployee filterEmployee) {
        List<Employee> employeeList = employeeRepository.filterEmployeeList(filterEmployee);
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

    @Override
    public int addEmployee(AddEmployeeDTO addEmployeeDTO) {
        Employee employee = (Employee) mapper.mapToEntity(addEmployeeDTO, Employee.class);
        employee.setStatus(addEmployeeDTO.getStatus() ? "Active" : "Inactive");
        employee.setPhoneNumber(addEmployeeDTO.getPhoneNumber());

        Team team = new Team();
        team.setTeamId(addEmployeeDTO.getTeamSelect());

        employee.setTeam(team);

        Project project = new Project();
        project.setProjectId(addEmployeeDTO.getProjectSelect());

        employee.setProject(project);
        int row_effect = employeeRepository.insertEmployee(employee);
        return row_effect;
    }
}
