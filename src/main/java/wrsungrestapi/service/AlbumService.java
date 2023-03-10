package wrsungrestapi.service;

import wrsungrestapi.exception.NoSuchDataException;
import wrsungrestapi.mapper.AlbumMapper;
import wrsungrestapi.vo.AlbumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    public List<AlbumVo> getAlbumList() {
        List<AlbumVo> list = albumMapper.getAlbumList();
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public List<AlbumVo> getAlbumListByUserId(Long userId) {
        List<AlbumVo> list = albumMapper.getAlbumListByUserId(userId);
        if (list.isEmpty())
            throw new NoSuchDataException("No such data exists.");
        return list;
    }

    public AlbumVo getAlbumById(Long id) {
        AlbumVo albumVo = albumMapper.getAlbumById(id);
        if (albumVo == null)
            throw new NoSuchDataException("No such data exists.");
        return albumVo;
    }

    public void createAlbum(AlbumVo albumVo) {
        albumMapper.insertAlbum(albumVo);
    }

    public void updateAlbum(AlbumVo albumVo) {
        int result = albumMapper.updateAlbum(albumVo);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }

    public void deleteAlbum(Long id) {
        int result = albumMapper.deleteAlbum(id);
        if (result == 0)
            throw new NoSuchDataException("No such data exists.");
    }
}
