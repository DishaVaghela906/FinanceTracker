package SpringBoot.Personal_Finance_Tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SpringBoot.Personal_Finance_Tracker.model.entity.Income;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;
import java.sql.Date;
import java.util.List;


@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{

    public Income findByIncomeId(Long incomeId);

    public List<Income> findAllByUser(UserEntity user);

    @Query("SELECT COALESCE(SUM(i.incomeAmount), 0) FROM Income i WHERE i.user = :user")
    Double getTotalIncomeForUser(@Param("user") UserEntity user);

    @Query("SELECT COALESCE(SUM(i.incomeAmount), 0) FROM Income i WHERE i.user = :user AND i.incomeDate BETWEEN :startDate AND :endDate")
    Double getTotalIncomeForUserByDateRange(@Param("user") UserEntity user, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
}
