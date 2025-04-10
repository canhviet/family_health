// TestResultsController.java
package sgu.j2ee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sgu.j2ee.dto.response.PageResponse;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.dto.request.TestResultsRequest;
import sgu.j2ee.service.TestResultsService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/test-results")
@RequiredArgsConstructor
public class TestResultsController {

    private final TestResultsService testResultsService;

    @GetMapping
    public ResponseData<?> getAllTestResults() {
        return testResultsService.getAllTestResults();
    }

    @GetMapping("/paged")
    public PageResponse<?> getAllTestResultsPaged(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        return testResultsService.getAllTestResults(pageNo, pageSize);
    }

    @GetMapping("/user/{userId}")
    public ResponseData<?> getTestResultsByUser(@PathVariable Long userId) {
        return testResultsService.getTestResultsByUser(userId);
    }

    @GetMapping("/date-range")
    public ResponseData<?> getTestResultsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return testResultsService.getTestResultsByDateRange(startDate, endDate);
    }

    @GetMapping("/type/{testType}")
    public ResponseData<?> getTestResultsByType(@PathVariable String testType) {
        return testResultsService.getTestResultsByType(testType);
    }

    @PostMapping
    public ResponseData<?> createTestResult(@RequestBody TestResultsRequest request) {
        return testResultsService.createTestResult(request);
    }

    @PutMapping("/{testId}")
    public ResponseData<?> updateTestResult(
            @PathVariable Long testId,
            @RequestBody TestResultsRequest request) {
        return testResultsService.updateTestResult(testId, request);
    }

    @DeleteMapping("/{testId}")
    public ResponseData<?> deleteTestResult(@PathVariable Long testId) {
        return testResultsService.deleteTestResult(testId);
    }

    @GetMapping("/{testId}")
    public ResponseData<?> getTestResultById(@PathVariable Long testId) {
        return testResultsService.getTestResultById(testId);
    }
}