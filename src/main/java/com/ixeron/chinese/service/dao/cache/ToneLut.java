package com.ixeron.chinese.service.dao.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ixeron.chinese.domain.Tone;
import com.ixeron.chinese.service.dao.ToneDao;

@Component
public class ToneLut {
    private Map<String, Tone> list = new HashMap<String, Tone>();

    @Autowired
    private ToneDao toneDao;
    
    public ToneLut() {
    }

    public Map<String, Tone> list() {
        if (list.size() == 0) {
            for (Tone tone : toneDao.list()) {
                list.put(tone.getSymbol(), tone);
            }
        }
        return list;
    }

    public Tone getBySymbol(String str) {
        if (str == null || str.length() != 1) {
            return null;
        }
        if (list().containsKey(str)) {
            return list().get(str);
        }
        return null;
    }
    public Integer getFirstToneId(){
        return list().get(" ").getId();
    }

    public Tone getById(Integer id) {
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
}
