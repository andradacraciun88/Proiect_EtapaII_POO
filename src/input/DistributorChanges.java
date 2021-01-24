package input;

public final class DistributorChanges {
    private int id;
    private int infrastructureCost;
    private int productionCost;

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    public void setInfrastructureCost(int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DistributorChanges{"
                +
                "id=" + id
                +
                ", infrastructureCost="
                + infrastructureCost
                +
                '}';
    }
}
