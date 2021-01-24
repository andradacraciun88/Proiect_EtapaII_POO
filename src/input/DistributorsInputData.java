package input;

import entities.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class DistributorsInputData implements Observer {
    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int initialProductionCost;
    private int energyNeededKW;
    private int contractPrice;
    private int profit;
    private boolean banckrupty;
    private double cost;
    private int productionCost;
    private int updated;
    private final double val = 0.2;
    private final double var = 10;

    public int getUpdated() {
        return updated;
    }

    /* list with the producers of a distributor */
    private List<ProducersInputData> producersOfaDistributor = new ArrayList<>();

    /* list with the contracts of a distributor */
    private List<Contract> contracts = new ArrayList<>();

    public List<ProducersInputData> getProducersOfaDistributor() {
        return producersOfaDistributor;
    }
    public void setProducersOfaDistributor(List<ProducersInputData> producersOfaDistributor) {
        this.producersOfaDistributor = producersOfaDistributor;
    }
    private String producerStrategy;

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

    public boolean isBanckrupty() {
        return banckrupty;
    }

    public void setBanckrupty(boolean banckrupty) {
        this.banckrupty = banckrupty;
    }

    public int getProfit() {
        return profit;
    }
    public void setProfit(int profit) {
        this.profit = profit;
    }
    public int getContractPrice() {
        return contractPrice;
    }
    public void setContractPrice(int contractPrice) {
        this.contractPrice = contractPrice;
    }
    public List<Contract> getContracts() {
        return contracts;
    }
    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
    public void setInitialProductionCost(int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }
    public void setInitialInfrastructureCost(int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }
    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }
    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getInitialProductionCost() {
        return initialProductionCost;
    }
    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }
    public int getInitialBudget() {
        return initialBudget;
    }
    public int getContractLength() {
        return contractLength;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DistributorsInputData{"
                +
                "id="
                + id
                +
                ", contractLength="
                + contractLength
                +
                ", initialBudget="
                + initialBudget
                +
                ", initialInfrastructureCost="
                + initialInfrastructureCost
                +
                ", initialProductionCost="
                + initialProductionCost
                +
                ", energyNeededKW="
                + energyNeededKW
                +
                ", contractPrice="
                + contractPrice
                +
                ", profit="
                + profit
                +
                ", banckrupty="
                + banckrupty
                +
                ", cost="
                + cost
                +
                ", productionCost="
                + productionCost
                +
                ", updated="
                + updated
                +
                ", producersOfaDistributor="
                + producersOfaDistributor
                +
                ", producerStrategy='"
                + producerStrategy + '\''
                +
                ", contracts="
                + contracts
                +
                '}';
    }

    /**
     * calculate profit
     */
    public void findProfit() {
        setProfit((int) Math.round(Math.floor(val * productionCost)));
    }

    /** calculate the price of the contract that the distributor offers */
    public void calculateContractPrice() {
        /* if the distributor has consumers */
        if (contracts.size() == 0 || contracts == null) {
            findProfit();
            setContractPrice(initialInfrastructureCost + productionCost + profit);
        } else {
            /* if the distributors has not consumers */
            findProfit();
            setContractPrice((int) Math.round(Math.
                    floor(initialInfrastructureCost / contracts.size()) + productionCost + profit));
        }
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * calculate the cost production for every
     * producer of a distributor
     */
    public void findCost() {
        cost = 0;
        for (int i = 0; i < getProducersOfaDistributor().size(); i++) {
            cost += (getProducersOfaDistributor().get(i).
                    getPriceKW() * getProducersOfaDistributor().
                    get(i).getEnergyPerDistributor());
            setCost(cost);
        }
    }
    public double getCost() {
        return cost;
    }

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    /**
     * calculate the final production cost
     */
    public void findProductionCost() {
        setProductionCost((int) Math.round(Math.floor(cost / var)));
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setUpdated(1);
    }
    public void setUpdated(int updated) {
        this.updated = updated;
    }
    public int getProductionCost() {
        return productionCost;
    }
}
