// TestResultsServiceImpl.java
package sgu.j2ee.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sgu.j2ee.dto.request.TestResultsRequest;
import sgu.j2ee.dto.response.PageResponse;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.dto.response.TestResultsResponse;
import sgu.j2ee.model.TestResults;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.TestResultsRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.TestResultsService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestResultsServiceImpl implements TestResultsService {

    private final TestResultsRepository testResultsRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseData<?> getAllTestResults() {
        List<TestResults> results = testResultsRepository.findAll();
        List<TestResultsResponse> responseList = results.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        
        return new ResponseData<>(HttpStatus.OK.value(), "All test results retrieved", responseList);
    }

    @Override
    public PageResponse<?> getAllTestResults(int pageNo, int pageSize) {
        Page<TestResults> page = testResultsRepository.findAll(PageRequest.of(pageNo, pageSize));
        
        List<TestResultsResponse> responseList = page.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(responseList)
                .build();
    }

    @Override
    public ResponseData<?> getTestResultsByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(), "User not found", null);
        }
        
        List<TestResults> results = testResultsRepository.findByUser(user);
        List<TestResultsResponse> responseList = results.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        
        return new ResponseData<>(HttpStatus.OK.value(), "Test results for user " + userId, responseList);
    }

    @Override
    public ResponseData<?> getTestResultsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<TestResults> results = testResultsRepository.findByDatePerformedBetween(startDate, endDate);
        List<TestResultsResponse> responseList = results.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        
        return new ResponseData<>(HttpStatus.OK.value(), 
                "Test results between " + startDate + " and " + endDate, 
                responseList);
    }

    @Override
    public ResponseData<?> getTestResultsByType(String testType) {
        List<TestResults> results = testResultsRepository.findByTestType(testType);
        List<TestResultsResponse> responseList = results.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        
        return new ResponseData<>(HttpStatus.OK.value(), 
                "Test results of type " + testType, 
                responseList);
    }

    @Override
    public ResponseData<?> createTestResult(TestResultsRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "User not found", null);
        }
        
        TestResults testResult = TestResults.builder()
                .user(user)
                .testType(request.getTestType())
                .result(request.getResult())
                .datePerformed(request.getDatePerformed() != null ? 
                        request.getDatePerformed() : LocalDate.now())
                .labName(request.getLabName())
                .build();
        
        TestResults savedResult = testResultsRepository.save(testResult);
        log.info("Test result created with ID: {}", savedResult.getTestId());
        
        return new ResponseData<>(HttpStatus.CREATED.value(), 
                "Test result created successfully", 
                mapToResponse(savedResult));
    }

    @Override
    public ResponseData<?> updateTestResult(Long testId, TestResultsRequest request) {
        TestResults existingResult = testResultsRepository.findById(testId).orElse(null);
        if (existingResult == null) {
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(), "Test result not found", null);
        }
        
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "User not found", null);
        }
        
        existingResult.setUser(user);
        existingResult.setTestType(request.getTestType());
        existingResult.setResult(request.getResult());
        existingResult.setDatePerformed(request.getDatePerformed());
        existingResult.setLabName(request.getLabName());
        
        TestResults updatedResult = testResultsRepository.save(existingResult);
        log.info("Test result updated with ID: {}", testId);
        
        return new ResponseData<>(HttpStatus.OK.value(), 
                "Test result updated successfully", 
                mapToResponse(updatedResult));
    }

    @Override
    public ResponseData<?> deleteTestResult(Long testId) {
        if (!testResultsRepository.existsById(testId)) {
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(), "Test result not found", null);
        }
        
        testResultsRepository.deleteById(testId);
        log.info("Test result deleted with ID: {}", testId);
        
        return new ResponseData<>(HttpStatus.OK.value(), "Test result deleted successfully", null);
    }

    @Override
    public ResponseData<?> getTestResultById(Long testId) {
        TestResults result = testResultsRepository.findById(testId).orElse(null);
        if (result == null) {
            return new ResponseData<>(HttpStatus.NOT_FOUND.value(), "Test result not found", null);
        }
        
        return new ResponseData<>(HttpStatus.OK.value(), 
                "Test result retrieved", 
                mapToResponse(result));
    }

    private TestResultsResponse mapToResponse(TestResults testResults) {
        return TestResultsResponse.builder()
                .testId(testResults.getTestId())
                .userId(testResults.getUser().getUserId())
                .testType(testResults.getTestType())
                .result(testResults.getResult())
                .datePerformed(testResults.getDatePerformed())
                .labName(testResults.getLabName())
                .build();
    }
}