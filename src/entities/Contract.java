package entities;

import input.ConsumersInputData;
import input.DistributorsInputData;

public final class Contract {
    private int contractPrice;
    private DistributorsInputData distributor;
    private ConsumersInputData consumer;
    private int validity;

    public final int getValidity() {
        return validity;
    }
    public void setValidity(int validity) {
        this.validity = validity;
    }
    public ConsumersInputData getConsumer() {
        return consumer;
    }
    public void setConsumer(ConsumersInputData consumer) {
        this.consumer = consumer;
    }
    public DistributorsInputData getDistributor() {
        return distributor;
    }
    public void setDistributor(DistributorsInputData distributor) {
        this.distributor = distributor;
    }
    public int getContractPrice() {
        return contractPrice;
    }
    public void setContractPrice(int contractPrice) {
        this.contractPrice = contractPrice;
    }
}
