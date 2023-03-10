package wrsungrestapi.mapper;

import wrsungrestapi.vo.TodoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<TodoVo> getTodoList();
    List<TodoVo> getTodoListByUserId(Long userId);
    TodoVo getTodoById(Long id);
    void insertTodo(TodoVo todoVo);
    int updateTodo(TodoVo todoVo);
    int deleteTodo(Long id);
}
