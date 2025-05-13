package sgu.j2ee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.ConnectionRequest;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.service.ConnectionService;

@RestController
@RequestMapping("/api/connection")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    @GetMapping("/doctors/{userId}")
    public ResponseData<?> getDoctorsConnected(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(), "view doctors", this.connectionService.getDoctors(userId));
    }

    @GetMapping("/{userId}/{search}")
    public ResponseData<?> searchDoctorsNotConnect(@PathVariable("userId") Long userId, @PathVariable(value = "search", required = false) String search) {
        return new ResponseData<>(HttpStatus.OK.value(), "view user not connected", this.connectionService.searchDoctorsNotConnect(userId, search));
    }

    @PostMapping("/")
    public ResponseData<?> addConnection(@RequestBody ConnectionRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "view user not connected", this.connectionService.addNewConnection(request));

    }

    @GetMapping("/patients/{doctorId}/{search}")
    public ResponseData<?> searchPatientsConnected(@PathVariable("doctorId") Long doctorId, @PathVariable(value = "search", required = false) String search) {
        return new ResponseData<>(HttpStatus.OK.value(), "view doctors", this.connectionService.searchPatients(doctorId, search));
    }

}
