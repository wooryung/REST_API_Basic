package wrsungrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.dto.album.*;
import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.service.AlbumService;
import wrsungrestapi.vo.AlbumVo;

@Slf4j
@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/albums")
    public GetAlbumListRespDto getAlbumList(@RequestParam(value = "userId", required = false) Long userId) {
        GetAlbumListRespDto getAlbumListRespDto = new GetAlbumListRespDto();
        try {
            if (userId != null)
                getAlbumListRespDto.setAlbumList(albumService.getAlbumListByUserId(userId));
            else
                getAlbumListRespDto.setAlbumList(albumService.getAlbumList());
        } catch (NoSuchDataException e) {
            getAlbumListRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getAlbumListRespDto.setMessage("No such album exists.");
        } catch (Exception e) {
            log.error("[AlbumController getAlbumList]", e);
            getAlbumListRespDto.setCode(ResCode.UNKNOWN.value());
            getAlbumListRespDto.setMessage(e.getLocalizedMessage());
        }
        return getAlbumListRespDto;
    }

    @GetMapping("/albums/{id}")
    public GetAlbumRespDto getAlbum(@PathVariable Long id) {
        GetAlbumRespDto getAlbumRespDto = new GetAlbumRespDto();
        try {
            getAlbumRespDto.setAlbum(albumService.getAlbumById(id));
        } catch (NoSuchDataException e) {
            getAlbumRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getAlbumRespDto.setMessage("No such album exists.");
        } catch (Exception e) {
            log.error("[AlbumController getAlbum]", e);
            getAlbumRespDto.setCode(ResCode.UNKNOWN.value());
            getAlbumRespDto.setMessage(e.getLocalizedMessage());
        }
        return getAlbumRespDto;
    }

    @PostMapping("/albums")
    public CreateAlbumRespDto createAlbum(@RequestBody CreateAlbumReqDto createAlbumReqDto) {
        CreateAlbumRespDto createAlbumRespDto = new CreateAlbumRespDto();
        try {
            AlbumVo albumVo = new AlbumVo(0L, createAlbumReqDto.getUserId(), createAlbumReqDto.getTitle());
            albumService.createAlbum(albumVo);
        } catch (DataIntegrityViolationException e) {
            createAlbumRespDto.setCode(ResCode.NULL_VALUE.value());
            createAlbumRespDto.setMessage("'userId', 'title' are required.");
        } catch (Exception e) {
            log.error("[AlbumController createAlbum]", e);
            createAlbumRespDto.setCode(ResCode.UNKNOWN.value());
            createAlbumRespDto.setMessage(e.getLocalizedMessage());
        }
        return createAlbumRespDto;
    }

    @PutMapping("/albums/{id}")
    public UpdateAlbumRespDto updateAlbum(@PathVariable Long id, @RequestBody UpdateAlbumReqDto updateAlbumReqDto) {
        UpdateAlbumRespDto updateAlbumRespDto = new UpdateAlbumRespDto();
        try {
            AlbumVo albumVo = new AlbumVo(id, 0L, updateAlbumReqDto.getTitle());
            albumService.updateAlbum(albumVo);
        } catch (NoSuchDataException e) {
            updateAlbumRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            updateAlbumRespDto.setMessage("No such album exists.");
        } catch (Exception e) {
            log.error("[AlbumController updateAlbum]", e);
            updateAlbumRespDto.setCode(ResCode.UNKNOWN.value());
            updateAlbumRespDto.setMessage(e.getLocalizedMessage());
        }
        return updateAlbumRespDto;
    }

    @DeleteMapping("/albums/{id}")
    public DeleteAlbumRespDto deleteAlbum(@PathVariable Long id) {
        DeleteAlbumRespDto deleteAlbumRespDto = new DeleteAlbumRespDto();
        try {
            albumService.deleteAlbum(id);
        } catch (NoSuchDataException e) {
            deleteAlbumRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            deleteAlbumRespDto.setMessage("No such album exists.");
        } catch (Exception e) {
            log.error("[AlbumController deleteAlbum]", e);
            deleteAlbumRespDto.setCode(ResCode.UNKNOWN.value());
            deleteAlbumRespDto.setMessage(e.getLocalizedMessage());
        }
        return deleteAlbumRespDto;
    }
}
