package SpringBoot.Personal_Finance_Tracker.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot.Personal_Finance_Tracker.model.dto.CategoryExpenseDto;
import SpringBoot.Personal_Finance_Tracker.model.dto.MonthlyReportResponse;
import SpringBoot.Personal_Finance_Tracker.model.entity.UserEntity;
import SpringBoot.Personal_Finance_Tracker.repository.ExpenseRepository;
import SpringBoot.Personal_Finance_Tracker.repository.IncomeRepository;

@Service
public class ReportService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    public MonthlyReportResponse getMonthlyReport(String userEmail, Integer year, Integer month) {
        try {
            System.out.println("Method getMonthlyReport: year=" + year + ", month=" + month);
            
            UserEntity user = userService.getUserbyUserEmail(userEmail);
            if (user == null) {
                System.out.println("User not found");
                return null;
            }

            // Create date range for the given month
            YearMonth yearMonth = YearMonth.of(year, month);
            LocalDate firstDay = yearMonth.atDay(1);
            LocalDate lastDay = yearMonth.atEndOfMonth();
            
            Date startDate = Date.valueOf(firstDay);
            Date endDate = Date.valueOf(lastDay);

            System.out.println("Date range: " + startDate + " to " + endDate);

            // Get total income and expenses for the month
            Double totalIncome = incomeRepository.getTotalIncomeForUserByDateRange(user, startDate, endDate);
            Double totalExpense = expenseRepository.getTotalExpenseForUserByDateRange(user, startDate, endDate);

            System.out.println("Total Income: " + totalIncome + ", Total Expense: " + totalExpense);

            // Get expense breakdown by category
            List<CategoryExpenseDto> categoryExpenses = expenseRepository.getExpenseByCategotyForDateRange(user, startDate, endDate);
            
            // Calculate percentages
            Map<String, Double> expenseBreakdown = new HashMap<>();
            if (totalExpense > 0) {
                for (CategoryExpenseDto categoryExpense : categoryExpenses) {
                    double percentage = (categoryExpense.getAmount() / totalExpense) * 100;
                    expenseBreakdown.put(categoryExpense.getCategory(), Math.round(percentage * 100.0) / 100.0);
                }
            }

            System.out.println("Expense Breakdown: " + expenseBreakdown);

            return new MonthlyReportResponse(year, month, totalIncome, totalExpense, expenseBreakdown);

        } catch (Exception e) {
            System.out.println("Exception getMonthlyReport: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
