package lg.cns.miniproject.sqlBuilder;

import lg.cns.miniproject.dto.employee.FilterEmployee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class EmployeeSqlBuilder {
    public static String builderFilterEmployeeQuery(@Param("filterEmployee") FilterEmployee filterEmployee, @Param("offset") int offset, @Param("limit") int limit) {
        SQL sql = new SQL();
        sql.SELECT("e.employee_id, e.name, e.gender, e.birthday, e.phone, e.email, e.address, " +
                "t.team_name, p.project_name, e2.name as nameLead, e.start_date, e.status");
        sql.FROM("employee e");
        sql.LEFT_OUTER_JOIN("project p ON e.project_id = p.project_id");
        sql.LEFT_OUTER_JOIN("employee e2 ON p.project_leader_id = e2.employee_id");
        sql.LEFT_OUTER_JOIN("team t ON e.team_id = t.team_id");

        if (filterEmployee.getProjectSelect() != null) {
            sql.WHERE("e.project_id = #{filterEmployee.projectSelect}");
        }
        if (filterEmployee.getTeamSelect() != null) {
            sql.WHERE("e.team_id = #{filterEmployee.teamSelect}");
        }

        if (filterEmployee.getSearchInput() != null && !filterEmployee.getSearchInput().isEmpty()) {
            sql.WHERE("(e.name LIKE CONCAT('%', #{filterEmployee.searchInput}, '%') " +
                    "OR e.email LIKE CONCAT('%', #{filterEmployee.searchInput}, '%') " +
                    "OR e.employee_id LIKE CONCAT('%', #{filterEmployee.searchInput}, '%'))");
        }

        if (filterEmployee.getFromDate() != null) {
            sql.WHERE("e.start_date >= #{filterEmployee.fromDate}");
        }
        if (filterEmployee.getEndDate() != null) {
            sql.WHERE("e.start_date <= #{filterEmployee.endDate}");
        }
        if (filterEmployee.getStatus() != null && !filterEmployee.getStatus().isEmpty()) {
            sql.WHERE("e.status = #{filterEmployee.status}");
        }
        sql.ORDER_BY("e.employee_id");
        sql.LIMIT("#{limit}");
        sql.OFFSET("#{offset}");

        String sqlStatement = sql.toString();
        System.out.println(sqlStatement);
        return sqlStatement;
    }

    public static String countFilteredEmployees(FilterEmployee filterEmployee) {
        SQL sql = new SQL();
        sql.SELECT("COUNT(*)");
        sql.FROM("employee e");
        sql.LEFT_OUTER_JOIN("project p ON e.project_id = p.project_id");
        sql.LEFT_OUTER_JOIN("employee e2 ON p.project_leader_id = e2.employee_id");
        sql.LEFT_OUTER_JOIN("team t ON e.team_id = t.team_id");

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
        return sql.toString();
    }
}
