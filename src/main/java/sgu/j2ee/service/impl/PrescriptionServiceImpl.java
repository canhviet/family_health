package sgu.j2ee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.MedicationRequest;
import sgu.j2ee.dto.request.PrescriptionRequest;
import sgu.j2ee.dto.response.MedicationResponse;
import sgu.j2ee.dto.response.PresciptionResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.Medications;
import sgu.j2ee.model.Prescriptions;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.MedicationRepository;
import sgu.j2ee.repository.PrescriptionRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.PrescriptionService;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public Long savePrescription(PrescriptionRequest request) {

        Prescriptions pre = Prescriptions.builder()
                            .doctor(getUserById(request.getDoctorUserId()))
                            .user(getUserById(request.getUserId()))
                            .prescriptionDate(request.getPrescriptionDate())
                            .notes(request.getNotes())
                            .build();

        pre.setMedications(convertToMedication(request.getMedications(), pre));  
        
        prescriptionRepository.save(pre);

        return pre.getPrescriptionId();
    }

    private List<Medications> convertToMedication(List<MedicationRequest> requests, Prescriptions pre) {
        return requests.stream().map(
            m -> Medications.builder()
                .dosage(m.getDosage())
                .frequency(m.getFrequency())
                .medicationName(m.getMedicationName())
                .instructions(m.getInstructions())
                .prescription(pre)
                .quantity(m.getQuantity())
                .endDate(m.getEndDate())
                .startDate(m.getStartDate())
                .build()
        ).collect(Collectors.toList());
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
    }

    @Override
    public List<PresciptionResponse> getByUserId(Long userId) {
        List<Prescriptions> list = prescriptionRepository.findWithUserId(userId);

        return list.stream().map(p -> PresciptionResponse.builder()
                                .doctorName(p.getDoctor().getFirstName() + " " + p.getDoctor().getLastName())
                                .medications(convertTMedicationResponses(p.getMedications()))
                                .userId(userId)
                                .prescriptionDate(p.getPrescriptionDate())
                                .prescriptionId(p.getPrescriptionId())
                                .notes(p.getNotes())
                                .build()
        
        ).toList();
    }

    private List<MedicationResponse> convertTMedicationResponses(List<Medications> medications) {
        return medications.stream().map(m -> MedicationResponse.builder()
                                         .dosage(m.getDosage())
                                         .endDate(m.getEndDate())
                                         .startDate(m.getStartDate())
                                         .frequency(m.getFrequency())
                                         .instructions(m.getInstructions())
                                         .medicationName(m.getMedicationName())
                                         .medicationId(m.getMedicationId())
                                         .quantity(m.getQuantity())
                                         .build()   
        ).toList();
    }

    @Override
    public List<MedicationResponse> reminders(Long userId) {
        List<Medications> list = medicationRepository.findMedicationsForTodayByUserId(userId);

        return list.stream().map(
            m -> MedicationResponse.builder()
            .dosage(m.getDosage())
            .endDate(m.getEndDate())
            .startDate(m.getStartDate())
            .frequency(m.getFrequency())
            .instructions(m.getInstructions())
            .medicationId(m.getMedicationId())
            .medicationName(m.getMedicationName())
            .quantity(m.getQuantity())
            .build()
        ).toList();
    }
}
