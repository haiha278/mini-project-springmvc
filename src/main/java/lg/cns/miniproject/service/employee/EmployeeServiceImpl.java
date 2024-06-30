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
//                employeeListDTO = (EmployeeListDTO) mapper.mapToDTO(employee, EmployeeListDTO.class);

                Team team = employee.getTeam();
                Project project = employee.getProject();

                employeeListDTO.setTeamName(team != null ? team.getTeamName() : "");
                employeeListDTO.setProjectName(project != null ? project.getProjectName() : "");
                employeeListDTO.setProjectLeaderName(employee.getNameLead());

                employeeListDTO.setStartDate(employee.getStartDate());
                employeeListDTO.setStatus(employee.getStatus());
                employeeListDTOList.add(employeeListDTO);
            }
        }
        return employeeListDTOList;
    }

    @Override
    public List<EmployeeListDTO> getEmployeeListByPaging(int page, int size) {
        int offset = (page - 1) * size;

        List<Employee> employeeList = employeeRepository.getEmployeeListByPaging(offset, size);

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
//                employeeListDTO = (EmployeeListDTO) mapper.mapToDTO(employee, EmployeeListDTO.class);

                Team team = employee.getTeam();
                Project project = employee.getProject();

                employeeListDTO.setTeamName(team != null ? team.getTeamName() : "");
                employeeListDTO.setProjectName(project != null ? project.getProjectName() : "");
                employeeListDTO.setProjectLeaderName(employee.getNameLead());

                employeeListDTO.setStartDate(employee.getStartDate());
                employeeListDTO.setStatus(employee.getStatus());
                employeeListDTOList.add(employeeListDTO);
            }
        }
        return employeeListDTOList;
    }

    @Override
    public List<EmployeeListDTO> filterEmployeeList(FilterEmployee filterEmployee, int page, int size) {
        int offset = (page - 1) * size;

        List<Employee> employeeList = employeeRepository.filterEmployeeList(filterEmployee, offset, size);
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
        if (!validation.validateNameFormat(addEmployeeDTO.getName())) {
            throw new NameFormatInvalidException("Name is invalid");
        }
        if (!validation.validatePhoneFormat(addEmployeeDTO.getPhoneNumber())) {
            throw new PhoneFormatInvalidException("Phone is invalid");
        }
        if (validation.checkPhoneExistForAddEmployee(addEmployeeDTO.getPhoneNumber(), getEmployeeList())) {
            throw new PhoneExistException("Phone Number is existed");
        }
        if (validation.validateStartDate(addEmployeeDTO.getStartDate())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (validation.validateBirthday(addEmployeeDTO.getDob())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (!validation.validateEmailFormat(addEmployeeDTO.getEmail())) {
            throw new InvalidEmailFormatException("Invalid Email Format");
        }
        if (validation.checkEmailExistForAddEmployee(addEmployeeDTO.getEmail(), getEmployeeList())) {
            throw new EmailExistException("Email is existed");
        }

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

        if (!validation.validateNameFormat(updateEmployeeDTO.getName())) {
            throw new NameFormatInvalidException("Name is invalid");
        }
        if (!validation.validatePhoneFormat(updateEmployeeDTO.getPhoneNumber())) {
            throw new PhoneFormatInvalidException("Phone is invalid");
        }
        if (validation.checkPhoneExistForUpdateEmployee(updateEmployeeDTO.getPhoneNumber(), employeeList)) {
            throw new PhoneExistException("Phone Number is existed");
        }
        if (validation.validateStartDate(updateEmployeeDTO.getStartDate())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (validation.validateBirthday(updateEmployeeDTO.getDob())) {
            throw new InvalidDateException("Invalid Date");
        }
        if (!validation.validateEmailFormat(updateEmployeeDTO.getEmail())) {
            throw new InvalidEmailFormatException("Invalid Email Format");
        }
        if (validation.checkEmailExistForUpdateEmployee(updateEmployeeDTO.getEmail(), employeeList)) {
            throw new EmailExistException("Email is existed");
        }

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
