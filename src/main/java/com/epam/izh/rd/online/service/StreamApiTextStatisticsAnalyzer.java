package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().map(String::length).reduce(Integer::sum).get();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getUniqueWords(text).stream().count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("[\\pP\\s]"))
                .filter(a -> (!(a.equals(""))))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return Arrays.stream(text.split("[\\pP\\s]"))
                .filter(a -> (!(a.equals(""))))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text)
                .stream()
                .collect(Collectors.toMap(element -> element, element -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        HashMap<Direction, List<String>> hashMapWordInText = new HashMap<>();

        List<String> ascendingWordsList = getWords(text).stream()
                .sorted((stringOne, stringTwo) -> stringOne.length() - stringTwo.length()).collect(Collectors.toList());
        hashMapWordInText.put(Direction.ASC, ascendingWordsList);

        List<String> descendingWordsList = getWords(text).stream()
                .sorted((stringOne, stringTwo) -> stringTwo.length() - stringOne.length()).collect(Collectors.toList());
        hashMapWordInText.put(Direction.DESC, descendingWordsList);

        return hashMapWordInText.get(direction);
    }
}
