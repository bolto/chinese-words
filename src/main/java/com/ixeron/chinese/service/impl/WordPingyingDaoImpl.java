package com.ixeron.chinese.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.Word;
import com.ixeron.chinese.domain.WordPingying;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.WordPingyingDao;

@Repository("wordPingyingDao")
public class WordPingyingDaoImpl extends HibernateDao <WordPingying, Integer> implements WordPingyingDao{

    @Override
    public WordPingying findByWordPingying(WordPingying wpy) {
        
        String queryStr = "SELECT * FROM word_pingying wpy WHERE wpy.word_id = {word_id} and wpy.pingying_id = {pingying_id};";
        queryStr = queryStr.replace("{word_id}", wpy.getPk().getWord().getId().toString());
        queryStr = queryStr.replace("{pingying_id}", wpy.getPk().getPingying().getId().toString());
        
        @SuppressWarnings("unchecked")
        List<WordPingying> list = currentSession().createSQLQuery(queryStr).addEntity(WordPingying.class).list();
        
        if(list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public WordPingying findFirstPingying(Word word) {
        if(word == null || word.getId() == null){
            return null;
        }
        String queryStr = "" +
        		"select * from word_pingying WHERE word_id = {word_id} and list_order = " +
        		"(" +
        		"SELECT min(wpy.list_order) FROM word_pingying wpy WHERE wpy.word_id = {word_id} group by word_id" +
        		") limit 1;";
        queryStr = queryStr.replace("{word_id}", word.getId().toString());
        
        @SuppressWarnings("unchecked")
        List<WordPingying> list = currentSession().createSQLQuery(queryStr).addEntity(WordPingying.class).list();
        
        if(list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

	@Override
	public List<WordPingying> listByWordId(Integer id) {
        String queryStr = "SELECT * FROM word_pingying wpy WHERE wpy.word_id = {word_id};";
        queryStr = queryStr.replace("{word_id}", id.toString());
        
        @SuppressWarnings("unchecked")
        List<WordPingying> list = currentSession().createSQLQuery(queryStr).addEntity(WordPingying.class).list();

        return list;
	}
}
