package lg.cns.miniproject.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse<T> {
    private int statusCode;
    private T data;

    public BaseResponse(int httpStatus) {
        this.statusCode = httpStatus;
    }

    public BaseResponse(int httpStatus, T data) {
        this.statusCode = httpStatus;
        this.data = data;
    }
}
