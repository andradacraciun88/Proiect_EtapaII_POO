package input;

import entities.Contract;

public final class ConsumersInputData {
    private int id;
    private int initialBudget;
    private int monthlyIncome;
    /* his distributor id */
    private int distributorId;
    /* the price of his curent contract*/
    private long priceContract;
    /* his current contract */
    private Contract currentContract;

    public Contract getOldContract() {
        return oldContract;
    }

    public void setOldContract(Contract oldContract) {
        this.oldContract = oldContract;
    }

    private Contract oldContract;
    private boolean debt;
    private boolean isBankruptcy;

    public Contract getCurrentContract() {
        return currentContract;
    }
    public void setCurrentContract(Contract currentContract) {
        this.currentContract = currentContract;
    }

    public boolean isBankruptcy() {
        return isBankruptcy;
    }

    public void setBankruptcy(boolean bankruptcy) {
        isBankruptcy = bankruptcy;
    }

    public boolean isDebt() {
        return debt;
    }
    public void setDebt(boolean debt) {
        this.debt = debt;
    }
    public int getDistributorId() {
        return distributorId;
    }
    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }
    public long getPriceContract() {
        return priceContract;
    }
    public void setPriceContract(long priceContract) {
        this.priceContract = priceContract;
    }
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
        return "ConsumersInputData{"
                +
                "id="
                + id
                +
                ", initialBudget="
                + initialBudget
                +
                ", monthlyIncome="
                + monthlyIncome
                +
                ", distributorId="
                + distributorId
                +
                ", priceContract="
                + priceContract
                +
                ", currentContract="
                + currentContract
                +
                ", oldContract="
                + oldContract
                +
                ", debt="
                + debt
                +
                ", isBankruptcy="
                + isBankruptcy
                +
                '}';
    }
}
