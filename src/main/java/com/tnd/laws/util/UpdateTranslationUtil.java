package com.tnd.laws.util;

import com.tnd.laws.model.Law;
import com.tnd.laws.repository.LawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateTranslationUtil {

    private final LawRepository lawRepository;
    private final TranslateUtil translateUtil;

    @Async
    public void updateLaw(Law law){
        String title = translateUtil.translate(law.getTitle());
        String description = translateUtil.translate(law.getDescription());

        if (null != title && null != description){
            law.setTranslated(true);
        }
        law.setTitleYoruba(title);
        law.setDescriptionYoruba(description);

        lawRepository.save(law);
    }
}
