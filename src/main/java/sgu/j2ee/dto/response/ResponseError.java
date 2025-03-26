package sgu.j2ee.dto.response;

@SuppressWarnings("rawtypes")
public class ResponseError extends ResponseData {
    public ResponseError(int status, String messgae) {
        super(status, messgae);
    }
}
