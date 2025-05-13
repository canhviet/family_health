package sgu.j2ee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.ImmunizationRequest;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.service.ImmunizationService;

@RestController
@RequestMapping("/api/immunization")
@RequiredArgsConstructor
public class ImmunizationController {
    private final ImmunizationService immunizationService;

    @PostMapping("/")
    public ResponseData<?> add(@RequestBody ImmunizationRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "add new immunization", this.immunizationService.add(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseData<?> viewByUserId(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(), "view by userId", this.immunizationService.getByUserId(userId));
    }
}
