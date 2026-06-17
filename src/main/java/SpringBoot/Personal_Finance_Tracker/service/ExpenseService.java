package SpringBoot.Personal_Finance_Tracker.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot.Personal_Finance_Tracker.model.dto.ExpenseRequest;
import SpringBoot.Personal_Finance_Tracker.model.dto.ExpenseResponse;
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

    public List<ExpenseResponse> getAllExpenseForUser(String userEmail){
        try{
            System.out.println("Method getAllExpenseForUser: ");
            UserEntity user = userService.getUserbyUserEmail(userEmail);
            if(user == null){
                return null;
            }
            List<Expense> expenses = expenseRepository.findAllByUser(user);
            List<ExpenseResponse> responseList = new ArrayList<>();
            for(Expense expense : expenses){
                responseList.add(new ExpenseResponse(
                    expense.getExpenseId(), 
                    expense.getExpenseAmount(), 
                    expense.getExpenseCategory(),
                    expense.getExpenseDate() != null ? expense.getExpenseDate().toLocalDate() : null
                ));
            }
            System.out.println(responseList);
            return responseList;
        }catch(Exception e){
            System.out.println("Exception getAllExpenseForUser: " + e.getMessage());
            return null;
        }
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

    public boolean delteExpenseForUser(Long expenseId, String userEmail){
        try{
            System.out.println("Method DeleteExpenseForUser: ");
            UserEntity user = userService.getUserbyUserEmail(userEmail);
            if(user == null){
                System.out.println("User doesn't exists");
                return false;
            }else{
                Expense expense = expenseRepository.findByExpenseId(expenseId);
                if(expense == null){
                    System.out.println("Expense doesn't exists for user");
                    return false;
                }
                if(expense.getUser().getUserEmail().equals(user.getUserEmail())){
                    expenseRepository.delete(expense);
                    return true;
                }
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception deleteExpenseForUser: " + e.getMessage());
            return false;
        }
    }

    public Double getTotalExpenseForUser(String userEmail){
        try{
            System.out.println("Method getTotalExpenseForUser: ");
            UserEntity user = userService.getUserbyUserEmail(userEmail);
            if(user == null){
                return 0.0;
            }
            Double totalExpense = expenseRepository.getTotalExpenseForUser(user);
            System.out.println("Total Expense: " + totalExpense);
            return totalExpense;
        }catch(Exception e){
            System.out.println("Exception getTotalExpenseForUser: " + e.getMessage());
            return 0.0;
        }
    }
}
