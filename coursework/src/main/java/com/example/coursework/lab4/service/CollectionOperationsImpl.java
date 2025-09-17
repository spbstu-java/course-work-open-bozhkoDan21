package com.example.coursework.lab4.service;

import com.example.coursework.lab4.exception.EmptyCollectionException;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CollectionOperationsImpl implements CollectionOperations {

    @Override
    public <T> T getLastElement(Collection<T> collection) {
        try {
            return collection.stream()
                    .reduce((first, second) -> second)
                    .orElseThrow(() -> new NoSuchElementException("Collection is empty"));
        } catch (NoSuchElementException e) {
            throw new EmptyCollectionException("Collection is empty", e);
        }
    }
}