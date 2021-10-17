package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;

public class SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    @Override
    public int countSumLengthOfWords(String text) {
        int sumLengthOfWords = 0;
        for (String word : getWords(text)) {
            sumLengthOfWords += word.length();
        }
        return sumLengthOfWords;
    }

    @Override
    public int countNumberOfWords(String text) {
        int numberOfWords = 0;
        for (String word : getWords(text)) {
            numberOfWords++;
        }
        return numberOfWords;
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        int numberOfUniqueWords = 0;
        for (String word : getUniqueWords(text)) {
            numberOfUniqueWords++;
        }
        return numberOfUniqueWords;
    }

    @Override
    public List<String> getWords(String text) {
        List<String> listWordsInText = new ArrayList<>();
        String[] arrayWordsInText = text.split("[\\pP\\s]");
        for (String word : arrayWordsInText) {
            if (!(word.equals(""))) {
                listWordsInText.add(word);
            }
        }
        return listWordsInText;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {

        Map<String, Integer> mapWordsInText = new HashMap<>();

        for (String stringOne : getUniqueWords(text)) {
            int NumberOfWordsRepetitions = 0;
            for (String stringTwo : getWords(text)) {
                if (stringOne.equals(stringTwo)) {
                    NumberOfWordsRepetitions++;
                }
            }
            mapWordsInText.put(stringOne, NumberOfWordsRepetitions);
        }
        return mapWordsInText;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        HashMap<Direction, List<String>> hashMapWordInText = new HashMap<>();

        List<String> ascendingWordsList = getWords(text);
        ascendingWordsList.sort(new Comparator<String>() {
            @Override
            public int compare(String stringOne, String stringTwo) {
                return stringOne.length() - stringTwo.length();
            }
        });
        hashMapWordInText.put(Direction.ASC, ascendingWordsList);

        List<String> descendingWordsList = getWords(text);
        descendingWordsList.sort(new Comparator<String>() {
            @Override
            public int compare(String stringOne, String stringTwo) {
                return stringTwo.length() - stringOne.length();
            }
        });
        hashMapWordInText.put(Direction.DESC, descendingWordsList);

        return hashMapWordInText.get(direction);
    }
}
