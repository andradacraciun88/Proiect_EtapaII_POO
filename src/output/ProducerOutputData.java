package output;

import input.ProducersInputData;
import java.util.ArrayList;
import java.util.List;

public final class ProducerOutputData {
    private int id;
    private int maxDistributors;
    private double priceKW;
    private String energyType;
    private int energyPerDistributor;
    private List<MonthlyStatsOut> monthlyStatOuts = new ArrayList<>();

    public ProducerOutputData(ProducersInputData producersInputData) {
        this.id = producersInputData.getId();
        this.maxDistributors = producersInputData.getMaxDistributors();
        this.priceKW = producersInputData.getPriceKW();
        this.energyType = producersInputData.getEnergyType();
        this.energyPerDistributor = producersInputData.getEnergyPerDistributor();
    }
    public List<MonthlyStatsOut> getMonthlyStats() {
        return monthlyStatOuts;
    }

    public void setMonthlyStats(List<MonthlyStatsOut> monthlyStatOuts) {
        this.monthlyStatOuts = monthlyStatOuts;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
