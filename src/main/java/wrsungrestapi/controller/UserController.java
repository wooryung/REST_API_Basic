package wrsungrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.dto.user.*;
import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.service.UserService;
import wrsungrestapi.vo.UserVo;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public GetUserListRespDto getUserList() {
        GetUserListRespDto getUserListRespDto = new GetUserListRespDto();
        try {
            getUserListRespDto.setUserList(userService.getUserList());
        } catch (NoSuchDataException e) {
            getUserListRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getUserListRespDto.setMessage("No such user exists.");
        } catch (Exception e) {
            log.error("[UserController getUserList]", e);
            getUserListRespDto.setCode(ResCode.UNKNOWN.value());
            getUserListRespDto.setMessage(e.getLocalizedMessage());
        }
        return getUserListRespDto;
    }

    @GetMapping("/users/{id}")
    public GetUserRespDto getUser(@PathVariable Long id) {
        GetUserRespDto getUserRespDto = new GetUserRespDto();
        try {
            getUserRespDto.setUser(userService.getUserById(id));
        } catch (NoSuchDataException e) {
            getUserRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getUserRespDto.setMessage("No such user exists.");
        } catch (Exception e) {
            log.error("[UserController getUser]", e);
            getUserRespDto.setCode(ResCode.UNKNOWN.value());
            getUserRespDto.setMessage(e.getLocalizedMessage());
        }
        return getUserRespDto;
    }

    @PostMapping("/users")
    public CreateUserRespDto createUser(@RequestBody CreateUserReqDto createUserReqDto) {
        CreateUserRespDto createUserRespDto = new CreateUserRespDto();
        try {
            UserVo userVo = new UserVo(0L, createUserReqDto.getName(), createUserReqDto.getUsername(), createUserReqDto.getEmail(), createUserReqDto.getPassword(), createUserReqDto.getAddress(), createUserReqDto.getPhone(), createUserReqDto.getWebsite(), createUserReqDto.getCompany());
            userService.createUser(userVo);
        } catch (DuplicateKeyException e) {
            createUserRespDto.setCode(ResCode.DUPLICATE_KEY.value());
            createUserRespDto.setMessage("Duplicate 'username' or 'email'.");
        } catch (DataIntegrityViolationException e) {
            createUserRespDto.setCode(ResCode.NULL_VALUE.value());
            createUserRespDto.setMessage("'username', 'email' are required.");
        } catch (Exception e) {
            log.error("[UserController createUser]", e);
            createUserRespDto.setCode(ResCode.UNKNOWN.value());
            createUserRespDto.setMessage(e.getLocalizedMessage());
        }
        return createUserRespDto;
    }

    @PutMapping("/users/{id}")
    public UpdateUserRespDto updateUser(@PathVariable Long id, @RequestBody UpdateUserReqDto updateUserReqDto) {
        UpdateUserRespDto updateUserRespDto = new UpdateUserRespDto();
        try {
            UserVo userVo = new UserVo(id, updateUserReqDto.getName(), updateUserReqDto.getUsername(), updateUserReqDto.getEmail(), updateUserReqDto.getPassword(), updateUserReqDto.getAddress(), updateUserReqDto.getPhone(), updateUserReqDto.getWebsite(), updateUserReqDto.getCompany());
            userService.updateUser(userVo);
        } catch (NoSuchDataException e) {
            updateUserRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            updateUserRespDto.setMessage("No such user exists.");
        } catch (Exception e) {
            log.error("[UserController updateUser]", e);
            updateUserRespDto.setCode(ResCode.UNKNOWN.value());
            updateUserRespDto.setMessage(e.getLocalizedMessage());
        }
        return updateUserRespDto;
    }

    @DeleteMapping("/users/{id}")
    public DeleteUserRespDto deleteUser(@PathVariable Long id) {
        DeleteUserRespDto deleteUserRespDto = new DeleteUserRespDto();
        try {
            userService.deleteUser(id);
        } catch (NoSuchDataException e) {
            deleteUserRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            deleteUserRespDto.setMessage("No such user exists.");
        } catch (Exception e) {
            log.error("[UserController deleteUser]", e);
            deleteUserRespDto.setCode(ResCode.UNKNOWN.value());
            deleteUserRespDto.setMessage(e.getLocalizedMessage());
        }
        return deleteUserRespDto;
    }
}
