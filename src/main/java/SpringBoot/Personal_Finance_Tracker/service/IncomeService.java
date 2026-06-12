package SpringBoot.Personal_Finance_Tracker.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
}
