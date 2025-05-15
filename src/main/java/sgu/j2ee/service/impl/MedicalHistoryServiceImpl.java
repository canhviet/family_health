package sgu.j2ee.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.HistoryRequest;
import sgu.j2ee.dto.response.HistoryResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.MedicalHistory;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.MedicalHistoryRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.MedicalHistoryService;

@Service
@RequiredArgsConstructor
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final UserRepository userRepository;

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
    }

    @Override
    public List<HistoryResponse> listByUser(Long userId) {
        List<MedicalHistory> list = medicalHistoryRepository.findByUser(getUserById(userId));

        return list.stream().map(
            m -> HistoryResponse.builder()
            .condition(m.getCondition())
            .doctorName(m.getDoctor().getFirstName() + " " + m.getDoctor().getLastName())
            .diagnosisDate(m.getDiagnosisDate())
            .revisitDate(m.getRevisitDate())
            .treatingDoctor(m.getTreatingDoctor())
            .userId(userId)
            .notes(m.getNotes())
            .historyId(m.getHistoryId())
            .build()
        ).toList();
    }

    @Override
    public Long add(HistoryRequest request) {
        MedicalHistory m = MedicalHistory.builder()
                            .condition(request.getCondition())
                            .diagnosisDate(request.getDiagnosisDate())
                            .revisitDate(request.getRevisitDate())
                            .doctor(getUserById(request.getDoctorUserId()))
                            .user(getUserById(request.getUserId()))
                            .notes(request.getNotes())
                            .treatingDoctor(request.getTreatingDoctor())
                            .build();
        medicalHistoryRepository.save(m);

        return m.getHistoryId();
    }

    private Optional<MedicalHistory> getNearestRevisitDate(Long userId) {
        if (userId == null || userId <= 0) {
            return Optional.empty(); 
        }
        return medicalHistoryRepository.findNearestRevisitDateByUserId(userId);
    }

    @Override
    public HistoryResponse nearestRevisitDate(Long userId) {
        Optional<MedicalHistory> optionalHistory = getNearestRevisitDate(userId);
        
        if (optionalHistory.isEmpty()) {
            return HistoryResponse.builder()
                    .userId(userId)
                    .notes("No medical history found for user with ID: " + userId)
                    .build();
        }

        MedicalHistory m = optionalHistory.get();
        return HistoryResponse.builder()
                .condition(m.getCondition())
                .revisitDate(m.getRevisitDate())
                .doctorName(m.getDoctor().getFirstName() + " " + m.getDoctor().getLastName())
                .notes(m.getNotes())
                .historyId(m.getHistoryId())
                .diagnosisDate(m.getDiagnosisDate())
                .userId(userId)
                .treatingDoctor(m.getTreatingDoctor())
                .build();
    }

}
