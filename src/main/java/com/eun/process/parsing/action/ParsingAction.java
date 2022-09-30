package com.eun.process.parsing.action;

import com.eun.property.Endpoint;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@Controller
public class ParsingAction {


    @RequestMapping(Endpoint.KAKAO_PARSING)
    @ResponseBody public String kakaoMap() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://map.kakao.com/").get();
        } catch (IOException e) {
            log.error("web page connect error");
        }

        log.info(doc.title());
        log.info(String.valueOf(doc.getAllElements()));
        Elements newsHeadlines = doc.select(".div");
        for (Element headline : newsHeadlines) {
            log.info(String.valueOf(headline));
        }

        return "";
    }


}
