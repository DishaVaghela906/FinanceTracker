package SpringBoot.Personal_Finance_Tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.Personal_Finance_Tracker.model.dto.ExpenseRequest;
import SpringBoot.Personal_Finance_Tracker.model.entity.Expense;
import SpringBoot.Personal_Finance_Tracker.service.ExpenseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    
    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody @Valid ExpenseRequest expenseRequest){
        try{
            System.out.println("Method addExpense: ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() ==null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
            String userEmail = authentication.getName();
            Expense saved = expenseService.addExpenseForUser(expenseRequest, userEmail);
            if(saved == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to add expense");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Expense added with id: " + saved.getExpenseId());
        }catch(Exception e){
            System.out.println("Exception addExpense: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception occured");
        }
    }
}
