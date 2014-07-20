package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.TestWordlistWord;

import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.TestWordlistWordDao;

@Repository("testWordlistWordDao")
public class TestWordlistWordDaoImpl extends HibernateDao <TestWordlistWord, Integer> implements TestWordlistWordDao{

}
