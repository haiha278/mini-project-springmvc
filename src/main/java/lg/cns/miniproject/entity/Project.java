package lg.cns.miniproject.entity;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    private Long projectId;
    private String projectName;
    private Employee projectLeader;
    private List<Employee> employeeList;
}
