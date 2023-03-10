package wrsungrestapi.service;

import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.mapper.TodoMapper;
import wrsungrestapi.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    public List<TodoVo> getTodoList() {
        List<TodoVo> list = todoMapper.getTodoList();
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public List<TodoVo> getTodoListByUserId(Long userId) {
        List<TodoVo> list = todoMapper.getTodoListByUserId(userId);
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public TodoVo getTodoById(Long id) {
        TodoVo todoVo = todoMapper.getTodoById(id);
        if (todoVo == null)
            throw new NoSuchDataException("No such data exists.");
        return todoVo;
    }

    public void createTodo(TodoVo todoVo) {
        todoMapper.insertTodo(todoVo);
    }

    public void updateTodo(TodoVo todoVo) {
        int result = todoMapper.updateTodo(todoVo);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }

    public void deleteTodo(Long id) {
        int result = todoMapper.deleteTodo(id);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }
}
