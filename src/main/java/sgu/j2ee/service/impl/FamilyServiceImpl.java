package sgu.j2ee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.AddNewMemberRequest;
import sgu.j2ee.dto.request.FamilyRequest;
import sgu.j2ee.dto.response.FamilyResponse;
import sgu.j2ee.dto.response.UserInFamilyResponse;
import sgu.j2ee.dto.response.UserResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.Family;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.FamilyRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.FamilyService;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyServiceImpl implements FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    @Override
    public void addMember(AddNewMemberRequest request) {
        User user = getUserById(request.getUserId());
        Family family = familyRepository.findByHeadId(request.getHeadId());

        user.setFamily(family);
        user.setRelationshipToHead(request.getRelationship());

        userRepository.save(user);
    }

    @Override
    public void addNewFamily(FamilyRequest request) {
        Family family = Family.builder()
                        .headOfHousehold(getUserById(request.getHeadId()))
                        .build();

        familyRepository.save(family);

        User user = getUserById(request.getHeadId());
        user.setFamily(family);
        user.setRelationshipToHead("Head");
        userRepository.save(user);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
    }

    @Override
    public List<UserInFamilyResponse> viewUserInFamily(Long familyId) {
        List<User> users = familyRepository.findUserByFamilyId(familyId);

        return users.stream().map(user -> UserInFamilyResponse.builder() 
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .relationshipToHead(user.getRelationshipToHead())
                                    .userId(user.getUserId())
                                    .build()
                            ).toList();
    }

    @Override
    public List<UserResponse> searchUser(Long userId, String search) {
        List<User> users = this.familyRepository.findUsersNotInFamilyBySearch(userId, search);

        return users.stream().map(user -> UserResponse.builder()
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .userId(user.getUserId())
                                    .username(user.getUsername())
                                    .build()      
        ).toList();
    }

    @Override
    public FamilyResponse getById(Long familyId) {
        Family f = familyRepository.findById(familyId).orElseThrow(() -> new InvalidDataException("Family not found with id: " + familyId));
        return FamilyResponse.builder()
                .familyId(familyId)
                .headId(f.getHeadOfHousehold().getUserId())
                .build();
    }
                            
}
