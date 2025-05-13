package sgu.j2ee.dto.request;

import lombok.Getter;

@Getter
public class AllergyRequest {
    String allergen;
    String reaction;
    String severity;
    Long userId;
}
