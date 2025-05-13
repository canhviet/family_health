package sgu.j2ee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.ConnectionRequest;
import sgu.j2ee.dto.response.UserConnected;
import sgu.j2ee.dto.response.UserResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.MedicalConnections;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.ConnectionRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.ConnectionService;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserConnected> getDoctors(Long userId) {
        return connectionRepository.findDoctorsByUserId(userId);
    }

    @Override
    public List<UserResponse> searchPatients(Long doctorId, String search) {
        List<User> users = connectionRepository.findPatientsByDoctorIdAndSearch(doctorId, search);

        return users.stream().map(user -> UserResponse.builder()
                                .address(user.getAddress())
                                .cityzenId(user.getCitizenId())
                                .dob(user.getDob())
                                .email(user.getEmail())
                                .familyId(user.getFamily() != null ? user.getFamily().getFamilyId() : -1L)
                                .firstName(user.getFirstName())
                                .healthInsuranceCode(user.getHealthInsuranceCode())
                                .lastName(user.getLastName())
                                .phone(user.getPhone())
                                .username(user.getUsername())
                                .gender(user.getGender())
                                .userId(user.getUserId())
                                .build()    
        ).toList();
    }

    @Override
    public List<UserResponse> searchDoctorsNotConnect(Long userId, String search) {
        List<User> users = connectionRepository.findDoctorsNotConnectedBySearch(userId, search);

        return users.stream().map(user -> UserResponse.builder()
                                .address(user.getAddress())
                                .cityzenId(user.getCitizenId())
                                .dob(user.getDob())
                                .email(user.getEmail())
                                .familyId(user.getFamily() != null ? user.getFamily().getFamilyId() : -1L)
                                .firstName(user.getFirstName())
                                .healthInsuranceCode(user.getHealthInsuranceCode())
                                .lastName(user.getLastName())
                                .phone(user.getPhone())
                                .username(user.getUsername())
                                .gender(user.getGender())
                                .userId(user.getUserId())
                                .build()    
        ).toList();
    }

    @Override
    public Long addNewConnection(ConnectionRequest request) {
        MedicalConnections connection = MedicalConnections.builder()
                                        .doctor(getUserById(request.getDoctorId()))
                                        .user(getUserById(request.getUserId()))
                                        .build();

        connectionRepository.save(connection);

        return connection.getConnectId();
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
    }

}
