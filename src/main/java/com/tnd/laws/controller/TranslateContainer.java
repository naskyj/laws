package com.tnd.laws.controller;

import com.tnd.laws.dto.LocalResponse;
import com.tnd.laws.util.TranslateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/translate")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TranslateContainer {

    private final TranslateUtil translateUtil;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public LocalResponse<String> translate(@RequestParam(value = "q", required = true) String text){
        String trans = translateUtil.translate(text);
        return null == trans ?
                new LocalResponse<>("99", "Translation unavailable") :
                new LocalResponse<>("00", "Success", trans);
    }
}
