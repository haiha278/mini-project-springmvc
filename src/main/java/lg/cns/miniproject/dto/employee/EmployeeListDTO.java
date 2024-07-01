package lg.cns.miniproject.dto.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeListDTO {
    private Long employeeId;
    private String employeeName;
    private String gender;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String address;
    private LocalDate startDate;
    private String status;
    private String teamName;
    private String projectName;
    private String nameLead;
}
