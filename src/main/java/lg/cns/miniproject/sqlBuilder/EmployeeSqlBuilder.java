package lg.cns.miniproject.sqlBuilder;

import lg.cns.miniproject.dto.employee.FilterEmployee;
import org.apache.ibatis.jdbc.SQL;

public class EmployeeSqlBuilder {
    public static String builderFilterEmployeeQuery(FilterEmployee filterEmployee) {
        SQL sql = new SQL();
        sql.SELECT("e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address, " +
                "t.team_name, p.project_name, e2.name as nameLead, e.start_date, e.status");
        sql.FROM("employee e");
        sql.JOIN("project p ON e.project_id = p.project_id");
        sql.JOIN("employee e2 ON p.project_leader_id = e2.employee_id");
        sql.JOIN("team t ON e.team_id = t.team_id");

        if (filterEmployee.getProjectSelect() != null) {
            sql.WHERE("e.project_id = #{projectSelect}");
        }
        if (filterEmployee.getTeamSelect() != null) {
            sql.WHERE("e.team_id = #{teamSelect}");
        }

        if (filterEmployee.getSearchInput() != null && !filterEmployee.getSearchInput().isEmpty()) {
            sql.WHERE("(e.name LIKE CONCAT('%', #{searchInput}, '%') " +
                    "OR e.email LIKE CONCAT('%', #{searchInput}, '%') " +
                    "OR e.employee_id LIKE CONCAT('%', #{searchInput}, '%'))");
        }

        if (filterEmployee.getFromDate() != null) {
            sql.WHERE("e.start_date >= #{fromDate}");
        }
        if (filterEmployee.getEndDate() != null) {
            sql.WHERE("e.start_date <= #{endDate}");
        }
        if (filterEmployee.getStatus() != null && !filterEmployee.getStatus().isEmpty()) {
            sql.WHERE("e.status = #{status}");
        }

        String sqlStatement = sql.toString();
        System.out.println(sqlStatement);
        return sqlStatement;
    }
}
