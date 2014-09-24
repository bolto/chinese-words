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
    
    private Integer getWordlistWordCount(Integer wordlistId){
        String queryStr = "select count(*) from wordlist_word where wordlist_id = {wordlist_id};";
        queryStr = queryStr.replace("{wordlist_id}", wordlistId.toString());
        @SuppressWarnings("unchecked")
		List<Integer> results = currentSession()
                .createSQLQuery(queryStr)
                .addEntity(Integer.class)
                .list();
        Integer count = 0;
        if(results != null){
        	count = results.get(0);
        }
        return count;
    }

    /* 
     * If list_order is not specified, it is default to auto-increment with zero index numbers.
     * @see com.ixeron.chinese.service.dao.HibernateDao#add(java.lang.Object)
     */
    public void add(WordlistWord wordlistWord){
    	if(wordlistWord.getListOrder() == null)
    		wordlistWord.setListOrder(getWordlistWordCount(wordlistWord.getWordlistId()));
    	super.add(wordlistWord);
    }
}
