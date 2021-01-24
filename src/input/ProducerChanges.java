package input;

public final class ProducerChanges {
    private int id;
    private int energyPerDistributor;

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProducerChanges{"
                +
                "id="
                + id
                +
                ", energyPerDistributor="
                + energyPerDistributor
                +
                '}';
    }
}
