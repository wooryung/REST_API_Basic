package wrsungrestapi.dto.user;

import lombok.Data;

@Data
public class UpdateUserReqDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String website;
    private String company;
}
