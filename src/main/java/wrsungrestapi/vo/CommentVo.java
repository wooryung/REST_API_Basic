package wrsungrestapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentVo {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
