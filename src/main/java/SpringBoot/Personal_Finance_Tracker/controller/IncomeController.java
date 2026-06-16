package SpringBoot.Personal_Finance_Tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.Personal_Finance_Tracker.model.dto.IncomeRequest;
import SpringBoot.Personal_Finance_Tracker.model.entity.Income;
import SpringBoot.Personal_Finance_Tracker.service.IncomeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/income")
public class IncomeController {
    
    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<String> addIncome(@RequestBody @Valid IncomeRequest incomeRequest){
        try{
            System.out.println("Method addIncome: ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
            String userEmail = authentication.getName();
            Income saved = incomeService.addIncomeForUser(incomeRequest, userEmail);
            if(saved == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to save income");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Income added with id: " + saved.getIncomeId());
        }catch(Exception e){
            System.out.println("Exception addIncome: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception in addIncome");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateIncome(@RequestBody @Valid IncomeRequest incomeRequest, @PathVariable Long id){
        try{
            System.out.println("Method updateIncome api : ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || authentication.getName() == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
            String userEmail  = authentication.getName();
            System.out.println("userEmail : " + userEmail);
            Income updated = incomeService.updateIncome(id, incomeRequest, userEmail);
            if(updated == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to update income");
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Income updated with id : " + id);
        }catch(Exception e){
            System.out.println("Exception updateIncome: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception in updateIncome");
        }
    }    
}