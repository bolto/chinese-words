package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;
import com.ixeron.chinese.domain.Tone;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.ToneDao;

@Repository("toneDao")
public class ToneDaoImpl extends HibernateDao <Tone, Integer> implements ToneDao{
}
