package input;

public final class NewConsumers {
    private int id;
    private int initialBudget;
    private int monthlyIncome;

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "NewConsumer{"
                +
                "id=" + id
                +
                ", initialBudget=" + initialBudget
                +
                ", monthlyIncome=" + monthlyIncome
                +
                '}';
    }
}
