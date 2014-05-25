package com.ixeron.chinese.service.dao.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ixeron.chinese.domain.PingyingCharacter;
import com.ixeron.chinese.service.dao.PingyingCharacterDao;

@Component
public class PingyingCharacterLut {

    private Map<String, PingyingCharacter> list = new HashMap<String, PingyingCharacter>();

    @Autowired
    private PingyingCharacterDao pingYingCharacterDao;
    
    private PingyingCharacter noChar;

    public PingyingCharacterLut() {
    }

    public Map<String, PingyingCharacter> list() {
        if (list.size() == 0) {
            for (PingyingCharacter pyChar : pingYingCharacterDao.list()) {
                list.put(pyChar.getSymbol(), pyChar);
            }
        }
        return list;
    }

    public PingyingCharacter getById(Integer id) {
        if (id == null) {
            return null;
        }
        for(String key : list().keySet()){
            if(list().get(key).getId() == id){
                return list().get(key);
            }
        }
        return null;
    }
    
    public PingyingCharacter getBySymbol(String str) {
        if (str == null || str.length() != 1) {
            return null;
        }
        if (list().containsKey(str)) {
            return list().get(str);
        }
        return null;
    }
    public PingyingCharacter getNoChar() {
        if(noChar == null)
            noChar = list().get("0");
        return noChar;
    }
}
