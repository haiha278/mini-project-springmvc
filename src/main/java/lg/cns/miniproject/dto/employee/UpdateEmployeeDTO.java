package lg.cns.miniproject.dto.employee;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UpdateEmployeeDTO {
    private long employeeId;
    private String name;
    private String phoneNumber;
    private LocalDate dob;
    private Long teamSelect;
    private Boolean status;
    private String gender;
    private String address;
    private LocalDate startDate;
    private Long projectSelect;
    private String email;
}
