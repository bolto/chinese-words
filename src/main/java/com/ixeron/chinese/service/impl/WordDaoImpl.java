package com.ixeron.chinese.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.ixeron.chinese.domain.Word;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.WordDao;

@Repository("wordDao")
public class WordDaoImpl extends HibernateDao <Word, Integer> implements WordDao{

    @Override
    public Word findBySymbol(String symbol) {
        String queryStr = "SELECT * FROM word WHERE symbol = '{symbol}';";
        queryStr = queryStr.replace("{symbol}", symbol);
        
        @SuppressWarnings("unchecked")
        List<Word> list = currentSession().createSQLQuery(queryStr).addEntity(Word.class).list();
        
        if(list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public List<Word> findBySymbols(String symbols) {
        if(symbols == null){
            return null;
        }
        if(symbols.trim().equals("")){
            return null;
        }
        String inCondition = "";
        for(int i = 0; i<symbols.length(); i++){
            if(!inCondition.equals(""))
                inCondition += ",";
            inCondition += String.format("'%s'", symbols.substring(i, i+1));
        }
        String queryStr = "SELECT * FROM word WHERE symbol in ({symbols});";
        queryStr = queryStr.replace("{symbols}", inCondition);
        
        @SuppressWarnings("unchecked")
        List<Word> list = currentSession().createSQLQuery(queryStr).addEntity(Word.class).list();
        
        if(list == null || list.size() == 0)
            return null;
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Word> list(Integer max) {
        String queryStr = "select * from word w {limit};";
        String limitStr = "";
        if(max != null && max > 0){
            limitStr = "limit " + max;
        }
        queryStr = queryStr.replace("{limit}", limitStr);
        return currentSession()
                .createSQLQuery(queryStr)
                .addEntity(Word.class)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Word> list(String wordList) {
        if(wordList == null || wordList.length() == 0){
            return null;
        }
        String inConditionStr = "";
        for(int i = 0; i < wordList.length(); i++){
            if(inConditionStr.length()>0)
                inConditionStr += ", ";
            inConditionStr += "'" + wordList.substring(i, i+1) + "'";
        }
        String queryStr = "select * from word w where symbol in ({in}) order by FIELD({order});";

        queryStr = queryStr.replace("{in}", inConditionStr);
        queryStr = queryStr.replace("{order}", "symbol," + inConditionStr);
        return currentSession()
                .createSQLQuery(queryStr)
                .addEntity(Word.class)
                .list();
    }

    @Override
    public Map<String, Word> listToMap(String wordList) {
        List<Word> list = list(wordList);
        Map<String, Word> hash = new HashMap<String, Word>();
        if(list != null && list.size() > 0){
            for(Word word : list){
                if(!hash.containsKey(word.getSymbol())){
                    hash.put(word.getSymbol(), word);
                }
            }
        }
        return hash;
    }
}
