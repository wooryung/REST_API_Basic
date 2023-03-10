package wrsungrestapi.dto.photo;

import lombok.Data;

@Data
public class UpdatePhotoReqDto {
    private String title;
    private String url;
    private String thumbnailUrl;
}
