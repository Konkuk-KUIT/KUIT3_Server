package kuit.server.common.exception.jwt.unauthorized;

import kuit.server.common.exception.jwt.unauthorized.JwtUnauthorizedTokenException;
import kuit.server.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtMalformedTokenException extends JwtUnauthorizedTokenException {

    private final ResponseStatus exceptionStatus;

    public JwtMalformedTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
        this.exceptionStatus = exceptionStatus;
    }

}
