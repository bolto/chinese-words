/**
 * 
 */
package com.ixeron.chinese.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.ixeron.chinese.domain.Pingying;
import com.ixeron.chinese.domain.PingyingCharacter;
import com.ixeron.chinese.domain.Tone;
import com.ixeron.chinese.service.dao.cache.PingyingCharacterLut;
import com.ixeron.chinese.service.dao.cache.ToneLut;

@Component
public class WordUtils {
    @Autowired
    private PingyingCharacterLut pingyingCharacterLut;
    @Autowired
    private ToneLut toneLut;
    
    public List<Pingying> getPingyingList(String word){
        List<Pingying> list = null;
        try {
            String responseStr = getWebPageByWord(word);
            List<String> pYStrList = extractPingyingList(word, responseStr);
            if(pYStrList == null || pYStrList.size() == 0){
                // if response contains link to words, then try to load the page
                // <a href="/public/phonetic/index.php?word_sn=20030531003628" style="font-size: 24pt
                String wordLinkSearchStr = "/public/phonetic/index.php?word_sn=";
                int index = responseStr.indexOf(wordLinkSearchStr);
                if(index == -1){
                    return null;
                }
                responseStr = responseStr.substring(index);
                int endIndex = responseStr.indexOf("\"");
                String wordLink = responseStr.substring(0, endIndex);
                wordLink = "http://www.loxa.edu.tw" + wordLink;
                responseStr = getWebPage(wordLink);
                pYStrList = extractPingyingList(word, responseStr);
                if(pYStrList == null || pYStrList.size() == 0){
                    return null;
                }
            }
            for(String pyStr : pYStrList){
                Pingying py = toPingying(pyStr);
                if(py == null){
                    //System.out.format("Unable to parse ping ying from input: %s. \n", pyStr);
                    writeToFile(word, "c:\\words_not_inserted.txt");
                    writeToFile(String.format("%s : %s\n", word, pyStr), "c:\\words_not_inserted_with_pingying_string.txt");
                    continue;
                }
                if(list == null)
                    list = new ArrayList<Pingying>();
                list.add(py);
//                System.out.format("Word: %s has a ping ying of : %s.\n", word, toPingYingString(py));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    public static void writeToFile(String text, String filepath){
        try {
            Files.append(text, new File(filepath), Charsets.UTF_8);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String toPingyingString(Pingying py){
        if(py == null)
            return null;
        String pyStr = "";
        if(py.getFirstPyId()!= null){
            pyStr += pingyingCharacterLut.getById(py.getFirstPyId()).getSymbol();
        }
        if(py.getSecondPyId()!= null){
            pyStr += pingyingCharacterLut.getById(py.getSecondPyId()).getSymbol();
        }
        if(py.getThirdPyId()!= null){
            pyStr += pingyingCharacterLut.getById(py.getThirdPyId()).getSymbol();
        }
        if(py.getToneId()!= null){
            pyStr += toneLut.getById(py.getToneId()).getSymbol();
        }
        return pyStr;
    }

    /**
     * Parses input string and look for ping ying characters.
     * 
     * @param pyStr
     * @return
     */
    private Pingying toPingying(final String pyStr){
        if(pyStr == null || pyStr.length() == 0)
            return null;
        Pingying py = new Pingying();
        int firstPYIndex = -1;
        for (int i = 0; i < pyStr.length(); i++) {
            String character = pyStr.substring(i, i + 1);
            PingyingCharacter pyChar = pingyingCharacterLut.getBySymbol(character);
            if (pyChar != null) {
                firstPYIndex = i;
                switch (pyChar.getType()) {
                case 1:
                    py.setFirstPyId(pyChar.getId());
                    break;
                case 2:
                    py.setSecondPyId(pyChar.getId());
                    break;
                case 3:
                    py.setThirdPyId(pyChar.getId());
                    break;
                default:
                    break;
                }
            } else {
                Tone tone = toneLut.getBySymbol(character);
                if(tone != null)
                    py.setToneId(tone.getId());
                break;
            }
            if(i>(firstPYIndex + 3)){
                return null;
            }
        }
        if(py.getFirstPyId() == null){
            py.setFirstPyId(pingyingCharacterLut.getNoChar().getId());
        }
        if(py.getSecondPyId() == null){
            py.setSecondPyId(pingyingCharacterLut.getNoChar().getId());
        }
        if(py.getThirdPyId() == null){
            py.setThirdPyId(pingyingCharacterLut.getNoChar().getId());
        }
        if(py.getToneId() == null){
            py.setToneId(toneLut.getFirstToneId());
        }
        Date now = new Date();
        py.setCreated(now);
        py.setUpdated(now);
        return py;
    }

    private List<String> extractPingyingList(final String chineseWord, final String responseStr){
        List<String> list = new ArrayList<String>();
        List<String> workingList = extractPingyingTagList(responseStr);
        for(String str : workingList){
            List<String> pyList = extractPingyingOnly(chineseWord, str, true);
            if(pyList != null && pyList.size() > 0){
                for(String pyStr : pyList){
                    list.add(pyStr);
                }
            }
        }
        return list;
    }
    
    private List<String> extractPingyingOnly(final String chineseWord, final String parsedStr, boolean isLevelOne){
        List<String> list = new ArrayList<String>();
        List<String> nextPingying = new ArrayList<String>();
        if(parsedStr == null || parsedStr.length() == 0){
            return null;
        }
        String result = "";
        // TODO: need to also take care of parsing two consecutive ping yings
        //       eg: sister in Chinese has three ping yings, two of them one after the other.
        for(int i = 0; i<parsedStr.length(); i++){
            String singleChar = parsedStr.substring(i, i+1);
            // ping ying string ends when you start getting neither ping ying nor tone characters
            if(!result.isEmpty() && pingyingCharacterLut.getBySymbol(singleChar) == null && toneLut.getBySymbol(singleChar) == null){
                nextPingying = extractPingyingOnly(chineseWord, parsedStr.substring(i+1), false);
                break;
            }
            if(result.isEmpty()){
                if(pingyingCharacterLut.getBySymbol(singleChar) == null)
                    continue;
                // bad ping ying string as only tone is found
                if(toneLut.getBySymbol(singleChar) != null)
                    break;
            }
                
            if(pingyingCharacterLut.getBySymbol(singleChar) != null){
                result += singleChar;
                continue;
            }

            // once tone is found, current ping ying is considered complete, move on to find to next
            if(toneLut.getBySymbol(singleChar) != null){
                result += singleChar;
                nextPingying = extractPingyingOnly(chineseWord, parsedStr.substring(i+1), false);
                break;
            }
        }
        if(nextPingying != null){
            for(String py : nextPingying){
                list.add(py);
            }
        }
        if(result != null && !result.isEmpty()){
            list.add(result);
        }
        if(isLevelOne && (result == null || result.isEmpty())){
            writeToFile(chineseWord + "\n", "c:\\words_not_inserted.txt");
            writeToFile(String.format("%s : %s\n", chineseWord, parsedStr), "c:\\words_not_inserted_with_pingying_string.txt");
        }
        System.out.format("parsedStr: %s, actual ping ying: %s\n", parsedStr, (result == null)? "":result);
        return list;
    }

    private List<String> extractPingyingTagList(final String responseStr){
        final String imgTag = "align=absmiddle>";
        final String breakTag = "<br>";
        final String openTag = "<";
        String workingStr = responseStr;
        System.out.println(workingStr);
        List<String> tagStrList = new ArrayList<String>();

        while(true){
            // first try to find the beginning tag of a ping ying
            int index = workingStr.indexOf(imgTag);
            if(index == -1)
                break;
            // move index to the end of search string
            index += imgTag.length();
            workingStr = workingStr.substring(index);

            // remove un-wanted break tags at the beginning of the ping ying string
            while(workingStr.startsWith(breakTag)){
                workingStr = workingStr.substring(breakTag.length());
            }
            // find the end of ping ying string by locating next break tag
            index = workingStr.indexOf(openTag);
            // if index = 0, then it is also one of the web pages that
            // has layouts that are not of a standard format.  So need
            // to handle this exception here.  Just remove the opening
            // tag and continue
            if(index == 0){
                workingStr = workingStr.substring(index + 1);
            }
            if(index == -1)
                break;

            index = workingStr.indexOf(openTag);
            if(index == -1)
                break;
            tagStrList.add(workingStr.substring(0, index-1).trim());
            workingStr = workingStr.substring(index);
        }
        return tagStrList;
    }

    private String getWebPage(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        final String USER_AGENT = "Mozilla/5.0";
        // add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        //int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        String responseStr = response.toString();
        //System.out.println(response.toString());
        return responseStr;

    }

    // HTTP GET request with Chinese word in url parameter
    private String getWebPageByWord(final String chineseWord) throws Exception {
        String url = "http://www.loxa.edu.tw/public/phonetic/index.php?Q=1&keyword={one-word-encoded-in-big5}&Submit=%ACd%B8%DF";
        String encodedString = URLEncoder.encode(chineseWord, "Big5");
        //System.out.format("Encoded string: %s", encodedString);
        url = url.replace("{one-word-encoded-in-big5}", encodedString);
        return getWebPage(url);
    }
}
