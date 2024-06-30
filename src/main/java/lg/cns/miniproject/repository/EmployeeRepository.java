package lg.cns.miniproject.repository;

import lg.cns.miniproject.dto.employee.EmployeeInfomationDTO;
import lg.cns.miniproject.dto.employee.FilterEmployee;
import lg.cns.miniproject.entity.Employee;
import lg.cns.miniproject.sqlBuilder.EmployeeSqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeRepository {

    @Select("SELECT e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address, t.team_name, p.project_name, e2.name as nameLead, e.start_date, e.status \n" +
            "FROM employee e \n" +
            "LEFT JOIN project p ON e.project_id = p.project_id \n" +
            "LEFT JOIN employee e2 ON p.project_leader_id = e2.employee_id \n" +
            "LEFT JOIN team t ON e.team_id = t.team_id\n" +
            "ORDER BY e.employee_id \n" +
            "LIMIT #{limit} OFFSET #{offset};")
    @ResultMap("EmployeeResultMap")
    List<Employee> getEmployeeListByPaging(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address, t.team_name, p.project_name, e2.name as nameLead, e.start_date, e.status \n" +
            "FROM employee e \n" +
            "LEFT JOIN project p ON e.project_id = p.project_id \n" +
            "LEFT JOIN employee e2 ON p.project_leader_id = e2.employee_id \n" +
            "LEFT JOIN team t ON e.team_id = t.team_id;")
    @ResultMap("EmployeeResultMap")
    List<Employee> findAll();

    @SelectProvider(type = EmployeeSqlBuilder.class, method = "builderFilterEmployeeQuery")
    @ResultMap("EmployeeResultMap")
    List<Employee> filterEmployeeList(@Param("filterEmployee") FilterEmployee filterEmployee, @Param("offset") int offset, @Param("limit") int limit);

    @SelectProvider(type = EmployeeSqlBuilder.class, method = "countFilteredEmployees")
    int countFilteredEmployees(FilterEmployee filterEmployee);

    @Insert("INSERT INTO employee (name, gender, birthday, phone, email, address, start_date, status, team_id, project_id) " +
            "VALUES (#{employeeName}, #{gender}, #{dob}, #{phoneNumber}, #{email}, #{address}, #{startDate}, #{status}, #{team.teamId}, #{project.projectId})")
    @ResultMap("EmployeeResultMap")
    int insertEmployee(Employee employee);

    @Select("select e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address,t.team_id, t.team_name,p.project_id, p.project_name, e.start_date, e.status from employee e left join project p on e.project_id = p.project_id join team t on e.team_id = t.team_id where e.employee_id = #{employeeId}")
    @ResultMap("EmployeeInformationDTOResultMap")
    EmployeeInfomationDTO getEmployeeById(long employeeId);


    @Update("UPDATE employee SET " +
            "name = #{employeeName}, " +
            "gender = #{gender}, " +
            "birthday = #{dob}, " +
            "phone = #{phoneNumber}, " +
            "email = #{email}, " +
            "address = #{address}, " +
            "start_date = #{startDate}, " +
            "status = #{status}, " +
            "team_id = #{teamId}, " +
            "project_id = #{projectId} " +
            "WHERE employee_id = #{employeeId}")
    int updateEmployee(EmployeeInfomationDTO employeeInfomationDTO);

    @Select("select * from employee where employee_id != #{employeeId}")
    @ResultMap("EmployeeInformationDTOResultMap")
    List<EmployeeInfomationDTO> getAllEmployeeExceptById(long employeeId);

    int deleteEmployees(@Param("employeeIds") List<Long> employeeIds);

    @Select("SELECT COUNT(*) FROM employee")
    int countAll();
}
