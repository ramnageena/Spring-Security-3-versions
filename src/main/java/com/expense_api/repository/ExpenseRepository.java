package com.expense_api.repository;

import com.expense_api.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    /*   CUSTOM FINDER METHODS   /*
//
//    /* SELECT * FROM expense_tracker_tbl WHERE category=? */
//    List<Expense>findByCategory(String category);
//
//    /*  SELECT * FROM expense_tracker_tbl WHERE name LIKE '%keyword%' */
//    List<Expense>findByNameContaining(String name);
//
//      /* SELECT * FROM expense_tracker_tbl WHERE date BETWEEN 'startDate' AND 'endDate' */
//    List<Expense> findByDateBetween(Date startDate, Date endDate);
//
//    Expense findByUserId(Long userId);
//
//    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);
//
//    Page<Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);
//
//    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);
//
//    Page<Expense> findByUserId(Long userId, Pageable page);
//
//    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
