package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.request.AddNewMemberRequest;
import sgu.j2ee.dto.request.FamilyRequest;
import sgu.j2ee.dto.response.FamilyResponse;
import sgu.j2ee.dto.response.UserInFamilyResponse;
import sgu.j2ee.dto.response.UserResponse;

public interface FamilyService {
    void addMember(AddNewMemberRequest request);
    void addNewFamily(FamilyRequest request);
    List<UserInFamilyResponse> viewUserInFamily(Long familyId);
    List<UserResponse> searchUser(Long userId, String search);
    FamilyResponse getById(Long familyId);
}
