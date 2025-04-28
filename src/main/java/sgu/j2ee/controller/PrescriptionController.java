package sgu.j2ee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.PrescriptionRequest;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.service.PrescriptionService;

@RequestMapping("/api/prescription")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @PostMapping("/")
    public ResponseData<?> addPrescription(@RequestBody PrescriptionRequest request) {
        Long prescriptionId = prescriptionService.savePrescription(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "add prescription success", prescriptionId);
    }
}
