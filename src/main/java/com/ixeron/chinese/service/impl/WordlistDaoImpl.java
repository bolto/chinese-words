package com.ixeron.chinese.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.Wordlist;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.WordlistDao;

@Repository("wordlistDao")
public class WordlistDaoImpl extends HibernateDao <Wordlist, Integer> implements WordlistDao{

    @Override
    public List<Wordlist> listByProfileId(Integer profileId) {
        String queryStr = "SELECT * FROM wordlist w inner join profile_wordlist pw on w.id = pw.wordlist_id WHERE pw.profile_id = {profile_id};";
        queryStr = queryStr.replace("{profile_id}", profileId.toString());
        
        @SuppressWarnings("unchecked")
        List<Wordlist> list = currentSession().createSQLQuery(queryStr).addEntity(Wordlist.class).list();
        
        return list;
    }

    @Override
    public List<Wordlist> listByTestId(Integer id) {
        String queryStr = "SELECT * FROM wordlist w inner join test_wordlist tw on w.id = tw.wordlist_id WHERE tw.test_id = {test_id};";
        queryStr = queryStr.replace("{test_id}", id.toString());
        
        @SuppressWarnings("unchecked")
        List<Wordlist> list = currentSession().createSQLQuery(queryStr).addEntity(Wordlist.class).list();
        
        return list;
    }
}
