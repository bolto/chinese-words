package com.ixeron.chinese.service.dao;

import java.util.List;

import com.ixeron.chinese.domain.Pingying;

public interface PingyingDao  extends GenericDao<Pingying, Integer> {

    public Pingying findByPingying(Pingying py);

    public List<Pingying> listByWordId(Integer wordId);
}
