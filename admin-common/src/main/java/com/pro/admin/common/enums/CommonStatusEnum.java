package com.pro.admin.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pro.admin.common.interfaces.CommonEnumConverter;
import com.pro.admin.common.interfaces.IntArrayValuable;
import com.pro.admin.common.interfaces.IntValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author lijunping on 2021/6/22
 */
@AllArgsConstructor
@Getter
@JsonDeserialize(using = CommonStatusEnum.EnumDeserializer.class)
public enum CommonStatusEnum implements IntArrayValuable, IntValuable {

    YES(1, "启用"),

    NO(0, "冻结"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CommonStatusEnum::getValue).toArray();

    @EnumValue
    @JsonValue
    private final Integer value;

    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

    public static CommonStatusEnum of(Integer value) {
        for (CommonStatusEnum statusEnum : values()) {
            if (Objects.equals(statusEnum.getValue(), value)) {
                return statusEnum;
            }
        }
        return null;
    }

    public static class Converter implements CommonEnumConverter<CommonStatusEnum> {
        public CommonStatusEnum intToEnum(Integer value) {
            return CommonStatusEnum.of(value);
        }
    }

    public static class EnumDeserializer extends JsonDeserializer<CommonStatusEnum> {

        @Override
        public CommonStatusEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            int valueAsInt = jsonParser.getValueAsInt();
            return CommonStatusEnum.of(valueAsInt);
        }
    }
}
