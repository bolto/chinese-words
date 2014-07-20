package com.ixeron.chinese.service.dao;

import java.util.List;
import java.util.Map;

import com.ixeron.chinese.domain.Word;

public interface WordDao extends GenericDao<Word, Integer> {

    public Word findBySymbol(String symbol);
    
    public List<Word> list(Integer max);
    
    public List<Word> list(String wordList);

    public Map<String, Word> listToMap(String wordList);
}