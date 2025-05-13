package sgu.j2ee.controller;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import sgu.j2ee.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/test-result")
    public ResponseEntity<byte[]> exportPdf(@RequestParam("testResultId") int testResultId) throws JRException {
        byte[] pdfData = reportService.exportTestResultReport(testResultId); // Pass testResultId to the service

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
            ContentDisposition.builder("attachment") // or "attachment" to download
                .filename("bao_cao.pdf")
                .build()
        );

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @GetMapping("/prescription")
    public ResponseEntity<byte[]> exportPrescriptionPdf(@RequestParam("prescriptionId") int prescriptionId) throws JRException {
        byte[] pdfData = reportService.exportPrescriptionReport(prescriptionId); // Pass testResultId to the service

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
            ContentDisposition.builder("attachment") // or "attachment" to download
                .filename("don_thuoc.pdf")
                .build()
        );

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }
}