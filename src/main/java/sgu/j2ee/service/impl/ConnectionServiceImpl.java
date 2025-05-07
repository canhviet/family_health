package sgu.j2ee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.response.UserConnected;
import sgu.j2ee.dto.response.UserResponse;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.ConnectionRepository;
import sgu.j2ee.service.ConnectionService;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;

    @Override
    public List<UserConnected> getDoctors(Long userId) {
        return connectionRepository.findDoctorsByUserId(userId);
    }

    @Override
    public List<UserConnected> getPatients(Long doctorId) {
        return connectionRepository.findPatientsByDoctorId(doctorId);
    }

    @Override
    public List<UserResponse> searchUserNotConnect(Long userId, String search) {
        List<User> users = connectionRepository.findUsersNotConnectedBySearch(userId, search);

        return users.stream().map(user -> UserResponse.builder()
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .userId(user.getUserId())
                                    .username(user.getUsername())
                                    .build()      
        ).toList();
    }

}
