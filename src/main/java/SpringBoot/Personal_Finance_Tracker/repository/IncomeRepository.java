package SpringBoot.Personal_Finance_Tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBoot.Personal_Finance_Tracker.model.entity.Income;
import java.util.List;


@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{

    public Income findByIncomeId(Long incomeId);
    
}
