package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.TestWordlist;

import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.TestWordlistDao;

@Repository("testWordlistDao")
public class TestWordlistDaoImpl extends HibernateDao <TestWordlist, Integer> implements TestWordlistDao{

}
