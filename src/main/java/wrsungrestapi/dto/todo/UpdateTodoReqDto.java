package wrsungrestapi.dto.todo;

import lombok.Data;

@Data
public class UpdateTodoReqDto {
    private String title;
    private int completed;
}
