/**
 * 
 */
package com.ixeron.chinese.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.Pingying;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.PingyingDao;

@Repository("pingyingDao")
public class PingyingDaoImpl extends HibernateDao <Pingying, Integer> implements PingyingDao {

    @Override
    public Pingying findByPingying(Pingying py) {
        String queryStr = "SELECT * FROM pingying WHERE first_py_id = {first_py_id} and second_py_id = {second_py_id} and third_py_id = {third_py_id} and tone_id = {tone_id};";
        queryStr = queryStr.replace("{first_py_id}", py.getFirstPyId().toString());
        queryStr = queryStr.replace("{second_py_id}", py.getSecondPyId().toString());
        queryStr = queryStr.replace("{third_py_id}", py.getThirdPyId().toString());
        queryStr = queryStr.replace("{tone_id}", py.getToneId().toString());
        
        @SuppressWarnings("unchecked")
        List<Pingying> list = currentSession().createSQLQuery(queryStr).addEntity(Pingying.class).list();
        
        if(list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public List<Pingying> listByWordId(Integer wordId) {
        
        String queryStr = "SELECT * FROM pingying py inner join word_pingying wpy on py.id = pwy.py.id WHERE wpy.word_id = {word_id}";
        queryStr = queryStr.replace("{word_id}", wordId.toString());
        
        @SuppressWarnings("unchecked")
        List<Pingying> list = currentSession().createSQLQuery(queryStr).addEntity(Pingying.class).list();
        
        return list;
    }
}
