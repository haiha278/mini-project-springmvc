package lg.cns.miniproject.dto.employee;

import lg.cns.miniproject.entity.Project;
import lg.cns.miniproject.entity.Team;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeInfomationDTO {
    private Long employeeId;
    private String employeeName;
    private String gender;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String address;
    private Long teamId;
    private String teamName;
    private Long projectId;
    private String projectName;
    private LocalDate startDate;
    private String status;
}
