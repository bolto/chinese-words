/**
 * 
 */
package com.ixeron.chinese.service.impl;

import org.springframework.stereotype.Repository;

import com.ixeron.chinese.domain.PingyingCharacter;
import com.ixeron.chinese.service.dao.HibernateDao;
import com.ixeron.chinese.service.dao.PingyingCharacterDao;

@Repository("pingyingCharacterDao")
public class PingyingCharacterDaoImpl extends HibernateDao <PingyingCharacter, Integer> implements PingyingCharacterDao {
}
