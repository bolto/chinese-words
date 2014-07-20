package com.ixeron.chinese.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.WordlistWord;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.WordlistWordDao;

@Repository("wordlistWordDao")
public class WordlistWordDaoImpl extends HibernateDao <WordlistWord, Integer> implements WordlistWordDao{

    @SuppressWarnings("unchecked")
    @Override
    public List<WordlistWord> listByWordlistId(Integer wordlistId) {
        String queryStr = "select * from wordlist_word where wordlist_id = {wordlist_id};";
        queryStr = queryStr.replace("{wordlist_id}", wordlistId.toString());
        return currentSession()
                .createSQLQuery(queryStr)
                .addEntity(WordlistWord.class)
                .list();
    }
}
