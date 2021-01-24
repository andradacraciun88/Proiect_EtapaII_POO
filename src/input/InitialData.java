package input;


import java.util.List;

public final class InitialData {
    private List<ConsumersInputData> consumers;
    private List<DistributorsInputData> distributors;
    private List<ProducersInputData> producers;

    public List<ProducersInputData> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducersInputData> producers) {
        this.producers = producers;
    }

    public void setDistributors(List<DistributorsInputData> distributors) {
        this.distributors = distributors;
    }

    public void setConsumers(List<ConsumersInputData> consumers) {
        this.consumers = consumers;
    }

    public List<DistributorsInputData> getDistributors() {
        return distributors;
    }

    public List<ConsumersInputData> getConsumers() {
        return consumers;
    }

    @Override
    public String toString() {
        return "InitialData{"
                +
                "consumers="
                + consumers
                +
                ", distributors="
                + distributors
                +
                ", producers="
                + producers
                +
                '}';
    }
}
