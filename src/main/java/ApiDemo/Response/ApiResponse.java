package ApiDemo.Response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private Integer code = 200;
    private Object error;
    private T data;
}
