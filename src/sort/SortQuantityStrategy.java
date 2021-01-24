package sort;

import compare.CompareQuantityStrategy;
import input.Input;
import input.ProducersInputData;
import java.util.Collections;
import java.util.List;

public final class SortQuantityStrategy implements SortStrategy {

    @Override
    public List<ProducersInputData> sort(Input input) {
        List<ProducersInputData> list = input.getInitialData().getProducers();
        Collections.sort(list, new CompareQuantityStrategy());
        return list;
    }
}
