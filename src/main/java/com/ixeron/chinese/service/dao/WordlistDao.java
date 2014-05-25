package com.ixeron.chinese.service.dao;

import java.util.List;

import com.ixeron.chinese.domain.Wordlist;

public interface WordlistDao extends GenericDao<Wordlist, Integer> {
    List<Wordlist> listByProfileId(Integer profileId);

    List<Wordlist> listByTestId(Integer id);
}