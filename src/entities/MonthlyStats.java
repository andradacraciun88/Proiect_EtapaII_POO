package entities;

import java.util.ArrayList;
import java.util.List;

public final class MonthlyStats {
    private int month;
    private List<Integer> distributorsIdList = new ArrayList<>();

    public List<Integer> getDistributorsIdList() {
        return distributorsIdList;
    }

    public void setDistributorsIdList(List<Integer> distributorsIdList) {
        this.distributorsIdList = distributorsIdList;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
