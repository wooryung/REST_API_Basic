package wrsungrestapi.mapper;

import wrsungrestapi.vo.AlbumVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlbumMapper {
    List<AlbumVo> getAlbumList();
    List<AlbumVo> getAlbumListByUserId(Long userId);
    AlbumVo getAlbumById(Long id);
    void insertAlbum(AlbumVo albumVo);
    int updateAlbum(AlbumVo albumVo);
    int deleteAlbum(Long id);
}
