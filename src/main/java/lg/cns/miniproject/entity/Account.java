package lg.cns.miniproject.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {
    private Long id;
    private String username;
    private String password;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;

}
