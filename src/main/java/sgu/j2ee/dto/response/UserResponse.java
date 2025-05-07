package sgu.j2ee.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    Long userId;
    String firstName;
    String lastName;
    String username;
}
