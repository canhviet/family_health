package sgu.j2ee.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInFamilyResponse {
    private String relationshipToHead;
    private Long userId;
    private String firstName;
    private String lastName;
}
