package sgu.j2ee.service;

import java.util.List;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
    byte[] exportTestResultReport(int testResultId) throws JRException;
    byte[] exportPrescriptionReport(int prescriptionId) throws JRException;
}
