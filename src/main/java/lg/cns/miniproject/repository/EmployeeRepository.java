package lg.cns.miniproject.repository;

import lg.cns.miniproject.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeRepository {
//    @Select("select e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address, t.team_name, p.project_name, e2.name, e.start_date, e.status " +
//            "from employee e left join project p on e.project_id = p.project_id " +
//            "join employee e2 on p.project_leader_id = e2.employee_id join team t on e.team_id = t.team_id")
//    @ResultMap("EmployeeResultMap")
//    List<Employee> findAll();


    @Select("select e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address, t.team_name, p.project_name, e2.name as nameLead, e.start_date, e.status " +
            "from employee e left join project p on e.project_id = p.project_id " +
            "join employee e2 on p.project_leader_id = e2.employee_id join team t on e.team_id = t.team_id")
    @ResultMap("EmployeeResultMap")
    List<Employee> findAll();
}
