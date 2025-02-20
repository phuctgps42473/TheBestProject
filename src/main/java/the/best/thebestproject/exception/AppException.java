package the.best.thebestproject.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends RuntimeException {

    private ErrorMessage errorMessage;

    public AppException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
