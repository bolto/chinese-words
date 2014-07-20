package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.AssignmentMistake;

import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.AssignmentMistakeDao;

@Repository("assignmentMistakeDao")
public class AssignmentMistakeDaoImpl extends HibernateDao <AssignmentMistake, Integer> implements AssignmentMistakeDao{

}
