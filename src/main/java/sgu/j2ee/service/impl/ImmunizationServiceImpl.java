package sgu.j2ee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.ImmunizationRequest;
import sgu.j2ee.dto.response.ImmunizationResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.Immunizations;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.ImmunizationRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.ImmunizationService;

@Service
@RequiredArgsConstructor
public class ImmunizationServiceImpl implements ImmunizationService {
    private final ImmunizationRepository immunizationRepository;
    private final UserRepository userRepository;

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
    }


    @Override
    public Long add(ImmunizationRequest request) {
        Immunizations i = Immunizations.builder()
                            .provider(request.getProvider())
                            .vaccineName(request.getVaccineName())
                            .user(getUserById(request.getUserId()))
                            .build();

        immunizationRepository.save(i);

        return i.getImmunizationId();
        
    }


    @Override
    public List<ImmunizationResponse> getByUserId(Long userId) {
        List<Immunizations> list = immunizationRepository.findByUser(getUserById(userId));

        return list.stream().map(i -> ImmunizationResponse.builder()
                                        .dateAdministered(i.getDateAdministered())
                                        .immunizationId(i.getImmunizationId())
                                        .vaccineName(i.getVaccineName())
                                        .userId(i.getUser().getUserId())
                                        .provider(i.getProvider())
                                        .build()
        ).toList();
    }

}
