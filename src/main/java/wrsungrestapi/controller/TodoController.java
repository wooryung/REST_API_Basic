package wrsungrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.dto.todo.*;
import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.service.TodoService;
import wrsungrestapi.vo.TodoVo;

@Slf4j
@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public GetTodoListRespDto getTodoList(@RequestParam(value = "userId", required = false) Long userId) {
        GetTodoListRespDto getTodoListRespDto = new GetTodoListRespDto();
        try {
            if (userId != null)
                getTodoListRespDto.setTodoList(todoService.getTodoListByUserId(userId));
            else
                getTodoListRespDto.setTodoList(todoService.getTodoList());
        } catch (NoSuchDataException e) {
            getTodoListRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getTodoListRespDto.setMessage("No such todo exists.");
        } catch (Exception e) {
            log.error("[TodoController getTodoList]", e);
            getTodoListRespDto.setCode(ResCode.UNKNOWN.value());
            getTodoListRespDto.setMessage(e.getLocalizedMessage());
        }
        return getTodoListRespDto;
    }

    @GetMapping("/todos/{id}")
    public GetTodoRespDto getTodo(@PathVariable Long id) {
        GetTodoRespDto getTodoRespDto = new GetTodoRespDto();
        try {
            getTodoRespDto.setTodo(todoService.getTodoById(id));
        } catch (NoSuchDataException e) {
            getTodoRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getTodoRespDto.setMessage("No such todo exists.");
        } catch (Exception e) {
            log.error("[TodoController getTodo]", e);
            getTodoRespDto.setCode(ResCode.UNKNOWN.value());
            getTodoRespDto.setMessage(e.getLocalizedMessage());
        }
        return getTodoRespDto;
    }

    @PostMapping("/todos")
    public CreateTodoRespDto createTodo(@RequestBody CreateTodoReqDto createTodoReqDto) {
        CreateTodoRespDto createTodoRespDto = new CreateTodoRespDto();
        try {
            TodoVo todoVo = new TodoVo(0L, createTodoReqDto.getUserId(), createTodoReqDto.getTitle(), createTodoReqDto.getCompleted());
            todoService.createTodo(todoVo);
        } catch (DataIntegrityViolationException e) {
            createTodoRespDto.setCode(ResCode.NULL_VALUE.value());
            createTodoRespDto.setMessage("'userId', 'title', 'completed' are required.");
        } catch (Exception e) {
            log.error("[TodoController createTodo]", e);
            createTodoRespDto.setCode(ResCode.UNKNOWN.value());
            createTodoRespDto.setMessage(e.getLocalizedMessage());
        }
        return createTodoRespDto;
    }

    @PutMapping("/todos/{id}")
    public UpdateTodoRespDto updateTodo(@PathVariable Long id, @RequestBody UpdateTodoReqDto updateTodoReqDto) {
        UpdateTodoRespDto updateTodoRespDto = new UpdateTodoRespDto();
        try {
            TodoVo todoVo = new TodoVo(id, 0L, updateTodoReqDto.getTitle(), updateTodoReqDto.getCompleted());
            todoService.updateTodo(todoVo);
        } catch (NoSuchDataException e) {
            updateTodoRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            updateTodoRespDto.setMessage("No such todo exists.");
        } catch (Exception e) {
            log.error("[TodoController updateTodo]", e);
            updateTodoRespDto.setCode(ResCode.UNKNOWN.value());
            updateTodoRespDto.setMessage(e.getLocalizedMessage());
        }
        return updateTodoRespDto;
    }

    @DeleteMapping("/todos/{id}")
    public DeleteTodoRespDto deleteTodo(@PathVariable Long id) {
        DeleteTodoRespDto deleteTodoRespDto = new DeleteTodoRespDto();
        try {
            todoService.deleteTodo(id);
        } catch (NoSuchDataException e) {
            deleteTodoRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            deleteTodoRespDto.setMessage("No such todo exists.");
        } catch (Exception e) {
            log.error("[TodoController deleteTodo]", e);
            deleteTodoRespDto.setCode(ResCode.UNKNOWN.value());
            deleteTodoRespDto.setMessage(e.getLocalizedMessage());
        }
        return deleteTodoRespDto;
    }
}