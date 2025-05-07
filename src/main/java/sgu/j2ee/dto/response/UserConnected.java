package sgu.j2ee.dto.response;

import java.util.Date;

public interface UserConnected {
    String getFirstName();
    String getLastName();
    String getUsername();
    Date getConnectAt();
    Long getUserId();
}
