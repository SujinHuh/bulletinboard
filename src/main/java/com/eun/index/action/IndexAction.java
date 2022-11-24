package com.eun.index.action;

import com.eun.common.property.Endpoint;
import com.eun.common.property.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class IndexAction {

    /**
     * Index page 이동
     *
     * @return Index page
     */
    @GetMapping(Endpoint.ROOT)
    public String indexPage() {
        return Template.INDEX;
    }

    @GetMapping(Endpoint.ROOT + "/sample")
    public String indexSamplePage() {
        return Template.INDEX + "_sample";
    }
    /**
     * 서버 내에서 오류가 발생 했을때 이동 되는 페이지
     *
     * @return 오류 결과 페이지
     */
    @GetMapping(Endpoint.ERROR)
    public String errorPage() {
        return Template.ERROR;
    }

    /**
     * 서버가 정상적으로 동작하는지 체크하기 위한 Endpoint
     *
     * @return 200 status ok
     */

    @GetMapping(Endpoint.HEALTH)
    @ResponseBody public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("result", "ok");
        return ResponseEntity.ok().body(hashMap);
    }
}