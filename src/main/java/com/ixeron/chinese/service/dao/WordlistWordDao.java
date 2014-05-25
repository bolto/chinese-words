package com.ixeron.chinese.service.dao;

import java.util.List;

import com.ixeron.chinese.domain.WordlistWord;

public interface WordlistWordDao extends GenericDao<WordlistWord, Integer> {
    List<WordlistWord> listByWordlistId(Integer wordId);
}