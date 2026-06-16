package SpringBoot.Personal_Finance_Tracker.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import SpringBoot.Personal_Finance_Tracker.model.dto.IncomeRequest;
import SpringBoot.Personal_Finance_Tracker.model.entity.Income;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;
import SpringBoot.Personal_Finance_Tracker.repository.IncomeRepository;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private UserService userService;

	public Income addIncomeForUser(IncomeRequest incomeRequest, String userEmail){
		try{
            System.out.println("Method addIncomeForUser: ");
			UserEntity user = userService.getUserbyUserEmail(userEmail);
			if(user == null){
				return null;
			}
			Income income = new Income();
			income.setIncomeAmount(incomeRequest.getIncomeAmount());
			income.setIncomeSource(incomeRequest.getIncomeSource());
			income.setIncomeDate(Date.valueOf(incomeRequest.getIncomeDate()));
			income.setUser(user);
			return incomeRepository.save(income);
		}catch(Exception e){
			System.out.println("Exception addIncomeForUser: " + e.getMessage());
			return null;
		}
	}

	public Income updateIncome(Long incomeId, IncomeRequest incomeRequest, String userEmail){
		try{
			System.out.println("Method updateIncome: ");
			UserEntity user = userService.getUserbyUserEmail(userEmail);
			if(user == null){
				System.out.println("User doesn't exists");
				return null;
			}else{
				Income income = incomeRepository.findByIncomeId(incomeId);
				System.out.println("user of income: " + income.getUser());
				System.out.println("userEmail of income user :" + income.getUser().getUserEmail());
				System.out.println("userEmail of user : " + user.getUserEmail());
				if(income.getUser().getUserEmail().equals(user.getUserEmail())){
					income.setIncomeAmount(incomeRequest.getIncomeAmount());
					income.setIncomeSource(incomeRequest.getIncomeSource());
					income.setIncomeDate(Date.valueOf(incomeRequest.getIncomeDate()));
					return incomeRepository.save(income);
				}
			}
			return null;
		}catch(Exception e){
			System.out.println("Exception updateIncome: " + e.getMessage());
			return null;
		}
	}

	public boolean deleteIncomeForUser(Long incomeId, String userEmail){
		try{
			System.out.println("Method deleteIncomeForUser: ");
			UserEntity user = userService.getUserbyUserEmail(userEmail);
			if(user == null){
				return false;
			}
			Income income = incomeRepository.findByIncomeId(incomeId);
			if(income == null){
				return false;
			}
			if(income.getUser().getUserEmail().equals(user.getUserEmail())){
				incomeRepository.delete(income);
				return true;
			}
			return false;
		}catch(Exception e){
			System.out.println("Exception deleteIncome: " + e.getMessage());
			return false;
		}
	}
}
