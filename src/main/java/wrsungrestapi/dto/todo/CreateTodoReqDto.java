package wrsungrestapi.dto.todo;

import lombok.Data;

@Data
public class CreateTodoReqDto {
    private Long userId;
    private String title;
    private int completed;
}
