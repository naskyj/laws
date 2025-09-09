package com.tnd.laws.controller;

import com.tnd.laws.dto.LawDTO;
import com.tnd.laws.dto.LocalResponse;
import com.tnd.laws.model.Law;
import com.tnd.laws.service.LawService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/laws")
public class LawsController {

    private final LawService lawService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LocalResponse<Law> addLaw(@RequestBody LawDTO lawDTO){
        return lawService.addLaw(lawDTO);
    }

    @GetMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LocalResponse<Law> findLawById(@RequestParam long id){
        return lawService.findById(id);
    }

    @GetMapping(value = "/all",  produces = MediaType.APPLICATION_JSON_VALUE)
    public LocalResponse<Map<String, Object>> allUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        return lawService.allPaged(page, size);
    }

    @GetMapping(value = "/common", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LocalResponse<Map<String, Object>> getCommonLaws(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size) {

        return lawService.getCommonLaws(page, size);
    }

    @GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LocalResponse<Map<String, Object>> getAllLaws(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam String q,
            @RequestParam String lang

    ) {

        return lawService.search(q, page, size, lang);
    }

}
