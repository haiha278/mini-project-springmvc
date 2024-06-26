package lg.cns.miniproject.entity;

import lombok.Data;

import java.util.List;

@Data
public class Team {
    private Long teamId;
    private String teamName;
    private List<Employee> employeeList;
}
