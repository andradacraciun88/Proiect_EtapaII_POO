package output;

import entities.Contract;

public final class ContractOutputData {
    private int consumerId;
    private int price;
    private int remainedContractMonths;

    /**
     * @return remainedContractMonths
     */
    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public ContractOutputData(Contract contract) {
        this.consumerId = contract.getConsumer().getId();
        this.price = contract.getContractPrice();
        this.remainedContractMonths = contract.getValidity();

    }
}
