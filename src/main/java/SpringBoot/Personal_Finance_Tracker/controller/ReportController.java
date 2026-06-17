package SpringBoot.Personal_Finance_Tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringBoot.Personal_Finance_Tracker.model.dto.MonthlyReportResponse;
import SpringBoot.Personal_Finance_Tracker.model.dto.ResponseWrapper;
import SpringBoot.Personal_Finance_Tracker.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/monthly")
    public ResponseEntity<ResponseWrapper<MonthlyReportResponse>> getMonthlyReport(
            @RequestParam(defaultValue = "2026") Integer year,
            @RequestParam(defaultValue = "6") Integer month) {
        try {
            System.out.println("Method getMonthlyReport: year=" + year + ", month=" + month);
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getName() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.error("Unauthorized"));
            }
            
            String userEmail = authentication.getName();
            System.out.println("userEmail : " + userEmail);

            // Validate month
            if (month < 1 || month > 12) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error("Invalid month value"));
            }

            // Validate year
            if (year < 1900 || year > 2100) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error("Invalid year value"));
            }

            MonthlyReportResponse report = reportService.getMonthlyReport(userEmail, year, month);
            
            if (report == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error("Unable to generate report"));
            }

            System.out.println("Report Response: " + report);
            return ResponseEntity.ok(ResponseWrapper.success(report, "Monthly report retrieved successfully"));

        } catch (Exception e) {
            System.out.println("Exception getMonthlyReport: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseWrapper.error("Unable to retrieve monthly report"));
        }
    }
}
