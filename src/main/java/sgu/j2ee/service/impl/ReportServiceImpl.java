package sgu.j2ee.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired; // Not needed with @RequiredArgsConstructor

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sgu.j2ee.model.Prescriptions;
// import sgu.j2ee.dto.response.TestResultsResponse; // This import was not used
import sgu.j2ee.model.TestResults;
import sgu.j2ee.repository.PrescriptionRepository;
import sgu.j2ee.repository.TestResultsRepository;
import sgu.j2ee.service.ReportService;
import sgu.j2ee.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final TestResultsRepository testResultsRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Override
    public byte[] exportTestResultReport(int testResultId) throws JRException {
        logger.info("Starting exportTestResultReport for testResultId: {}", testResultId);

        // Fetch test result data
        TestResults testResult = testResultsRepository.findById((long) testResultId)
                .orElseThrow(() -> {
                    String errorMessage = "Test result not found with ID: " + testResultId;
                    logger.error(errorMessage);
                    return new IllegalArgumentException(errorMessage);
                });
        logger.info("Fetched test result data for ID: {}", testResultId);

        // Fetch associated user information
        User user = testResult.getUser();
        if (user == null) {
            String errorMessage = "User not found for test result ID: " + testResultId;
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        logger.info("Fetched user information for testResultId: {}, username: {}", testResultId, user.getUsername());

        // 1. Load report template using try-with-resources for automatic stream closing
        try (InputStream reportStream = getClass().getClassLoader()
                .getResourceAsStream("report/testResultReport.jrxml")) {
            
            if (reportStream == null) {
                String errorMessage = "Report template 'report/testResultReport.jrxml' not found in classpath.";
                logger.error(errorMessage);
                throw new JRException(errorMessage);
            }
            logger.debug("Report template 'report/testResultReport.jrxml' loaded successfully.");

            // 2. Compile report (Compatible with JasperReports 6.17.0)
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            logger.debug("Report compiled successfully.");
            
            // 3. Fill data
            Map<String, Object> params = new HashMap<>();
            params.put("createdBy", "SGU J2EE Application"); // You can make this more dynamic if needed
            params.put("userName", user.getUsername()); // Pass username to the report
            // Add any other parameters your report JRXML might expect
            // e.g., params.put("REPORT_TITLE", "Details for Test Result ID: " + testResultId);

            // The JRBeanCollectionDataSource expects a collection.
            // If your report is designed to show details of a single TestResult,
            // wrapping it in a List is the correct approach.
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(testResult));
            logger.debug("JRBeanCollectionDataSource created with test result data.");
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            logger.debug("Report filled successfully.");
            
            // 4. Export to PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            logger.info("Report exported to PDF successfully for testResultId: {}", testResultId);
            return pdfBytes;
            
        } catch (JRException e) {
            // This catches exceptions from JasperReports operations like compile, fill, export
            logger.error("JRException occurred during report generation for testResultId: {}: {}", testResultId, e.getMessage(), e);
            throw new JRException("Failed to generate report: " + e.getMessage(), e);
        } catch (IOException e) {
            // This catches IOExceptions that might occur from reportStream.close() (implicitly by try-with-resources)
            // or if getResourceAsStream itself had an issue not wrapped by JRException (less common for compile).
            logger.error("IOException occurred, possibly during report template access or closing for testResultId: {}: {}", testResultId, e.getMessage(), e);
            // It's good practice to wrap this in JRException if the method signature expects it,
            // or handle it as a distinct error type if preferred.
            throw new JRException("Failed due to I/O error during report generation: " + e.getMessage(), e);
        }
        // No 'finally' block needed for reportStream.close() due to try-with-resources.
    }

    @Override
    public byte[] exportPrescriptionReport(int prescriptionId) throws JRException {
        logger.info("Starting exportPrescriptionReport for prescriptionId: {}", prescriptionId);

        // Fetch prescription data
        Prescriptions prescription = prescriptionRepository.findById((long) prescriptionId)
                .orElseThrow(() -> {
                    String errorMessage = "Prescription not found with ID: " + prescriptionId;
                    logger.error(errorMessage);
                    return new IllegalArgumentException(errorMessage);
                });
        logger.info("Fetched prescription data for ID: {}", prescriptionId);

        // User (patient) should always exist for a prescription based on your model (nullable = false)
        User patient = prescription.getUser();
        if (patient == null) {
            // This case should ideally not happen if DB constraints and application logic are correct
            String errorMessage = "Patient (User) not found for prescription ID: " + prescriptionId;
            logger.error(errorMessage);
            throw new IllegalStateException(errorMessage); // Or IllegalArgumentException
        }
        logger.info("Fetched patient information for prescriptionId: {}, patient username: {}", prescriptionId, patient.getUsername());

        // Doctor can potentially be null, handle this in the report template ($F{doctor} != null ? ...)
        User doctor = prescription.getDoctor();
        if (doctor != null) {
            logger.info("Fetched doctor information for prescriptionId: {}, doctor username: {}", prescriptionId, doctor.getUsername());
        } else {
            logger.info("No doctor associated with prescriptionId: {}", prescriptionId);
        }
        
        // Ensure medications list is not null, though it can be empty.
        // JRBeanCollectionDataSource handles empty lists gracefully for the table component.
        if (prescription.getMedications() == null) {
            logger.warn("Medications list is null for prescriptionId: {}. Setting to empty list.", prescriptionId);
            prescription.setMedications(java.util.Collections.emptyList());
        }


        // 1. Load report template
        try (InputStream reportStream = getClass().getClassLoader()
                .getResourceAsStream("report/prescriptionReport.jrxml")) {

            if (reportStream == null) {
                String errorMessage = "Report template 'report/prescriptionReport.jrxml' not found in classpath.";
                logger.error(errorMessage);
                throw new JRException(errorMessage);
            }
            logger.debug("Report template 'report/prescriptionReport.jrxml' loaded successfully.");

            // 2. Compile report
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            logger.debug("Prescription report compiled successfully.");

            // 3. Fill data
            Map<String, Object> params = new HashMap<>();
            params.put("createdBy", "SGU J2EE Application");
            // Other global parameters can be added here if needed by the report

            // The JRBeanCollectionDataSource expects a collection.
            // We pass a list containing the single Prescription object.
            // The report will then access fields from this Prescription object,
            // including the list of medications for the table component.
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(prescription));
            logger.debug("JRBeanCollectionDataSource created with prescription data.");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            logger.debug("Prescription report filled successfully.");

            // 4. Export to PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            logger.info("Prescription report exported to PDF successfully for prescriptionId: {}", prescriptionId);
            return pdfBytes;

        } catch (JRException e) {
            logger.error("JRException occurred during prescription report generation for ID: {}: {}", prescriptionId, e.getMessage(), e);
            throw new JRException("Failed to generate prescription report: " + e.getMessage(), e);
        } catch (IOException e) {
            logger.error("IOException occurred during prescription report template access or closing for ID: {}: {}", prescriptionId, e.getMessage(), e);
            throw new JRException("Failed due to I/O error during prescription report generation: " + e.getMessage(), e);
        }
    }
}