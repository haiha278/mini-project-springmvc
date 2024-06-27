package lg.cns.miniproject.repository;

import lg.cns.miniproject.dto.employee.FilterEmployee;
import lg.cns.miniproject.entity.Employee;
import lg.cns.miniproject.sqlBuilder.EmployeeSqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeRepository {

    @Select("select e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address, t.team_name, p.project_name, e2.name as nameLead, e.start_date, e.status " +
            "from employee e left join project p on e.project_id = p.project_id " +
            "join employee e2 on p.project_leader_id = e2.employee_id join team t on e.team_id = t.team_id")
    @ResultMap("EmployeeResultMap")
    List<Employee> findAll();

    @SelectProvider(type = EmployeeSqlBuilder.class, method = "builderFilterEmployeeQuery")
    @ResultMap("EmployeeResultMap")
    List<Employee> filterEmployeeList(FilterEmployee filterEmployee);

    @Insert("INSERT INTO employee (name, gender, birthday, phone, email, address, start_date, status, team_id, project_id) " +
            "VALUES (#{employeeName}, #{gender}, #{dob}, #{phoneNumber}, #{email}, #{address}, #{startDate}, #{status}, #{team.teamId}, #{project.projectId})")
    @ResultMap("EmployeeResultMap")
    int insertEmployee(Employee employee);

//    @Select("SELECT * FROM employee e " +
//            "WHERE 1=1 " +
//            "AND (e.name LIKE CONCAT('%', LOWER(#{name}), '%') OR #{name} IS NULL) " +
//            "AND (e.team LIKE CONCAT('%', LOWER(#{team}), '%') OR #{team} IS NULL) " +
//            "AND (e.phone LIKE CONCAT('%', LOWER(#{phone}), '%') OR #{phone} IS NULL) " +
//            "AND (e.gender = LOWER(#{gender}) OR #{gender} IS NULL) " +
//            "AND (e.birth_date >= #{fromDate} OR #{fromDate} IS NULL) " +
//            "AND (e.birth_date <= #{toDate} OR #{toDate} IS NULL)")
//    List<Employee> findEmployeesByFilters(@Param("name") String name, @Param("team") String team,
//                                          @Param("phone") String phone, @Param("gender") String gender,
//                                          @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
