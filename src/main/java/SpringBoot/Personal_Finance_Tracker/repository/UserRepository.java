package SpringBoot.Personal_Finance_Tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    public UserEntity findByUserEmail(String userEmail);

    public boolean existsByUserEmail(String userEmail);
}
