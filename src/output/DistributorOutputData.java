package output;

import input.DistributorsInputData;
import java.util.ArrayList;
import java.util.List;

public final class DistributorOutputData {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private String producerStrategy;
    private boolean isBankrupt;
    private List<ContractOutputData> contracts = new ArrayList<>();

    public DistributorOutputData(DistributorsInputData distributorsInput) {
        this.id = distributorsInput.getId();
        this.energyNeededKW = distributorsInput.getEnergyNeededKW();
        this.contractCost = distributorsInput.getContractPrice();
        this.budget = distributorsInput.getInitialBudget();
        this.producerStrategy = distributorsInput.getProducerStrategy();
        this.isBankrupt = distributorsInput.isBanckrupty();
    }


    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return isBankrupt
     */
    public boolean getisBankrupt() {
        return isBankrupt;
    }

    /**
     * @param bankrupt
     */
    public void setisBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }
    public List<ContractOutputData> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractOutputData> contracts) {
        this.contracts = contracts;
    }
}
