package com.example.coursework.lab3.service;

import com.example.coursework.lab3.exception.FileReadException;
import com.example.coursework.lab3.exception.InvalidFileFormatException;

public interface DictionaryService {
    void loadDictionary(String filePath) throws FileReadException, InvalidFileFormatException;
    String translate(String text);
    boolean containsWord(String word);
}