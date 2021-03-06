package com.ixeron.chinese.webservice.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.ixeron.chinese.domain.Pingying;
import com.ixeron.chinese.domain.PingyingCharacter;
import com.ixeron.chinese.domain.Profile;
import com.ixeron.chinese.domain.ProfileWordlist;
import com.ixeron.chinese.domain.ProfileWordlistId;
import com.ixeron.chinese.domain.Test;
import com.ixeron.chinese.domain.Tone;
import com.ixeron.chinese.domain.Word;
import com.ixeron.chinese.domain.WordPingying;
import com.ixeron.chinese.domain.WordPingyingId;
import com.ixeron.chinese.domain.Wordlist;
import com.ixeron.chinese.domain.WordlistWord;
import com.ixeron.chinese.service.dao.PingyingCharacterDao;
import com.ixeron.chinese.service.dao.PingyingDao;
import com.ixeron.chinese.service.dao.ProfileDao;
import com.ixeron.chinese.service.dao.ProfileWordlistDao;
import com.ixeron.chinese.service.dao.ToneDao;
import com.ixeron.chinese.service.dao.WordDao;
import com.ixeron.chinese.service.dao.WordPingyingDao;
import com.ixeron.chinese.service.dao.WordlistDao;
import com.ixeron.chinese.service.dao.WordlistWordDao;
import com.ixeron.chinese.service.dao.cache.PingyingCharacterLut;
import com.ixeron.chinese.service.dao.cache.ToneLut;
import com.ixeron.chinese.service.impl.WordlistWordDaoImpl;
import com.ixeron.chinese.tools.Big5Parser;
import com.ixeron.chinese.webservice.rest.dto.WordDto;
import com.ixeron.chinese.webservice.rest.dto.WordDivDto;


/**
 * Tomcat server.xml needs to have all connectors set UTF-8 <Connector
 * port="8009" URIEncoding="UTF-8" useBodyEncodingForURI="true" The WebFilter
 * and Bean public CharacterEncodingFilter for Spring Boot or you can use
 * web.xml for <filter> <filter-name>EncodingFilter</filter-name>
 * <filter-class>org
 * .springframework.web.filter.CharacterEncodingFilter</filter-class>
 * <init-param> <param-name>encoding</param-name>
 * <param-value>UTF-8</param-value> </init-param> <init-param>
 * <param-name>forceEncoding</param-name> * <param-value>true</param-value>
 * </init-param> </filter>
 * 
 * <filter-mapping> <filter-name>EncodingFilter</filter-name>
 * <url-pattern>*</url-pattern> </filter-mapping>
 * 
 */
@Configuration
@ImportResource(value = { "application-context.xml" })
@ComponentScan(basePackages = { "com.ixeron" })
@EnableAutoConfiguration
@Controller
@RequestMapping("/api")
@WebFilter(urlPatterns = {"/*"}) 
public class ChineseWordsController {
    @Autowired
    private ToneDao toneDao;
    @Autowired
    private WordDao wordDao;
    @Autowired
    private WordlistDao wordlistDao;
    @Autowired
    private WordlistWordDao wordlistWordDao;
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private ProfileWordlistDao profileWordlistDao;
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
    private Big5Parser parser;
    @Autowired
    private View jsonView_i;

    private static final String DATA_FIELD = "data";

    /**
     * Gets all tones.
     * 
     * @return the tones
     */
    @RequestMapping(value = {"/tones/", "/tones"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Tone> getTones() {
        List<Tone> tones = toneDao.list();

        return tones;
    }

    @RequestMapping(value = {"/profiles/", "/profiles"}, method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Profile> getProfiles() {
        List<Profile> list = null;

        list = profileDao.list();

        return list;
    }

    @RequestMapping(value = {"/profiles/{profileId}/wordlists"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Wordlist> getWordlistListByProfileId(@PathVariable("profileId") String profileId_p) {
        Profile profile = null;
        
        profile = profileDao.find(Integer.parseInt(profileId_p));
        if(profile == null){
            return null;
        }
        List<Wordlist> list = null;

        list = wordlistDao.listByProfileId(profile.getId());

        return list;
    }

    @RequestMapping(value = {"/wordlists/", "/wordlists"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Wordlist> getWordlistList() {
        List<Wordlist> list = null;

        list = wordlistDao.list();

        return list;
    }

    @RequestMapping(value = {"/wordlists/", "/wordlists"}, method = RequestMethod.POST)
    @ResponseBody
    public Wordlist saveWordlist(@RequestBody @Valid final Wordlist wordlist) {
    	Date now = new Date();
    	wordlist.setCreated(now);
    	wordlist.setUpdated(now);
    	wordlistDao.add(wordlist);
    	
    	wordlistDao.add(wordlist);

    	for(WordlistWord word : wordlist.getWords()){
    		
    	}
        return wordlist;
    }

    @RequestMapping(value = {"/wordlists/{id}", "/wordlists/{id}/"}, method = RequestMethod.GET)
    @ResponseBody
    public Wordlist getWordlistList(@PathVariable("id") Integer id_p) {
        if (id_p == null){
            return null;
        }
        Wordlist wl = wordlistDao.find(id_p);

        return wl;
    }

    @RequestMapping(value = {"/profiles/{profileId}/wordlists/{wordlistId}", "/profiles/{profileId}/wordlists/{wordlistId}/"}, produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<WordDivDto> getWordListWebPage(@PathVariable("profileId") String profileId_p, @PathVariable("wordlistId") String wordlistId_p) {
        Profile profile = getProfile(profileId_p);
        if(profile == null){
            String msg = String.format("Invalid profile id: %s.", (profileId_p==null)? "null":profileId_p);
            return null;
        }
        Wordlist wordlist = getWordlist(profileId_p, wordlistId_p);
        if(wordlist == null){
            String msg = String.format("Invalid wordlist id: %s.", (wordlistId_p==null)? "null":wordlistId_p);
            return null;
        }
        List<WordlistWord> wordlistWords = wordlistWordDao.listByWordlistId(wordlist.getId());
        List<WordDivDto> wordDtos = new ArrayList<WordDivDto>();

        int counter = 0;
        if(wordlistWords == null || wordlistWords.size() ==0){
            return null;
        }
        for(WordlistWord word : wordlistWords){
            counter ++;
            WordDivDto wordDto = new WordDivDto();
            wordDto.setLetter(word.getSymbol());
            String pyStr = "";
            String toneStr = "";
            int toneId = 0;
            if(word.getWordPingyingId()!=null){
                Pingying py = word.getWordPingyingId().getPingying();
                if(py.getFirstPyId() != pingyingCharacterLut.getNoChar().getId()){
                    pyStr += pingyingCharacterLut.getById(py.getFirstPyId()).getSymbol();
                }
                if(py.getSecondPyId() != pingyingCharacterLut.getNoChar().getId()){
                    pyStr += pingyingCharacterLut.getById(py.getSecondPyId()).getSymbol();
                }
                if(py.getThirdPyId() != pingyingCharacterLut.getNoChar().getId()){
                    pyStr += pingyingCharacterLut.getById(py.getThirdPyId()).getSymbol();
                }
                toneId = py.getToneId();
                if(py.getToneId() != 1){
                    toneStr = toneLut.getById(py.getToneId()).getSymbol();
                }
            }
            
            switch (pyStr.length()){
            case 1:
                // template 1
                wordDto.setThree(pyStr);
                if(toneId == 5){
                    // template 2
                    wordDto.setTwoBold(toneStr);
                }else if(toneId != 0){
                    // template 3
                    wordDto.setTone(toneStr);
                }
                break;
            case 2:
                // template 4
                wordDto.setTwo(pyStr.substring(0, 1));
                wordDto.setThree(pyStr.substring(1, 2));
                if(toneId == 5){
                    // template 5
                    wordDto.setOne(toneStr);
                }else if(toneId != 0){
                    // template 6
                    wordDto.setTone(toneStr);
                }
                break;
            case 3:
                // template 7
                wordDto.setTwo(pyStr.substring(0, 1));
                wordDto.setThree(pyStr.substring(1, 2));
                wordDto.setFour(pyStr.substring(2, 3));
                if(toneId == 5){
                    // template 8
                    wordDto.setOne(toneStr);
                }else if(toneId != 0){
                    // template 9
                    wordDto.setTone(toneStr);
                }
                break;
            }
            wordDtos.add(wordDto);
        }
        System.out.format("Counter: %d.\nWordDtos size: %d.\n", counter, wordDtos.size());
        return wordDtos;
    }

    @RequestMapping(value = {"/profiles/{profileId}/addWordlist"}, produces = "text/html; charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String getProfileWebPage(@PathVariable("profileId") String profileId_p) {
        Profile profile = getProfile(profileId_p);
        if(profile == null){
            return String.format("Invalid profile id: %s.", (profileId_p==null)? "null":profileId_p);
        }
        return getProfileAddWordListWebPageHtml().replace("{profileId}", profileId_p);
    }

    private Profile getProfile(String profileIdStr){
        if(profileIdStr == null || profileIdStr.isEmpty()){
            return null;
        }
        Integer profileId = Integer.parseInt(profileIdStr);
        if(profileId == null || profileId < 0){
            return null;
        }
        return profileDao.find(profileId);
    }
    
    private Wordlist getWordlist(String profileIdStr, String wordlistIdStr){
        if(profileIdStr == null || wordlistIdStr == null){
            return null;
        }
        Profile profile = getProfile(profileIdStr);
        if(profile == null)
            return null;
        Integer wordlistId = Integer.parseInt(wordlistIdStr);
        if(wordlistId == null || wordlistId < 0){
            return null;
        }
        Wordlist wordlist = wordlistDao.find(wordlistId);
        return wordlist;
    }

    @RequestMapping(value = {"/wordlists/addWordlist"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public RestResult addWordlist(@RequestParam("wordlist") String wordList_p, @RequestParam("name") String name_p,
            HttpServletResponse httpResponse_p, WebRequest request_p) {
        RestResult res = new RestResult();

        if(name_p == null || name_p.equals("")){
        	res.setSuccess(false);
        	res.setErrors("Wordlist name can not be empty.");
        }
        if(wordList_p == null || wordList_p.length() == 0){
        	res.setSuccess(false);
        	res.setErrors("No words were submitted and therefore nothing was done.");
        }

        Wordlist wordlistEntry = new Wordlist();
        Date now = new Date();
        wordlistEntry.setCreated(now);
        wordlistEntry.setUpdated(now);
        wordlistEntry.setName(name_p);
        wordlistDao.add(wordlistEntry);

        addWordlistWords(wordlistEntry.getId(), wordList_p);

        res.setSuccess(true);
        res.setMessage(String.format("Wordlist %s has been created with supplied words.", wordlistEntry.getName()));
        return res;
    }
    private void addWordlistWords(Integer wordlistId, String texts){
    	if(wordlistId == null)
    		return;
    	Wordlist wordlist = wordlistDao.find(wordlistId);
    	if(wordlist == null)
    		return;
    	if(texts == null || texts.length() <= 0)
    		return;

    	Date now = null;
        Map<String, Word> words = wordDao.listToMap(texts);

        for(int i = 0; i < texts.length(); i++){
            String symbol = texts.substring(i, i+1);
            WordlistWord wordlistWord = new WordlistWord();
            wordlistWord.setSymbol(symbol);
            wordlistWord.setListOrder(i);
            wordlistWord.setWordlistId(wordlistId);
            Word word = null;
            if(words != null && words.containsKey(symbol)){
                word = words.get(symbol);
            }
            if (word != null){
                WordPingying wpy = wordPingyingDao.findFirstPingying(word);
                if (wpy != null)
                	wordlistWord.setWordPingyingId(wpy.getPk());
                else
                	System.out.println(String.format("Pingying not found for %s.", word.getSymbol()));
            }
            now = new Date();
            wordlistWord.setCreated(now);
            wordlistWord.setUpdated(now);
            wordlistWordDao.add(wordlistWord);
        }
    }

    private String getProfileAddWordListWebPageHtml(){
        URL url = Resources.getResource("addWordlist.html");
        String text = null;
        try {
            text = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }

    private String getTestHtml(){
        URL url = Resources.getResource("search_form.html");
        String text = null;
        try {
            text = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }

    @RequestMapping(value = {"/search", "/search/"}, produces={"text/html; charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public String showSearchForm() {
        URL url = Resources.getResource("search_form.html");
        String text = null;
        try {
            text = Resources.toString(url, Charsets.UTF_8);
            text = text.replace("{words}", "");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }
    
    @RequestMapping(value = {"/search", "/search/"}, produces={"text/html; charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public String getWords(@RequestParam("wordlist") String wordList,
            HttpServletResponse httpResponse_p, WebRequest request_p) {
        //System.out.format("wordList: %s.\n", wordList);
        Map<String, Word> words = null;

        if (wordList != null && wordList.length() != 0) {
            words = wordDao.listToMap(wordList);
        }
        String wordsStr = "";
        if (words == null || words.size() == 0) {
            return getTestHtml().replace("{words}", wordsStr);
        }
        List<WordDto> wordDtos = new ArrayList<WordDto>();
        Word word = null;
        for(int i = 0; i< wordList.length(); i++){
            String wordChar = wordList.substring(i, i+1);
            if(!words.containsKey(wordChar)){
                wordsStr += wordChar + "<br>";
                continue;
            }

            word = words.get(wordChar);
            WordDto wordDto = new WordDto();
            wordDto.setSymbol(word.getSymbol());

            wordDtos.add(wordDto);
            wordsStr += wordDto.toString() + "<br>";
        }
        return getTestHtml().replace("{words}", wordsStr);
    }

    @RequestMapping(value = {"/words/{wordId}", "/words/{wordId}/"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Word getWord(@PathVariable("wordId") String wordId_p) {
        // TODO: this needs to return a list of words using GET filter parameters
    	//check to see if it's integer
    	// then check to see if id returns a valid word
    	// then check to see if wordId is actually a Chinese word
        Word word = wordDao.find(Integer.parseInt(wordId_p));

        return word;
    }

    /**
     * Gets all wordPingyings.
     * RequestParam("word") String word 
     * @return all wordPingyings
     */
    @RequestMapping(value = {"/wordpingyings/", "/wordpingyings"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<WordPingying> getWordPingyings(@RequestParam(value="word", required=true) String word_p) {
    	
        if(word_p.trim().equals("") || word_p.trim().length() != 1){
        	return null;
        }
        Word word = wordDao.findBySymbol(word_p);
        if(word == null)
        	return null;
        List<WordPingying> list = wordPingyingDao.listByWordId(word.getId());
        return list;
    }

    @RequestMapping(value = {"/wordpingyings/", "/wordpingyings"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public WordPingying saveTest(@RequestBody WordPingying wordPingying_p, HttpServletResponse httpResponse_p) {
		wordPingyingDao.update(wordPingying_p);
        httpResponse_p.setStatus(HttpStatus.OK.value()); 
        return wordPingying_p;
    }

    @RequestMapping(value = {"/wordlistword/{wordlistwordId}/", "/wordlistword/{wordlistwordId}"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public WordlistWord saveWordlistWord(@RequestBody WordlistWord wordlistword_p, HttpServletResponse httpResponse_p) {
        WordlistWord ww = wordlistWordDao.find(wordlistword_p.getId());
        WordPingyingId wpi = ww.getWordPingyingId();
        wpi.getPingying().setId(wordlistword_p.getWordPingyingId().getPingying().getId());
        ww.setUpdated(new Date());
        wordlistWordDao.update(ww);
        
        ww = wordlistWordDao.find(wordlistword_p.getId());
        
        httpResponse_p.setStatus(HttpStatus.OK.value()); 
        return ww;
    }

    /**
     * Return the detailed word data of a given Chinese word.  Request parameter search should contain only word Chinese word. 
     * @return a word object matching supplied search word
     */
    @RequestMapping(value = {"/wordlistword/{wordlistwordId}/", "/wordlistword/{wordlistwordId}"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public WordlistWord getWordlistWordById(@PathVariable("wordlistwordId") Integer wordlistwordId_p) {
        WordlistWord word = null;

        word = wordlistWordDao.find(wordlistwordId_p);

        return word;
    }
    
    /**
     * Return the detailed word data of a given Chinese word.  Request parameter search should contain only word Chinese word. 
     * @return a word object matching supplied search word
     */
    @RequestMapping(value = {"/words/", "/words"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Word> findWord(@RequestParam(value="search", required=false) String search) {
        List<Word> words = null;

        if(search != null && !search.trim().equals("")){
            words = wordDao.findBySymbols(search.substring(0,1));
        }

        if(words == null || words.size() ==0){
            return null;
        }
        return words;
    }
    
    /**
     * Gets all ping ying characters.
     * 
     * @return all ping ying characters
     */
    @RequestMapping(value = {"/pingying_characters/", "/pingying_characters"}, method = RequestMethod.GET)
    @ResponseBody
    public List<PingyingCharacter> getPingyingCharacters() {
        List<PingyingCharacter> pingyingCharacters = pingyingCharacterDao.list();

        return pingyingCharacters;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
      final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
      characterEncodingFilter.setEncoding("UTF-8");
      characterEncodingFilter.setForceEncoding(true);
      return characterEncodingFilter;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChineseWordsController.class, args);
    }
}