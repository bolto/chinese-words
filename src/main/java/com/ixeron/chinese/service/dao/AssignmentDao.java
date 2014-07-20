package com.ixeron.chinese.service.dao;

import java.util.List;

import com.ixeron.chinese.domain.Assignment;

public interface AssignmentDao extends GenericDao<Assignment, Integer> {

    List<Assignment> listByTestId(Integer id);
}