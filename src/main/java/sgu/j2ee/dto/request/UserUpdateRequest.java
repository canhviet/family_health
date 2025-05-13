package sgu.j2ee.dto.request;

import java.util.Date;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String gender;
    private String phone;
    private String address;
    private String healthInsuranceCode;
    private String citizenId;
}
