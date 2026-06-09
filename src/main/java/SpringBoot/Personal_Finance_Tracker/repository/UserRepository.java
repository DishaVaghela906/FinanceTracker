package SpringBoot.Personal_Finance_Tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringBoot.Personal_Finance_Tracker.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
