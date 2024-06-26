package lg.cns.miniproject.dto.auth;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String confirmPassword;
    private Long createBy;
    private Long updateBy;
}
