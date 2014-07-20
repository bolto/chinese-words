package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.Profile;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.ProfileDao;

@Repository("profileDao")
public class ProfileDaoImpl extends HibernateDao <Profile, Integer> implements ProfileDao{

}
