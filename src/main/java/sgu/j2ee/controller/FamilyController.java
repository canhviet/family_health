package sgu.j2ee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.AddNewMemberRequest;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.service.FamilyService;

@RestController
@RequestMapping("/api/family")
@RequiredArgsConstructor
public class FamilyController {
    private final FamilyService familyService;

    @PostMapping("/add-new-member")
    public ResponseData<?> AddNewMember(@RequestBody AddNewMemberRequest request) {
        this.familyService.addMember(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "add new member success");
    }

    @GetMapping("/user/{familyId}")
    public ResponseData<?> viewUserInFamily(@PathVariable Long familyId) {
        return new ResponseData<>(HttpStatus.OK.value(), "view user in family success", this.familyService.viewUserInFamily(familyId));
    }

    @GetMapping("/{userId}/{search}")
    public ResponseData<?> search(@PathVariable("userId") Long userId, @PathVariable(value = "search", required = false) String search) {
        return new ResponseData<>(HttpStatus.OK.value(), "search user", this.familyService.searchUser(userId, search));
    }
}
