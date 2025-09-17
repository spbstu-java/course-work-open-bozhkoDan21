package com.example.coursework.lab3.service;

import com.example.coursework.lab3.exception.FileReadException;
import com.example.coursework.lab3.exception.InvalidFileFormatException;

public class TranslatorFactory {
    public static DictionaryService createEnglishRussianTranslator(String dictionaryPath)
            throws FileReadException, InvalidFileFormatException {
        EnglishRussianTranslator translator = new EnglishRussianTranslator();
        translator.loadDictionary(dictionaryPath);
        return translator;
    }
}