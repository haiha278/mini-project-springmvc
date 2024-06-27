package lg.cns.miniproject.dto.employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FilterEmployee {
    private Long teamSelect;
    private Long projectSelect;
    private String status;
    private LocalDate fromDate;
    private LocalDate endDate;
    private String searchInput;
}
