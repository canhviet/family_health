package sgu.j2ee.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AllergyResponse {
    private String allergen;
    private String reaction;
    private String severity;
    private Long userId;
    private Long allergyId;
}
