package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.ProfileWordlist;

import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.ProfileWordlistDao;

@Repository("profileWordlistDao")
public class ProfileWordlistDaoImpl extends HibernateDao <ProfileWordlist, Integer> implements ProfileWordlistDao{

}
