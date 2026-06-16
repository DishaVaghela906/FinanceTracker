package SpringBoot.Personal_Finance_Tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBoot.Personal_Finance_Tracker.model.entity.Expense;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    
    public Expense findByExpenseId(Long expenseId);

    public List<Expense> findAllByUser(UserEntity user);
}
