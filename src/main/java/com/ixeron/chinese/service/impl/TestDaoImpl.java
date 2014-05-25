package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.Test;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.TestDao;

@Repository("testDao")
public class TestDaoImpl extends HibernateDao <Test, Integer> implements TestDao{

}
