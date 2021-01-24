package sort;

import compare.CompareGreenStrategy;
import input.Input;
import input.ProducersInputData;

import java.util.Collections;
import java.util.List;

public final class SortGreenStrategy implements SortStrategy {

    @Override
    public List<ProducersInputData> sort(Input input) {
        List<ProducersInputData> list = input.getInitialData().getProducers();
        Collections.sort(list, new CompareGreenStrategy());
        return list;
    }
}
