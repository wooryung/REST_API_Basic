package wrsungrestapi.dto.comment;

import lombok.Data;

@Data
public class UpdateCommentReqDto {
    private String name;
    private String email;
    private String body;
}
