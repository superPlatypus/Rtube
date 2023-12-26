package com.platypus.Rtube.service;

import com.ibm.icu.text.Transliterator;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileService {
    public String cyryllicToLatin(String str){
        String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";

        String regex = "[а-яёА-ЯЁ]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(str);
        if (m.find()){
            Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
            str = toLatinTrans.transliterate(str);
        }
        return str;
    }
}
