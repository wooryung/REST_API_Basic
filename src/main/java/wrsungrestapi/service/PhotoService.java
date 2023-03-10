package wrsungrestapi.service;

import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.mapper.PhotoMapper;
import wrsungrestapi.vo.PhotoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private PhotoMapper photoMapper;

    public List<PhotoVo> getPhotoList() {
        List<PhotoVo> list = photoMapper.getPhotoList();
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public List<PhotoVo> getPhotoListByAlbumId(Long albumId) {
        List<PhotoVo> list = photoMapper.getPhotoListByAlbumId(albumId);
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public PhotoVo getPhotoById(Long id) {
        PhotoVo photoVo = photoMapper.getPhotoById(id);
        if (photoVo == null)
            throw new NoSuchDataException("No such data exists.");
        return photoVo;
    }

    public void createPhoto(PhotoVo photoVo) {
        photoMapper.insertPhoto(photoVo);
    }

    public void updatePhoto(PhotoVo photoVo) {
        int result = photoMapper.updatePhoto(photoVo);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }

    public void deletePhoto(Long id) {
        int result = photoMapper.deletePhoto(id);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }
}
