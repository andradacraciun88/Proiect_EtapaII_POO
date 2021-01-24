package output;

import input.ConsumersInputData;

public final class ConsumerOutputData {
    private int id;
    private boolean isBankrupt;

    public ConsumerOutputData(ConsumersInputData consumersInputData) {
        this.id = consumersInputData.getId();
        this.isBankrupt = consumersInputData.isBankruptcy();
        this.budget = consumersInputData.getInitialBudget();
    }

    /**
     *
     * @return isBankrupt
     */
    public boolean getisBankrupt() {
        return isBankrupt;
    }

    /**
     * @param isBankrupt
     */
    public void setisBankrupt(boolean isBankrupt) {
        this.isBankrupt = isBankrupt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    private int budget;
}
