package smartphone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchResourceFoundException extends RuntimeException {

 
	@Serial
    private static final long serialVersionUID = -8749643454264131447L;

    public NoSuchResourceFoundException(String msg) {
        super(msg);
    }
	public NoSuchResourceFoundException(String msg, Exception e) {
        super(msg);
    }
}
