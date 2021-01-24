package output;

import entities.MonthlyStats;
import java.util.ArrayList;
import java.util.List;

public final class MonthlyStatsOut {
    private int month;
    private List<Integer> distributorsIds = new ArrayList<>();

    public MonthlyStatsOut(MonthlyStats monthlyStats) {
        this.month = monthlyStats.getMonth();
        for (int i = 0; i < monthlyStats.getDistributorsIdList().size(); i++) {
            this.distributorsIds = monthlyStats.getDistributorsIdList();
        }
    }
    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
