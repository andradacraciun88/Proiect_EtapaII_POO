package entities;

import sort.SortStrategy;
import sort.SortStrategyFactory;
import input.DistributorsInputData;
import input.Input;
import input.ProducersInputData;

import java.util.*;
import java.util.stream.Collectors;

public class Simulation {

    private final double val = 1.2;
    /**
    * we read from input the changes that takes place for
    consumers and distributor in the beginning of the month
     */
    public void update(Input input, int i) {
        if (input.getMonthlyUpdates().get(i).getNewConsumers().size() != 0) {
            for (int j = 0; j < input.getMonthlyUpdates().get(i).getNewConsumers().size(); j++) {
                input.getInitialData().getConsumers().add(input.
                        getMonthlyUpdates().get(i).getNewConsumers().get(j));
            }
        }
        if (input.getMonthlyUpdates().get(i).getDistributorChanges().size() != 0) {
            for (int k = 0; k < input.getInitialData().getDistributors().size(); k++) {
                for (int j = 0; j < input.getMonthlyUpdates().get(i).getDistributorChanges().
                        size(); j++) {
                    if (input.getInitialData().getDistributors().get(k).getId() == input.
                            getMonthlyUpdates().get(i).getDistributorChanges().
                                    get(j).getId()) {
                        /* when we find the id that is going to pe updated */
                        input.getInitialData().getDistributors().get(k).
                                setInitialInfrastructureCost(input
                                .getMonthlyUpdates().get(i).getDistributorChanges().get(j).
                                        getInfrastructureCost());
                        input.getInitialData().getDistributors().get(k).
                                setInitialProductionCost(input
                                .getMonthlyUpdates().get(i).getDistributorChanges().get(j).
                                        getProductionCost());
                    }
                }
            }
        }
    }

    /**
     * we return the distributor that has the minimum price
     */
    public DistributorsInputData bestPrice(Input input) {
        List<DistributorsInputData> difFromBanckrupt = input.getInitialData().
                getDistributors()
                .stream()
                .filter(c -> !c.isBanckrupty())
                .collect(Collectors.toList());

        DistributorsInputData minPriceDistrib = difFromBanckrupt
                .stream()
                .min(Comparator.comparing(DistributorsInputData::getContractPrice))
                .orElseThrow(NoSuchElementException::new);
        return minPriceDistrib;
    }

    /** we read from input the changes that takes place
    * for producers in the beginning of the month
     * */
    public void updateProducers(Input input, int i) {
        if (input.getMonthlyUpdates().get(i).getProducerChanges().size() != 0) {
            for (int j = 0; j < input.getInitialData().getProducers().size(); j++) {
                for (int k = 0; k < input.getMonthlyUpdates().get(i).
                        getProducerChanges().size(); k++) {
                    if (input.getInitialData().getProducers().get(j).getId() == input.
                            getMonthlyUpdates().get(i).getProducerChanges().
                                    get(k).getId()) {
                        input.getInitialData().getProducers().get(j).
                                setEnergyPerDistributor(
                                input.getMonthlyUpdates().get(i).getProducerChanges()
                                        .get(k).getEnergyPerDistributor());
                        /* notify the distributors */
                        input.getInitialData().getProducers().get(j).makeChange();
                    }
                }
            }
        }
    }

    /** for each distributor we calculate the price of his contract */
    public void priceDistributor(Input input) {
        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            input.getInitialData().getDistributors().get(i).calculateContractPrice();
        }
    }

    /** consumers gets salary it they are not in bankruptcy */
    public void consumatorGetSalary(Input input) {
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            if (!input.getInitialData().getConsumers().get(i).isBankruptcy()) {
                input.getInitialData().getConsumers().get(i).setInitialBudget(input.
                        getInitialData().getConsumers().get(i).getInitialBudget() + input.
                        getInitialData().getConsumers().get(i).
                                        getMonthlyIncome());
            }
        }
    }
    /** consumers choose contracts */
    public void chooseContract(Input input) {
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            DistributorsInputData distributor = bestPrice(input);
            if (input.getInitialData().getConsumers().get(i).getCurrentContract() == null || input.
                    getInitialData().getConsumers().get(i).getCurrentContract().
                            getValidity() == 0
                            && (!input.getInitialData().getConsumers().get(i).isBankruptcy())) {
                Contract contract = new Contract();
                contract.setContractPrice(distributor.getContractPrice());
                contract.setDistributor(distributor);
                contract.setConsumer(input.getInitialData().getConsumers().get(i));
                contract.setValidity(distributor.getContractLength());
                input.getInitialData().getConsumers().get(i).setCurrentContract(contract);
                distributor.getContracts().add(contract);
            }
        }
    }
    /** consumers pay rate */
    public void payRate(Input input) {
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            if (!input.getInitialData().getConsumers().get(i).isBankruptcy()) {
                /* if they have debt */
                if (input.getInitialData().getConsumers().get(i).getOldContract() != null) {
                    /* if they have debt and can pay */
                    if (input.getInitialData().getConsumers().get(i).getInitialBudget() >= input.
                            getInitialData().getConsumers().get(i).getOldContract().
                                    getContractPrice() * val + input.
                            getInitialData().getConsumers().get(i).
                                            getCurrentContract().getContractPrice()) {
                        input.getInitialData().getConsumers().get(i).setInitialBudget(
                                (int) (input.getInitialData().getConsumers().get(i).
                                        getInitialBudget() - (input.getInitialData().getConsumers().
                                        get(i).getOldContract().
                                        getContractPrice() * val + input.getInitialData().
                                        getConsumers().get(i).getCurrentContract().
                                        getContractPrice())));
                        input.getInitialData().getConsumers().get(i).
                                getCurrentContract().
                                setValidity(input.getInitialData().
                                        getConsumers().get(i).
                                        getCurrentContract().
                                        getValidity() - 1);
                        input.getInitialData().getConsumers().get(i).setOldContract(null);
                        input.getInitialData().getConsumers().get(i).setDebt(false);
                        /* if they have debt and can not pay */
                    } else {
                        /* if they get bankrupt */
                        input.getInitialData().getConsumers().get(i).setBankruptcy(true);
                    }
                    /* if they have not debt */
                } else {
                    /* if they have not debt and they can pay */
                    if (input.getInitialData().getConsumers().get(i).getInitialBudget() >= input.
                            getInitialData().getConsumers().get(i).getCurrentContract().
                            getContractPrice()) {
                        input.getInitialData().getConsumers().get(i).
                                setInitialBudget(input.getInitialData().
                                getConsumers().get(i).getInitialBudget() - input.getInitialData().
                                getConsumers().
                                get(i).getCurrentContract().getContractPrice());
                        input.getInitialData().getConsumers().get(i).
                                getCurrentContract().
                                setValidity(input.getInitialData().
                                        getConsumers().get(i).
                                        getCurrentContract().getValidity() - 1);
                        /* if they have not debt and they can not pay */
                    } else {
                        input.getInitialData().getConsumers().get(i).setDebt(true);
                        input.getInitialData().getConsumers().get(i).
                                setOldContract(input.getInitialData().
                                getConsumers().get(i).getCurrentContract());
                        input.getInitialData().getConsumers().get(i).getCurrentContract().
                                setValidity(input.getInitialData().getConsumers().get(i).
                                        getCurrentContract().getValidity() - 1);
                    }
                }
            }
        }
    }
    /** distributors pay the costs needed */
    public void paySpendingsDistributors(Input input) {
        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            if (!input.getInitialData().getDistributors().get(i).isBanckrupty()) {
                if (input.getInitialData().getDistributors().get(i).getInitialBudget() < 0) {
                    input.getInitialData().getDistributors().get(i).setBanckrupty(true);
                    for (int j = 0; j < input.getInitialData().
                            getConsumers().size(); j++) {
                        if (input.getInitialData().getConsumers().get(j).
                                getOldContract() != null && input.
                                getInitialData().getConsumers().get(j).
                                getOldContract().
                                getDistributor() == input.
                                getInitialData().getDistributors().get(i)) {
                            input.getInitialData().getConsumers().get(j).
                                    setOldContract(null);
                            input.getInitialData().getConsumers().get(i).
                                    setDebt(false);
                        }
                        /* if the consumer has a contract with a bankrupt distributor */
                        if (input.getInitialData().getConsumers().get(j).
                                getCurrentContract() != null && input.
                                getInitialData().getConsumers().get(j).getCurrentContract().
                                getDistributor() == input.
                                getInitialData().getDistributors().get(i)) {
                            input.getInitialData().getConsumers().get(j).
                                    setCurrentContract(null);
                        }
                    }
                } else {
                    input.getInitialData().getDistributors().get(i).setInitialBudget(
                            input.getInitialData().getDistributors().get(i).
                                    getInitialBudget() - input.
                                    getInitialData().getDistributors().get(i).
                                            getInitialInfrastructureCost() - input.
                                    getInitialData().getDistributors().get(i).
                                    getProductionCost() * input.
                                    getInitialData().getDistributors().get(i).
                                    getContracts().size());

                    /* add to the distributors the rate that the consumers pay */
                    for (int j = 0; j < input.getInitialData().getConsumers().size(); j++) {
                        if ((!input.getInitialData().getConsumers().get(j).isBankruptcy()) && input.
                                getInitialData().getConsumers().get(j).
                                        getOldContract() == null && input.
                                getInitialData().getConsumers().get(j).
                                getCurrentContract() != null && input.
                                getInitialData().getConsumers().get(j).
                                getCurrentContract().
                                        getDistributor() == input.
                                getInitialData().getDistributors().get(i)) {
                            input.getInitialData().getDistributors().get(i).
                                    setInitialBudget(input.
                                    getInitialData().getDistributors().get(i)
                                            .getInitialBudget() + input.getInitialData().
                                            getConsumers().
                                            get(j).getCurrentContract().getContractPrice());
                        }
                    }
                    if (input.getInitialData().getDistributors().get(i).
                            getInitialBudget() < 0) {
                        input.getInitialData().getDistributors().get(i).
                                setBanckrupty(true);
                    }
                }
            }
        }
    }
    /** delete the consumers that are bankrupt */
    public void deleteBankrupt(Input input) {
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            if (input.getInitialData().getConsumers().get(i).isBankruptcy()) {
                for (int j = 0; j < input.getInitialData().getDistributors().size(); j++) {
                    input.getInitialData().getDistributors().get(j).getContracts().
                            remove(input.getInitialData().getConsumers().get(i).
                                    getCurrentContract());
                }
            }
        }
    }
    /** delete contracts if they no longer have validity */
    public void deleteContracts(Input input) {
        for (DistributorsInputData distributor : input.getInitialData().
                getDistributors()) {
            distributor.getContracts().removeIf(contract1 ->
                    contract1.getValidity() == 0);
        }
    }
    /** set the renewableValue field from the input */
    public void putRenewableValue(Input input) {
        for (int i = 0; i < input.getInitialData().getProducers().size(); i++) {
            input.getInitialData().getProducers().get(i).setRenewableValue();
        }
    }

    /** sort the producers after id in ascending order */
    public void sortListProducers(Input input) {
        Collections.sort(input.getInitialData().
                getProducers(), new Comparator<ProducersInputData>() {
            @Override
            public int compare(ProducersInputData o1, ProducersInputData o2) {
                return o1.getId() - o2.getId();
            }
        });
    }
    /** distributors choose producers that they need */
    public void distributorsChooseProducers(Input input) {
        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {

            /* apply strategy pattern */
            SortStrategy strategyType = SortStrategyFactory.
                    createStrategy(input.getInitialData().getDistributors().get(i).
                            getProducerStrategy());
            List<ProducersInputData> sortedList = strategyType.sort(input);

            int k = 0;
            int energyNeeded = input.getInitialData().getDistributors().get(i).
                    getEnergyNeededKW();
            while (input.getInitialData().getDistributors().get(i).
                    getEnergyNeededKW() > 0) {

                if (sortedList.get(k).getDistributorsOfaProducer().size() < sortedList.get(k).
                        getMaxDistributors()) {
                    input.getInitialData().getDistributors().get(i).
                            getProducersOfaDistributor().
                            add(sortedList.get(k));
                    /* add the distributors in Observers list */
                    sortedList.get(k).addObserver(input.getInitialData().
                            getDistributors().get(i));
                    sortedList.get(k).getDistributorsOfaProducer().
                            add(input.getInitialData().
                            getDistributors().get(i));
                    input.getInitialData().getDistributors().get(i).
                            setEnergyNeededKW(input.
                            getInitialData().getDistributors().
                            get(i).getEnergyNeededKW() - sortedList.get(k).
                            getEnergyPerDistributor());
                }
                k++;
            }
            input.getInitialData().getDistributors().get(i).
                    setEnergyNeededKW(energyNeeded);
            input.getInitialData().getDistributors().get(i).findCost();
            input.getInitialData().getDistributors().get(i).findProductionCost();
            input.getInitialData().getDistributors().get(i).setUpdated(0);
        }
    }
    /** in case the distributor is notified we choose another producer */
    public void deleteIfNotified(Input input) {
        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            if (!input.getInitialData().getDistributors().get(i).isBanckrupty() && input.
                    getInitialData().getDistributors().get(i).getUpdated() == 1) {

                for (int l = 0; l < input.getInitialData().getDistributors().
                        get(i).getProducersOfaDistributor().size(); l++) {
                    input.getInitialData().getDistributors().get(i).
                            getProducersOfaDistributor().get(l).getDistributorsOfaProducer().
                            remove(input.getInitialData().getDistributors().get(i));
                    /* delete the distributor from observers list */
                    input.getInitialData().getDistributors().get(i).
                            getProducersOfaDistributor().get(l).
                            deleteObserver(input.getInitialData().getDistributors().get(i));
                }
                /* add the distributors in producers lists */
                for (int j = 0; j < input.getInitialData().getProducers().size(); j++) {
                    for (int k = 0; k < input.getInitialData().getProducers().get(j).
                            getDistributorsOfaProducer().
                            size(); k++) {
                        if (input.getInitialData().getProducers().get(j).
                                getDistributorsOfaProducer().get(k).getId() == input.
                                getInitialData().getDistributors().get(i).getId()) {
                            input.getInitialData().getProducers().get(j).
                                    getDistributorsOfaProducer().
                                    get(k).setUpdated(0);
                        }
                    }
                }
                /* delete for each distributor their list of producers */
                input.getInitialData().getDistributors().get(i).
                        getProducersOfaDistributor().
                        clear();
                /* choose other producers */
                SortStrategy strategyType = SortStrategyFactory.
                        createStrategy(input.getInitialData().getDistributors().
                                get(i).getProducerStrategy());
                List<ProducersInputData> sortedList = strategyType.sort(input);

                    int k = 0;
                    int energyNeeded = input.getInitialData().getDistributors().get(i).
                            getEnergyNeededKW();
                    while (input.getInitialData().getDistributors().get(i).
                            getEnergyNeededKW() > 0 ) {
                        if (sortedList.get(k).getDistributorsOfaProducer().
                                size() < sortedList.get(k).
                                getMaxDistributors()) {
                            input.getInitialData().getDistributors().get(i).
                                    getProducersOfaDistributor().
                                    add(sortedList.get(k));
                            /* add the distributors in Observers list */
                            sortedList.get(k).addObserver(input.getInitialData().
                                    getDistributors().get(i));
                            sortedList.get(k).getDistributorsOfaProducer().
                                    add(input.getInitialData().
                                    getDistributors().get(i));
                            input.getInitialData().getDistributors().get(i).
                                    setEnergyNeededKW(input.
                                    getInitialData()
                                    .getDistributors().
                                    get(i).getEnergyNeededKW() - sortedList.get(k).
                                            getEnergyPerDistributor());
                        }
                        k++;
                    }
                        input.getInitialData().getDistributors().get(i).
                                setEnergyNeededKW(energyNeeded);
                        input.getInitialData().getDistributors().get(i).findCost();
                        input.getInitialData().getDistributors().get(i).findProductionCost();
                        input.getInitialData().getDistributors().get(i).setUpdated(0);
                    }
        }
    }
    /** initialize the monthlystats lists and add what needed */
    public void monthlyStats(Input input, int i) {
        /* for the first month */
        if (i == -1) {
            for (int j = 0; j < input.getInitialData().getProducers().size(); j++) {
                MonthlyStats monthlyStats = new MonthlyStats();
                monthlyStats.setMonth(i + 1);
                monthlyStats.setDistributorsIdList(new ArrayList<>());
                input.getInitialData().getProducers().get(j).getMonthlyStats().
                        add(monthlyStats);
            }
        } else {
            for (int j = 0; j < input.getInitialData().getProducers().size(); j++) {
                MonthlyStats monthlyStats = new MonthlyStats();
                monthlyStats.setMonth(i + 1);
                input.getInitialData().getProducers().get(j).getMonthlyStats().
                        add(monthlyStats);
            }
        }
    }
    /** calculate for each distributor his contract price */
    public void distributorsCalculateCost(Input input) {
        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            input.getInitialData().getDistributors().get(i).findCost();
            input.getInitialData().getDistributors().get(i).findProductionCost();
        }
    }
    /** put for each producer his distributors in a list */
    public void putDistributorsInList(Input input, int m) {
        for (int i = 0; i < input.getInitialData().getProducers().size(); i++) {
            for (int j = 0; j < input.getInitialData().getProducers().get(i).
                    getDistributorsOfaProducer().size(); j++) {
                input.getInitialData().getProducers().get(i).getMonthlyStats().get(m).
                        getDistributorsIdList().add(input.getInitialData().
                        getProducers().
                        get(i).getDistributorsOfaProducer().get(j).getId());
                Collections.sort(input.getInitialData().getProducers().get(i).
                        getMonthlyStats().
                        get(m).getDistributorsIdList());
            }
        }
    }
}
