package com.ixeron.chinese.webservice.rest;

import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.View;

import com.ixeron.chinese.domain.Assignment;
import com.ixeron.chinese.domain.Test;
import com.ixeron.chinese.domain.Wordlist;
import com.ixeron.chinese.service.dao.AssignmentDao;
import com.ixeron.chinese.service.dao.TestDao;
import com.ixeron.chinese.service.dao.WordlistDao;
import com.ixeron.chinese.service.dao.WordlistWordDao;
import com.ixeron.chinese.service.dao.cache.PingyingCharacterLut;
import com.ixeron.chinese.service.dao.cache.ToneLut;


@Controller
@RequestMapping("/api")
@WebFilter(urlPatterns = {"/*"}) 
public class ChineseWordsTestController {
    @Autowired
    private AssignmentDao assignmentDao;
    @Autowired
    private TestDao testDao;
    @Autowired
    private WordlistDao wordlistDao;
    @Autowired
    private WordlistWordDao wordlistWordDao;
    @Autowired
    private PingyingCharacterLut pingyingCharacterLut;
    @Autowired
    private ToneLut toneLut;
    @Autowired
    private View jsonView_i;

    /**
     * Gets all assignments with their test ids equal to the supplied test id.
     * 
     * @return the assignments
     */
    @RequestMapping(value = {"/tests/{testId}/assignments/", "/tests/{testId}/assignments"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Assignment> listAssignmentsByTestId(@PathVariable("testId") String testId_p) {
        Test test = testDao.find(Integer.parseInt(testId_p));
        if(test == null){
            String msg = String.format("Invalid test id: %s.", (testId_p==null)? "null":testId_p);
            System.out.format("%s", msg);
            return null;
        }
        List<Assignment> assignments = assignmentDao.listByTestId(test.getId());
        return assignments;
    }

    @RequestMapping(value = {"/tests/{testId}/wordlists"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Wordlist> getWordlistListByTestId(@PathVariable("testId") String testId_p) {
        Test test = null;
        
        test = testDao.find(Integer.parseInt(testId_p));
        if(test == null){
            return null;
        }
        List<Wordlist> list = null;

        list = wordlistDao.listByTestId(test.getId());

        return list;
    }

    @RequestMapping(value = {"/tests/{testId}/wordlistwords"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Wordlist> getWordlistWordListByTestId(@PathVariable("testId") String testId_p) {
        Test test = null;

        test = testDao.find(Integer.parseInt(testId_p));
        if(test == null){
            return null;
        }
        List<Wordlist> list = null;

        list = wordlistDao.listByTestId(test.getId());

        return list;
    }

    @RequestMapping(value = {"/tests/{testId}/", "/tests/{testId}"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public Test getTest(@PathVariable("testId") String testId_p) {
        Test test = testDao.find(Integer.parseInt(testId_p));
        if(test == null){
            String msg = String.format("Invalid test id: %s.", (testId_p==null)? "null":testId_p);
            System.out.format("%s", msg);
            return null;
        }
        return test;
    }

    @RequestMapping(value = {"/tests/{testId}/", "/tests/{testId}"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Test saveTest(@RequestBody Test test_p, @PathVariable("testId") String testId_p, HttpServletResponse httpResponse_p) {
        if(testId_p == null || testId_p.isEmpty()){
            throw new RuntimeException("Test id is null or empty.");
        }
        testDao.update(test_p);

        httpResponse_p.setStatus(HttpStatus.OK.value()); 
        return test_p;
    }

    @RequestMapping(value = {"/tests/{testId}/wordlists/", "/tests/{testId}/wordlists"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Test addWordlistToTest(@PathVariable("testId") String testId_p, @RequestBody final List<Wordlist> wordlistList) {
        Test test = testDao.find(Integer.parseInt(testId_p));
        if(test == null){
            String msg = String.format("Invalid test id: %s.", (testId_p==null)? "null":testId_p);
            System.out.format("%s", msg);
            throw new IllegalArgumentException(msg);
        }
        //TODO: add wordlists to test
        for(Wordlist wordlist : wordlistList){
            
        }
        //TODO: re-load test so that it has all the list of wordlists
        return test;
    }

    @RequestMapping(value = {"/assignments/", "/assignments"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Assignment saveAssignment(@RequestBody final Assignment assignment) {
        assignmentDao.add(assignment);
        return assignment;
    }

    /**
     * Gets all assignments.
     * 
     * @return
     */
    @RequestMapping(value = {"/assignments/", "/assignments"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Assignment> getAssignments() {
        List<Assignment> list = null;

        list = assignmentDao.list();

        return list;
    }

    @RequestMapping(value = {"/tests/", "/tests"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Test saveTest(@RequestBody final Test test) {
        testDao.add(test);
        return test;
    }

    /**
     * Gets all tests.
     * 
     * @return all tests
     */
    @RequestMapping(value = {"/tests/", "/tests"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Test> getTests() {
        List<Test> list = null;

        list = testDao.list();

        return list;
    }
}