package SpringBoot.Personal_Finance_Tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBoot.Personal_Finance_Tracker.model.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    
    public Expense findByExpenseId(Long expenseId);
}
