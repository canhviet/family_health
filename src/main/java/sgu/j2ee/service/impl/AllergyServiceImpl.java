package sgu.j2ee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.AllergyRequest;
import sgu.j2ee.dto.response.AllergyResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.Allergies;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.AllergyRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.AllergyService;

@Service
@RequiredArgsConstructor
public class AllergyServiceImpl implements AllergyService {
    private final AllergyRepository allergyRepository;
    private final UserRepository userRepository;

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
    }

    @Override
    public Long add(AllergyRequest request) {
        Allergies a = Allergies.builder()
                    .allergen(request.getAllergen())
                    .reaction(request.getReaction())
                    .user(getUserById(request.getUserId()))
                    .severity(request.getSeverity())
                    .build();
        allergyRepository.save(a);

        return a.getAllergyId();
    }

    @Override
    public List<AllergyResponse> getByUserId(Long userId) {
        List<Allergies> list = allergyRepository.findByUser(getUserById(userId));

        return list.stream().map(a -> AllergyResponse.builder() 
                                .allergen(a.getAllergen())
                                .allergyId(a.getAllergyId())
                                .reaction(a.getReaction())
                                .severity(a.getSeverity())
                                .userId(userId)
                                .build()
        ).toList();
    }

}
