package input;

import java.util.List;

public final class MonthlyUpdate {
    private List<ConsumersInputData> newConsumers;
    private List<DistributorChanges> distributorChanges;
    private List<ProducerChanges> producerChanges;

    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(List<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(List<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public List<ConsumersInputData> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(List<ConsumersInputData> newConsumers) {
        this.newConsumers = newConsumers;
    }

    @Override
    public String toString() {
        return "MonthlyUpdate{"
                +
                "newConsumers="
                + newConsumers
                +
                ", distributorChanges="
                + distributorChanges
                +
                ", producerChanges="
                + producerChanges
                +
                '}';
    }
}
