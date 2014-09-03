package com.ixeron.chinese.service.dao;

import java.util.List;

import com.ixeron.chinese.domain.Word;
import com.ixeron.chinese.domain.WordPingying;

public interface WordPingyingDao extends GenericDao<WordPingying, Integer> {

    public WordPingying findByWordPingying(WordPingying wpy);
    
    public WordPingying findFirstPingying(Word word);

	public List<WordPingying> listByWordId(Integer id);
}