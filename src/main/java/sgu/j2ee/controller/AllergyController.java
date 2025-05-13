package sgu.j2ee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.AllergyRequest;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.service.AllergyService;

@RestController
@RequestMapping("/api/allergy")
@RequiredArgsConstructor
public class AllergyController {
    private final AllergyService allergyService;

    @PostMapping("/")
    public ResponseData<?> add(@RequestBody AllergyRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "add new allergy", this.allergyService.add(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseData<?> viewByUserId(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(), "view by userId", this.allergyService.getByUserId(userId));
    }
}
