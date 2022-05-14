package daiku.domain.infra.entity;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.ResponseEntity;

@Value
@Builder
public class ErrorResponse {
    Integer code;
    String message;
    String errorCd;
}
