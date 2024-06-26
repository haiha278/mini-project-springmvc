package lg.cns.miniproject.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Employee {
    private Long employeeId;
    private String employeeName;
    private String gender;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String address;
    private Team team;
    private Project project;
    private LocalDate startDate;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private String status;
    private String nameLead;
}
