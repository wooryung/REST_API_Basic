package wrsungrestapi.dto.photo;

import lombok.Data;

@Data
public class CreatePhotoReqDto {
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
