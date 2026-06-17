package SpringBoot.Personal_Finance_Tracker.controller;

import java.util.List;

import javax.swing.plaf.synth.SynthDesktopIconUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.Personal_Finance_Tracker.model.dto.ExpenseRequest;
import SpringBoot.Personal_Finance_Tracker.model.dto.ExpenseResponse;
import SpringBoot.Personal_Finance_Tracker.model.dto.ResponseWrapper;
import SpringBoot.Personal_Finance_Tracker.model.entity.Expense;
import SpringBoot.Personal_Finance_Tracker.service.ExpenseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<ExpenseResponse>>> getAllExpense(){
        try{
            System.out.println("Method getAllExpense: ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("Unauthorized"));
            }
            String userEmail = authentication.getName();
            System.out.println("userEmail : " + userEmail);
            List<ExpenseResponse> expenses = expenseService.getAllExpenseForUser(userEmail);
            if(expenses == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error("Unable to retrieve expenses"));
            }
            return ResponseEntity.ok(ResponseWrapper.success(expenses, "Expenses retrieved successfully"));
        }catch(Exception e){
            System.out.println("Exception getAllExpense: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseWrapper.error("Internal server error"));
        }
    }
    
    @PostMapping
    public ResponseEntity<ResponseWrapper<String>> addExpense(@RequestBody @Valid ExpenseRequest expenseRequest){
        try{
            System.out.println("Method addExpense: ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() ==null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("Unauthorized"));
            }
            String userEmail = authentication.getName();
            Expense saved = expenseService.addExpenseForUser(expenseRequest, userEmail);
            if(saved == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error("Unable to add expense"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success("Expense added with id: " + saved.getExpenseId()));
        }catch(Exception e){
            System.out.println("Exception addExpense: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseWrapper.error("Exception occured"));
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseWrapper<String>> updateExpense(@RequestBody @Valid ExpenseRequest expenseRequest, @PathVariable Long id){
        try{
            System.out.println("Method updateExpense API: ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("Unauthorized"));
            }
            String userEmail = authentication.getName();
            System.out.println("userEmail: " + userEmail);
            Expense updated = expenseService.updateExpenseForUser(id,expenseRequest,userEmail);
            if(updated == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error("Unable to update expense"));
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ResponseWrapper.success("Expense updated with id : " + id));
        }catch(Exception e){
            System.out.println("Exception updateExpense: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseWrapper.error("Exception in updateExpense"));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteExpense(@PathVariable Long id){
        try{
            System.out.println("Method deleteIncome API: ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("Unauthorized"));
            }
            String userEmail = authentication.getName();
            System.out.println("userEmail: " + userEmail);
            boolean deleted = expenseService.delteExpenseForUser(id, userEmail);
            if(!deleted){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error("Unable to delete expense"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success("Expense delete with id : " + id));
        }catch(Exception e){
            System.out.println("Exception deleteExpense: "+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseWrapper.error("Exception in deleteExpense"));
        }
    }
}
