package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.response.UserConnected;
import sgu.j2ee.dto.response.UserResponse;

public interface ConnectionService {
    List<UserConnected> getDoctors(Long userId);
    List<UserConnected> getPatients(Long doctorId);
    List<UserResponse> searchUserNotConnect(Long userId, String search);
}
