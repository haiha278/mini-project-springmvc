package lg.cns.miniproject.service.employee;

import lg.cns.miniproject.dto.employee.*;
import lg.cns.miniproject.entity.Employee;
import lg.cns.miniproject.entity.Project;
import lg.cns.miniproject.entity.Team;
import lg.cns.miniproject.exception.employee.*;
import lg.cns.miniproject.modelMapper.Mapper;
import lg.cns.miniproject.repository.EmployeeRepository;
import lg.cns.miniproject.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Validation validation;
    private EmployeeRepository employeeRepository;
    private Mapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Mapper mapper, Validation validation) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.validation = validation;
    }

    @Override
    public List<EmployeeListDTO> getEmployeeList() {
        List<Employee> employeeList = employeeRepository.findAll();

        if (employeeList != null && employeeList.size() > 0) {

            List<EmployeeListDTO> employeeListDTOList = employeeList.stream().map(employee -> {
                EmployeeListDTO employeeListDTO = (EmployeeListDTO) mapper.mapToDTO(employee, EmployeeListDTO.class);
                Team team = employee.getTeam();
                Project project = employee.getProject();

                employeeListDTO.setTeamName(team != null ? team.getTeamName() : "");

                employeeListDTO.setProjectName(project != null ? project.getProjectName() : "");

                employeeListDTO.setNameLead(employee.getNameLead());
                return employeeListDTO;
            }).toList();

            return employeeListDTOList;
        }
        return null;
    }

    @Override
    public List<EmployeeListDTO> getEmployeeListByPaging(int page, int size) {
        int offset = (page - 1) * size;

        List<Employee> employeeList = employeeRepository.getEmployeeListByPaging(offset, size);

        if (employeeList != null && employeeList.size() > 0) {
            List<EmployeeListDTO> employeeListDTOList = employeeList.stream().map(employee -> {
                EmployeeListDTO employeeListDTO = (EmployeeListDTO) mapper.mapToDTO(employee, EmployeeListDTO.class);
                Team team = employee.getTeam();
                Project project = employee.getProject();

                employeeListDTO.setTeamName(team != null ? team.getTeamName() : "");

                employeeListDTO.setProjectName(project != null ? project.getProjectName() : "");

                employeeListDTO.setNameLead(employee.getNameLead());
                return employeeListDTO;
            }).toList();
            return employeeListDTOList;
        }
        return null;
    }

    @Override
    public List<EmployeeListDTO> filterEmployeeList(FilterEmployee filterEmployee, int page, int size) {
        int offset = (page - 1) * size;

        List<Employee> employeeList = employeeRepository.filterEmployeeList(filterEmployee, offset, size);

        if (employeeList != null && employeeList.size() > 0) {
            List<EmployeeListDTO> employeeListDTOList = employeeList.stream().map(employee -> {
                EmployeeListDTO employeeListDTO = (EmployeeListDTO) mapper.mapToDTO(employee, EmployeeListDTO.class);

                Team team = employee.getTeam();
                Project project = employee.getProject();

                employeeListDTO.setTeamName(team != null ? team.getTeamName() : "");
                employeeListDTO.setProjectName(project != null ? project.getProjectName() : "");
                employeeListDTO.setNameLead(employee.getNameLead());

                return employeeListDTO;
            }).toList();
            return employeeListDTOList;
        }
        return null;
    }

    @Override
    public int addEmployee(AddEmployeeDTO addEmployeeDTO) {
        validation.validateDataForAddEmployee(addEmployeeDTO, getEmployeeList());

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

    @Override
    public EmployeeInfomationDTO getEmployeeById(long employeeId) {
        EmployeeInfomationDTO employee = employeeRepository.getEmployeeById(employeeId);
        return employee;
    }

    @Override
    public int updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        List<EmployeeInfomationDTO> employeeList = employeeRepository.getAllEmployeeExceptById(updateEmployeeDTO.getEmployeeId());

        validation.validateDataForUpdateEmployee(updateEmployeeDTO, employeeList);

        EmployeeInfomationDTO employeeInfomationDTO = (EmployeeInfomationDTO) mapper.mapToEntity(updateEmployeeDTO, EmployeeInfomationDTO.class);
        employeeInfomationDTO.setProjectId(updateEmployeeDTO.getProjectSelect());
        employeeInfomationDTO.setTeamId(updateEmployeeDTO.getTeamSelect());
        if (updateEmployeeDTO.getStatus() != null) {
            employeeInfomationDTO.setStatus(updateEmployeeDTO.getStatus() ? "Active" : "Inactive");
        } else {
            employeeInfomationDTO.setStatus("Inactive");
        }
        int row_effected = employeeRepository.updateEmployee(employeeInfomationDTO);
        return row_effected;
    }

    @Override
    public int deleteEmployee(List<Long> employeeIds) {
        int row_effected = employeeRepository.deleteEmployees(employeeIds);
        return row_effected;
    }

    @Override
    public int countAllEmployees() {
        return employeeRepository.countAll();
    }

    @Override
    public int countFilteredEmployees(FilterEmployee filterEmployee) {
        return employeeRepository.countFilteredEmployees(filterEmployee);
    }
}
