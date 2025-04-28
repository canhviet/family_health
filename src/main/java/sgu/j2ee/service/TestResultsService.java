// TestResultsService.java
package sgu.j2ee.service;

import sgu.j2ee.dto.request.TestResultsRequest;
import sgu.j2ee.dto.response.PageResponse;
import sgu.j2ee.dto.response.ResponseData;

import java.time.LocalDate;

public interface TestResultsService {
    ResponseData<?> getAllTestResults();
    PageResponse<?> getAllTestResults(int pageNo, int pageSize);
    ResponseData<?> getTestResultsByUser(Long userId);
    ResponseData<?> getTestResultsByDateRange(LocalDate startDate, LocalDate endDate);
    ResponseData<?> getTestResultsByType(String testType);
    ResponseData<?> createTestResult(TestResultsRequest request);
    ResponseData<?> updateTestResult(Long testId, TestResultsRequest request);
    ResponseData<?> deleteTestResult(Long testId);
    ResponseData<?> getTestResultById(Long testId);
}