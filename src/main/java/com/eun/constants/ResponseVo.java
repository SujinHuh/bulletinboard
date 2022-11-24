package com.eun.constants;

import lombok.*;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**  HTTP상태 코드 */
public class ResponseVo {

    private String code = ResponseCodes.ERROR.getCode();
    private String message = ResponseCodes.ERROR.getMessage();
    private Object data;

    public static ResponseVo successData(Object data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseCodes.SUCCESS.getCode());
        responseVo.setMessage(ResponseCodes.SUCCESS.getMessage());
        responseVo.setData(data);
        return responseVo;
    }

    /**
     * validation Error 가 존재할 경우 오류메세지와 함께 리턴
     */
    public static ResponseVo hasErrors(Errors errors) {
        List<String> errorMessages = errors.getFieldErrors().stream().map( t -> "[" + t.getField() + ":" + t.getDefaultMessage() + "]").collect(Collectors.toList());
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseCodes.ERROR.getCode());
        responseVo.setMessage(StringUtils.join(errorMessages, ','));
        return responseVo;
    }
}
