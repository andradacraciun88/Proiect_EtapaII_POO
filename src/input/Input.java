package input;

import java.util.List;

public final class Input {

    private final List<MonthlyUpdate> monthlyUpdates;
    private final Integer numberOfTurns;
    private InitialData initialData;

    public Input() {
        this.monthlyUpdates = null;
        this.numberOfTurns = null;
        this.initialData = null;
    }

    public Input(List<MonthlyUpdate> monthlyUpdates, int numberOfTurns,
                 InitialData initialData) {

        this.monthlyUpdates = monthlyUpdates;
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
    }
    public Integer getNumberOfTurns() {
        return numberOfTurns;
    }
    public List<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }
    public void setInitialData(InitialData initialData) {
        this.initialData = initialData;
    }
    public InitialData getInitialData() {
        return initialData;
    }

    @Override
    public String toString() {
        return "Input{"
                +
                "monthlyUpdates="
                + monthlyUpdates
                +
                ", numberOfTurns="
                + numberOfTurns
                +
                ", initialData="
                + initialData
                +
                '}';
    }
}
