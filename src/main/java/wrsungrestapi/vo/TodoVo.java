package wrsungrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoVo {
    private Long id;
    private Long userId;
    private String title;
    private int completed;
}
