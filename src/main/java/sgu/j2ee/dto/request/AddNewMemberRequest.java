package sgu.j2ee.dto.request;

import lombok.Getter;

@Getter
public class AddNewMemberRequest {
    private Long headId;
    private Long userId;
    private String relationship;
}
