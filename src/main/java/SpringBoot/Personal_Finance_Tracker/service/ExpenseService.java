package SpringBoot.Personal_Finance_Tracker.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot.Personal_Finance_Tracker.model.dto.ExpenseRequest;
import SpringBoot.Personal_Finance_Tracker.model.entity.Expense;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;
import SpringBoot.Personal_Finance_Tracker.repository.ExpenseRepository;
import jakarta.validation.Valid;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;


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
    
    public Expense updateExpenseForUser(Long expenseId, ExpenseRequest expenseRequest, String userEmail){
        try{
            System.out.println("Method updateExpenseForUser: ");
            UserEntity user = userService.getUserbyUserEmail(userEmail);
            if(user == null){
                System.out.println("User doesn't exists");
                return null;
            }else{
                Expense expense = expenseRepository.findByExpenseId(expenseId);
                if(expense.getUser().getUserEmail().equals(user.getUserEmail())){
                    expense.setExpenseAmount(expenseRequest.getExpenseAmount());
                    expense.setExpenseCategory(expenseRequest.getExpenseCategory());
                    expense.setExpenseDate(Date.valueOf(expenseRequest.getExpenseDate()));
                    return expenseRepository.save(expense);
                }
            }
            return null;
        }catch(Exception e){
            System.out.println("Exception updateExpenseForUser : " + e.getMessage());
            return null;
        }
    }
}
