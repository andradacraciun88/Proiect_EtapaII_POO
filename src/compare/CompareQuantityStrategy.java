package compare;
import input.ProducersInputData;

import java.util.Comparator;

public final class CompareQuantityStrategy implements Comparator<ProducersInputData> {
    @Override
    public int compare(ProducersInputData o1, ProducersInputData o2) {
        return o2.getEnergyPerDistributor() - o1.getEnergyPerDistributor();
    }
}
