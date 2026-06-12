package SpringBoot.Personal_Finance_Tracker.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot.Personal_Finance_Tracker.model.dto.ExpenseRequest;
import SpringBoot.Personal_Finance_Tracker.model.entity.Expense;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;
import jakarta.validation.Valid;

@Service
public class ExpenseService {

    private final SpringBoot.Personal_Finance_Tracker.repository.ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;

    ExpenseService(SpringBoot.Personal_Finance_Tracker.repository.ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpenseForUser(ExpenseRequest expenseRequest, String userEmail){
        try{
            System.out.println("Method addExpenseForUser: ");
            UserEntity user = userService.getUserbyUserEmail(userEmail);
            if(user == null){
                return null;
            }
            Expense expense = new Expense();
            expense.setExpenseAmount(expenseRequest.getExpenseAmount());
            expense.setExpenseCategory(expenseRequest.getExpenseCategory());
            expense.setExpenseDate(Date.valueOf(expenseRequest.getExpenseDate()));
            expense.setUser(user);
            return expenseRepository.save(expense);
        }catch(Exception e){
            System.out.println("Exception addExpenseForUser: " + e.getMessage());
            return null;
        }
    }
    
}
