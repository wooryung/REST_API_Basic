package wrsungrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import wrsungrestapi.consts.ResCode;
import wrsungrestapi.dto.photo.*;
import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.service.PhotoService;
import wrsungrestapi.vo.PhotoVo;

@Slf4j
@RestController
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/photos")
    public GetPhotoListRespDto getPhotoList(@RequestParam(value = "albumId", required = false) Long albumId) {
        GetPhotoListRespDto getPhotoListRespDto = new GetPhotoListRespDto();
        try {
            if (albumId != null)
                getPhotoListRespDto.setPhotoList(photoService.getPhotoListByAlbumId(albumId));
            else
                getPhotoListRespDto.setPhotoList(photoService.getPhotoList());
        } catch (NoSuchDataException e) {
            getPhotoListRespDto.setCode(ResCode.SUCCESS.value());
            getPhotoListRespDto.setMessage("No such photo exists.");
        } catch (Exception e) {
            log.error("[PhotoController getPhotoList]", e);
            getPhotoListRespDto.setCode(ResCode.UNKNOWN.value());
            getPhotoListRespDto.setMessage(e.getLocalizedMessage());
        }
        return getPhotoListRespDto;
    }

    @GetMapping("/photos/{id}")
    public GetPhotoRespDto getPhoto(@PathVariable Long id) {
        GetPhotoRespDto getPhotoRespDto = new GetPhotoRespDto();
        try {
            getPhotoRespDto.setPhoto(photoService.getPhotoById(id));
        } catch (NoSuchDataException e) {
            getPhotoRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            getPhotoRespDto.setMessage("No such photo exists.");
        } catch (Exception e) {
            log.error("[PhotoController getPhoto]", e);
            getPhotoRespDto.setCode(ResCode.UNKNOWN.value());
            getPhotoRespDto.setMessage(e.getLocalizedMessage());
        }
        return getPhotoRespDto;
    }

    @PostMapping("/photos")
    public CreatePhotoRespDto createPhoto(@RequestBody CreatePhotoReqDto createPhotoReqDto) {
        CreatePhotoRespDto createPhotoRespDto = new CreatePhotoRespDto();
        try {
            PhotoVo photoVo = new PhotoVo(0L, createPhotoReqDto.getAlbumId(), createPhotoReqDto.getTitle(), createPhotoReqDto.getUrl(), createPhotoReqDto.getThumbnailUrl());
            photoService.createPhoto(photoVo);
        } catch (DataIntegrityViolationException e) {
            createPhotoRespDto.setCode(ResCode.NULL_VALUE.value());
            createPhotoRespDto.setMessage("'albumId', 'title', 'url', 'thumbnailUrl' are required.");
        } catch (Exception e) {
            log.error("[PhotoController createPhoto]", e);
            createPhotoRespDto.setCode(ResCode.UNKNOWN.value());
            createPhotoRespDto.setMessage(e.getLocalizedMessage());
        }
        return createPhotoRespDto;
    }

    @PutMapping("/photos/{id}")
    public UpdatePhotoRespDto updatePhoto(@PathVariable Long id, @RequestBody UpdatePhotoReqDto updatePhotoReqDto) {
        UpdatePhotoRespDto updatePhotoRespDto = new UpdatePhotoRespDto();
        try {
            PhotoVo photoVo = new PhotoVo(id, 0L, updatePhotoReqDto.getTitle(), updatePhotoReqDto.getUrl(), updatePhotoReqDto.getThumbnailUrl());
            photoService.updatePhoto(photoVo);
        } catch (NoSuchDataException e) {
            updatePhotoRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            updatePhotoRespDto.setMessage("No such photo exists.");
        } catch (Exception e) {
            log.error("[PhotoController updatePhoto]", e);
            updatePhotoRespDto.setCode(ResCode.UNKNOWN.value());
            updatePhotoRespDto.setMessage(e.getLocalizedMessage());
        }
        return updatePhotoRespDto;
    }

    @DeleteMapping("/photos/{id}")
    public DeletePhotoRespDto deletePhoto(@PathVariable Long id) {
        DeletePhotoRespDto deletePhotoRespDto = new DeletePhotoRespDto();
        try {
            photoService.deletePhoto(id);
        } catch (NoSuchDataException e) {
            deletePhotoRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            deletePhotoRespDto.setMessage("No such photo exists.");
        } catch (Exception e) {
            log.error("[PhotoController deletePhoto]", e);
            deletePhotoRespDto.setCode(ResCode.UNKNOWN.value());
            deletePhotoRespDto.setMessage(e.getLocalizedMessage());
        }
        return deletePhotoRespDto;
    }
}
