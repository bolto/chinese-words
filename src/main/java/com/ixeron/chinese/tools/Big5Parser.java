/**
 * 
 */
package com.ixeron.chinese.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ixeron.chinese.domain.Pingying;
import com.ixeron.chinese.domain.Word;
import com.ixeron.chinese.domain.WordPingying;
import com.ixeron.chinese.service.dao.PingyingCharacterDao;
import com.ixeron.chinese.service.dao.PingyingDao;
import com.ixeron.chinese.service.dao.ToneDao;
import com.ixeron.chinese.service.dao.WordDao;
import com.ixeron.chinese.service.dao.WordPingyingDao;
import com.ixeron.chinese.service.dao.cache.PingyingCharacterLut;
import com.ixeron.chinese.service.dao.cache.ToneLut;

/**
 * Parses Chinese characters from a file and insert new valid 
 * Chinese characters into database.
 *
 */
@Component
@Controller
public class Big5Parser {
    @Autowired
    private ToneDao toneDao;
    @Autowired
    private WordDao wordDao;
    @Autowired
    private PingyingDao pingyingDao;
    @Autowired
    private WordPingyingDao wordPingyingDao;
    @Autowired
    private PingyingCharacterDao pingyingCharacterDao;
    @Autowired
    private PingyingCharacterLut pingyingCharacterLut;
    @Autowired
    private ToneLut toneLut;
    @Autowired
    private WordUtils wordUtils;
    
    private static String BIG5_CHARACTERS_FILEPATH = "D:\\git_local\\projects\\chinese-word-web-app\\src\\main\\resources\\big5-test.txt";
    private static String allBig5Characters;

    public static String getAllBig5Characters(){
        if(allBig5Characters == null){
            allBig5Characters = big5FileToString(BIG5_CHARACTERS_FILEPATH);
        }
        return allBig5Characters;
    }

    public static boolean isBig5Character(String singleCharStr){
        if(singleCharStr == null || singleCharStr.length() != 1){
            return false;
        }
        return getAllBig5Characters().contains(singleCharStr);
    }

    public void insertToDatabase(String singleCharStr){
        if(!isBig5Character(singleCharStr)){
            throw new IllegalArgumentException(String.format("Not valid single Chinese character: %s\n", singleCharStr));
        }
        Word word = wordDao.findBySymbol(singleCharStr);
        if(word == null){
            word = new Word();
            word.setSymbol(singleCharStr);
            word.setCreated(new Date());
            word.setUpdated(new Date());
            wordDao.add(word);
        }
        List<Pingying> pyList = wordUtils.getPingyingList(singleCharStr);
//         this if is only used to output words that ping yings were not found and you want to write those words to a file
//        if (pyList == null || pyList.size() == 0) {
//            WordUtils.writeToFile(singleCharStr + "\n", "D:\\git_local\\projects\\chinese-word-web-app\\src\\main\\resources\\big5-missing2.txt");
//        }

        if (pyList != null && pyList.size() != 0) {
            // insert word
//            Word word = wordDao.findBySymbol(singleCharStr);
//
//            if (word == null) {
//                word = new Word();
//                word.setSymbol(singleCharStr);
//                word.setCreated(new Date());
//                word.setUpdated(new Date());
//                wordDao.add(word);
//            }

            // insert ping ying list
            for (Pingying py : pyList) {
                System.out.format("Word: %s has ping ying: %s.\n",
                        singleCharStr, wordUtils.toPingyingString(py));
                Pingying curPy = pingyingDao.findByPingying(py);
                if(curPy == null){
                    // insert Ping Ying into database since it's not in database
                    Date now = new Date();
                    py.setCreated(now);
                    py.setUpdated(now);
                    pingyingDao.add(py);
                    // then we associate ping ying to the word
                    WordPingying newWpy = new WordPingying();
                    newWpy.setWord(word);
                    newWpy.setPingying(py);
                    now = new Date();
                    newWpy.setCreated(now);
                    newWpy.setUpdated(now);
                    wordPingyingDao.add(newWpy);
                }else{
                    // try to see if word and ping ying are already associated
                    WordPingying newWpy = new WordPingying();
                    newWpy.setWord(word);
                    newWpy.setPingying(curPy);
                    WordPingying curWpy = wordPingyingDao.findByWordPingying(newWpy);
                    if(curWpy == null){
                        // word and ping ying are not associated, 
                        // so we will add the association to db
                        Date now = new Date();
                        newWpy.setCreated(now);
                        newWpy.setUpdated(now);
                        wordPingyingDao.add(newWpy);
                    }
                }
            }
        }
    }

    public void parse() {
        String chineseCharList;
        try {
            //words = Resources.toString(url, Charsets.UTF_8);
            chineseCharList = Big5Parser.getAllBig5Characters();
            for (int i = 0; i < chineseCharList.length(); i++) {
                insertToDatabase(chineseCharList.substring(i, i+1));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void setListOrder(){
        List<Word> words = wordDao.list();
        Set<Integer> nullList = new TreeSet<Integer>();
        nullList.add(10907);
        
        for(Word word : words){
            if(!nullList.contains(word.getId()))
                continue;
            int counter = 0;
            for(Pingying py : word.getPingyings()){
                counter ++;
                WordPingying wpy = new WordPingying();
                wpy.setWord(word);
                wpy.setPingying(py);
                WordPingying wpy2 = wordPingyingDao.findByWordPingying(wpy);
                wpy2.setListOrder(counter);
                wordPingyingDao.update(wpy2);
            }
        }
    }

    public static String big5FileToString(String file){
        String str = null;
        //String file="big5.txt";
        FileInputStream fis;
        try {
            fis = new FileInputStream(new File(file));
            // java.io.FileNotFoundException
            BufferedReader br =new BufferedReader(new InputStreamReader(fis,"UTF-8"));
             // java.io.UnsupportedEncodingException
            // java.io.IOException
            while(br.ready())
            {
              String line=br.readLine();
              if(str == null)
                  str = "";
              str += line;
              System.out.println(line);
              System.out.flush();
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
                "parser-context.xml");
        Big5Parser parser = Big5Parser.class.cast(ctx.getBean("big5Parser"));
        parser.parse();
        parser.setListOrder();
        ctx.close();
    }

}
