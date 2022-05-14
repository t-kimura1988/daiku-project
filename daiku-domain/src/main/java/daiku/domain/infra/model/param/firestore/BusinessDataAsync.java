package daiku.domain.infra.model.param.firestore;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BusinessDataAsync {
    Long accountCount;
    Long goalCount;
}
