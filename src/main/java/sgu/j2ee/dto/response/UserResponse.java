package sgu.j2ee.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Date dob;
    private String gender;
    private String phone;
    private String address;
    private String healthInsuranceCode;
    private String cityzenId;
    private Long familyId;
}
