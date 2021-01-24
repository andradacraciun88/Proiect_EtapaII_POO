package output;

import java.util.ArrayList;
import java.util.List;

public final class Output {
    private List<ConsumerOutputData> consumers = new ArrayList<>();
    private List<DistributorOutputData> distributors = new ArrayList<>();
    private List<ProducerOutputData> energyProducers = new ArrayList<>();

    public List<ProducerOutputData> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(List<ProducerOutputData> energyProducers) {
        this.energyProducers = energyProducers;
    }

    public List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public void setDistributors(List<DistributorOutputData> distributors) {
        this.distributors = distributors;
    }

    public List<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<ConsumerOutputData> consumers) {
        this.consumers = consumers;
    }

}
