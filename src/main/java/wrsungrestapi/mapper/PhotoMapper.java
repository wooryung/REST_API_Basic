package wrsungrestapi.mapper;

import wrsungrestapi.vo.PhotoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {
    List<PhotoVo> getPhotoList();
    List<PhotoVo> getPhotoListByAlbumId(Long albumId);
    PhotoVo getPhotoById(Long id);
    void insertPhoto(PhotoVo photoVo);
    int updatePhoto(PhotoVo photoVo);
    int deletePhoto(Long id);
}
