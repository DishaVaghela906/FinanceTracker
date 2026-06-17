package SpringBoot.Personal_Finance_Tracker.model.dto;

public class CategoryExpenseDto {
    private String category;
    private Double amount;

    public CategoryExpenseDto() {}

    public CategoryExpenseDto(String category, Double amount) {
        this.category = category;
        this.amount = amount != null ? amount : 0.0;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount != null ? amount : 0.0;
    }

    @Override
    public String toString() {
        return "CategoryExpenseDto{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
