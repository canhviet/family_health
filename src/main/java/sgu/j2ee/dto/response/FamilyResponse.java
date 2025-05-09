package sgu.j2ee.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FamilyResponse {
    Long familyId;
    Long headId;
}
