package com.ixeron.chinese.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.Assignment;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.AssignmentDao;

@Repository("assignmentDao")
public class AssignmentDaoImpl extends HibernateDao <Assignment, Integer> implements AssignmentDao{

    @Override
    public List<Assignment> listByTestId(Integer id) {
        String queryStr = "SELECT * FROM assignment a inner join test t on a.test_id = t.id WHERE t.id = {test_id};";
        queryStr = queryStr.replace("{test_id}", id.toString());
        
        @SuppressWarnings("unchecked")
        List<Assignment> list = currentSession().createSQLQuery(queryStr).addEntity(Assignment.class).list();
        
        return list;
    }
}
