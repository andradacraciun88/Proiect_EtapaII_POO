package compare;
import input.ProducersInputData;
import java.util.Comparator;

public final class CompareGreenStrategy implements Comparator<ProducersInputData> {
    @Override
    public int compare(ProducersInputData o1, ProducersInputData o2) {

        if (o1.getRenewableValue() > o2.getRenewableValue()) {
            return 1;
        }
        if (o1.getRenewableValue() < o2.getRenewableValue()) {
            return -1;
        }
        if (o1.getPriceKW() != o2.getPriceKW()) {
            if (o1.getPriceKW() > o2.getPriceKW()) {
                return 1;
            }
            if (o1.getPriceKW() < o2.getPriceKW()) {
                return -1;
            }
        }
        return o2.getEnergyPerDistributor() - o1.getEnergyPerDistributor();
    }
}
