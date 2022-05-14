package daiku.domain.infra.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import daiku.domain.infra.utils.CodeEnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.Domain;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@Domain(valueType = String.class, factoryMethod = "of")
public enum ProcessPriority implements CodeEnum<ProcessPriority> {
    LOW("0", "低"),
    NORMAL("1", "中"),
    HEIGHT("2", "高"),
    TOP("3", "優先的");

    @Getter
    private final String value;
    @Getter
    private final String name;

    private static final Map<String, ProcessPriority> valueMap;

    static {
        valueMap = CodeEnumUtil.initEnumMap(ProcessPriority.class);
    }

    public static ProcessPriority of(String value) { return valueMap.get(value); }

    @JsonCreator
    public static ProcessPriority fromString(String value) {
        log.info("=========");
        log.info(value);
        log.info("=========");
        return valueMap.get(value);
    }
}
