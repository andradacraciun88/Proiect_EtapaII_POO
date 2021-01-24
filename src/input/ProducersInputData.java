package input;

import entities.MonthlyStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public final class ProducersInputData extends Observable {

    private int id;
    private String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private int renewableValue;
    private List<MonthlyStats> monthlyStats = new ArrayList<>();

    /* list with the distributors of a producer */
    private List<DistributorsInputData> distributorsOfaProducer = new ArrayList<>();

    public List<DistributorsInputData> getDistributorsOfaProducer() {
        return distributorsOfaProducer;
    }

    public void setDistributorsOfaProducer(List<DistributorsInputData> distributorsOfaProducer) {
        this.distributorsOfaProducer = distributorsOfaProducer;
    }

    public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setRenewableValue(int renewableValue) {
        this.renewableValue = renewableValue;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProducersInputData{"
                +
                "id="
                + id
                +
                ", energyType='"
                + energyType
                + '\''
                +
                ", maxDistributors="
                + maxDistributors
                +
                ", priceKW="
                + priceKW
                +
                ", energyPerDistributor="
                + energyPerDistributor
                +
                '}';
    }

    /**
     */
    public void setRenewableValue() {
        if (getEnergyType().equals("WIND")) {
            this.renewableValue = 0;
        } else
        if (getEnergyType().equals("SOLAR")) {
            this.renewableValue = 0;
        } else
        if (getEnergyType().equals("HYDRO")) {
            this.renewableValue = 0;
        } else
        if (getEnergyType().equals("COAL")) {
            this.renewableValue = 1;
        } else
        if (getEnergyType().equals("NUCLEAR")) {
            this.renewableValue = 1;
        }
    }

    /** Observer method: producers notify their distributors
     */
    public void makeChange() {
        setChanged();
        notifyObservers();
    }

    public int getRenewableValue() {
        return renewableValue;
    }
}
