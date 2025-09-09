package com.tnd.laws.service;

import com.tnd.laws.dto.LawDTO;
import com.tnd.laws.dto.LocalResponse;
import com.tnd.laws.model.Law;
import com.tnd.laws.repository.LawRepository;
import com.tnd.laws.util.UpdateTranslationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LawService {

    private final LawRepository lawRepository;
    private final UpdateTranslationUtil updateTranslationUtil;

    public LocalResponse<Law> addLaw(LawDTO lawDTO){
        try{
            Law law = lawRepository.save(new Law(lawDTO));
            updateTranslationUtil.updateLaw(law);
            return new LocalResponse<>("00", "Success", law);
        }catch (Exception ex){
            log.info("Reg Law Exception: {}", ex.getMessage());
            return new LocalResponse<>("99", "Operation Failed");
        }
    }

    public LocalResponse<Law> findById(long id){
        try{
            Optional<Law> law = lawRepository.findById(id);

            return law.map(
                    value -> new LocalResponse<>("00", "Success", value))
                    .orElseGet(() -> new LocalResponse<>("99", "Not Found")
                    );

        }catch (Exception ex){
            log.info("Law Find Exception: {}", ex.getMessage());
            return new LocalResponse<>("99", "Operation Failed");
        }
    }

    public LocalResponse<Map<String, Object>> allPaged(int page, int size){

        Pageable paging = PageRequest.of(page, size, Sort.by("lawId").ascending());

        //Page<Law> laws = lawRepository.findAll(paging);
        Page<Law> laws = lawRepository.findAllLawsWithChaptersAndSections(paging);


        Map<String, Object> response = new HashMap<>();
        response.put("details", laws.getContent());
        response.put("currentPage", laws.getNumber());
        response.put("totalRecords", laws.getTotalElements());
        response.put("totalPages", laws.getTotalPages());

        return new LocalResponse<>("00", "Success", response);
    }


    public LocalResponse<Map<String, Object>> search(String searchQuery, int page, int size, String lang){

        Pageable paging = PageRequest.of(page, size, Sort.by("lawId").ascending());


        Page<Law> laws = null;

        switch (lang.toUpperCase()){
            case "YR":
                laws = lawRepository.findByTitleYorubaContainingIgnoreCaseOrDescriptionYorubaContainingIgnoreCase(searchQuery,searchQuery, paging);
                break;

            case "EN":
            default:
                laws = lawRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchQuery,searchQuery, paging);
                break;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("details", laws.getContent());
        response.put("currentPage", laws.getNumber());
        response.put("totalRecords", laws.getTotalElements());
        response.put("totalPages", laws.getTotalPages());

        return new LocalResponse<>("00", "Success", response);
    }

    public LocalResponse<Map<String, Object>> getCommonLaws(int page, int size){

        Pageable paging = PageRequest.of(page, size, Sort.by("lawId").ascending());


        Page<Law> laws = lawRepository.findByCommon(true, paging);

        Map<String, Object> response = new HashMap<>();
        response.put("details", laws.getContent());
        response.put("currentPage", laws.getNumber());
        response.put("totalRecords", laws.getTotalElements());
        response.put("totalPages", laws.getTotalPages());

        return new LocalResponse<>("00", "Success", response);
    }
}
