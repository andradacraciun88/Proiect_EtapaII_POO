package entities;

import input.Input;

public final class Play {
    private Simulation simulation = new Simulation();

    /** call the methods to play the rounds */
    public void turns (Input input) {
        simulation.monthlyStats(input, -1);
        simulation.putRenewableValue(input);
        simulation.sortListProducers(input);
        simulation.distributorsChooseProducers(input);
        simulation.sortListProducers(input);
        simulation.distributorsCalculateCost(input);
        simulation.priceDistributor(input);
        simulation.consumatorGetSalary(input);
        simulation.chooseContract(input);
        simulation.deleteContracts(input);
        simulation.payRate(input);
        simulation.paySpendingsDistributors(input);
        simulation.deleteBankrupt(input);
        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            simulation.update(input, i);
            simulation.priceDistributor(input);
            simulation.consumatorGetSalary(input);
            simulation.chooseContract(input);
            simulation.deleteContracts(input);
            simulation.payRate(input);
            simulation.paySpendingsDistributors(input);
            simulation.updateProducers(input, i);
            simulation.deleteIfNotified(input);
            simulation.monthlyStats(input, i);
            simulation.deleteBankrupt(input);
            simulation.sortListProducers(input);
            simulation.putDistributorsInList(input, i + 1);
        }
        for (int i = 0; i < input.getInitialData().getProducers().size(); i++) {
            input.getInitialData().getProducers().get(i).getMonthlyStats().remove(0);
        }
    }
}
