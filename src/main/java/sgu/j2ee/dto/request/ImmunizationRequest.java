package sgu.j2ee.dto.request;

import lombok.Getter;

@Getter
public class ImmunizationRequest {
    Long userId;
    String provider;
    String vaccineName;
}
