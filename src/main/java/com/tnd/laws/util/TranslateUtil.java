package com.tnd.laws.util;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TranslateUtil {

    @Value("${translate.key}") private String key;
    @Value("${transate.url}") private String translateUrl;

    public String translate(String text){

        try {
            HttpResponse<JsonNode> response = Unirest
                    .post(translateUrl)
                    .header("Ocp-Apim-Subscription-Key", key)
                    .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .body(List.of(Map.of("Text", text)))
                    .asJson();

            if (!response.isSuccess())
                return null;

            return response.getBody().getArray()
                    .getJSONObject(0).optJSONArray("translations")
                    .getJSONObject(0).optString("text");

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
