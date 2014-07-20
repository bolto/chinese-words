package com.ixeron.chinese.webservice.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class WordDto {
    private String symbol;
    List<String> pingyings = new ArrayList<String>();
//    private List<PingyingDto> pingyings = new ArrayList<PingyingDto>();
    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }
    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void addPingying(String py){
        pingyings.add(py);
    }
    /**
     * @return the pingyings
     */
    public List<String> getPingyings() {
        return pingyings;
    }
    /**
     * @param pingyings the pingyings to set
     */
    public void setPingyings(List<String> pingyings) {
        this.pingyings = pingyings;
    }
    
    public String getPingyingString(){
        String str = "";
        int counter = 0;
        for(String py : getPingyings()){
            counter++;
            if(counter > 1)
                str += "   ";
            str += String.format("%d. %s", counter, py);
        }
        return str;
    }
    
    public String toString(){
        return String.format("%s: %s\n", getSymbol(), getPingyingString());
    }
}
