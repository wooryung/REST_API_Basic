package wrsungrestapi.dto.todo;

import com.fasterxml.jackson.annotation.JsonInclude;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.vo.TodoVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetTodoListRespDto {
    private List<TodoVo> todoList;
    private int code = ResCode.SUCCESS.value();
    private String message;
}
