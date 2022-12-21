package ajc.sopra.eshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "id inconnu")
public class IdException extends RuntimeException{
	public IdException() {
		super("id inconnu");
	}
}
