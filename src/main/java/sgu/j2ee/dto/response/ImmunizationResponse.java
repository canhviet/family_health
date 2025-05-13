package sgu.j2ee.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImmunizationResponse {
    private Long userId;
    private String provider;
    private String vaccineName;
    private Date dateAdministered;
    private Long immunizationId;
}
