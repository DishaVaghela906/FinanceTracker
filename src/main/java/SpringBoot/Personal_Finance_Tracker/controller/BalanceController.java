package SpringBoot.Personal_Finance_Tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.Personal_Finance_Tracker.model.dto.BalanceResponse;
import SpringBoot.Personal_Finance_Tracker.service.ExpenseService;
import SpringBoot.Personal_Finance_Tracker.service.IncomeService;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public ResponseEntity<BalanceResponse> getBalance(){
        try{
            System.out.println("Method getBalance: ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            String userEmail = authentication.getName();
            System.out.println("userEmail : " + userEmail);

            Double totalIncome = incomeService.getTotalIncomeForUser(userEmail);
            Double totalExpense = expenseService.getTotalExpenseForUser(userEmail);

            BalanceResponse balanceResponse = new BalanceResponse(totalIncome, totalExpense);
            System.out.println("Balance Response: " + balanceResponse);
            
            return ResponseEntity.ok(balanceResponse);
        }catch(Exception e){
            System.out.println("Exception getBalance: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
