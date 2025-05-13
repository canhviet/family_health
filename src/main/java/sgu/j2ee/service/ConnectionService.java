package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.request.ConnectionRequest;
import sgu.j2ee.dto.response.UserConnected;
import sgu.j2ee.dto.response.UserResponse;

public interface ConnectionService {
    List<UserConnected> getDoctors(Long userId);
    List<UserResponse> searchPatients(Long doctorId, String search);
    List<UserResponse> searchDoctorsNotConnect(Long userId, String search);
    Long addNewConnection(ConnectionRequest request);
}
