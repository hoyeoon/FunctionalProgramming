package chapter9;

import chapter9.model.Order;
import chapter9.model.OrderLine;
import chapter9.priceprocessor.OrderLineAggregationPriceProcessor;
import chapter9.priceprocessor.TaxPriceProcessor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Function Composition
 * - 여러 개의 함수를 합쳐 하나의 새로운 함수로 만드는 것
 *
 * java.util.function.Function
 * <V> Function<V, R> compose(Function<? super V, ? extends T> before)
 * <V> Function<T, V> andThen(Function<? super R, ? extends V> after)
 */
public class Chapter9Section3 {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer, Integer> addTen = x -> x + 10;

        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen);
        System.out.println(composedFunction.apply(3));

        System.out.println("----------------------------------");

        Order unprocessedOrder = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));

        List<Function<Order, Order>> priceProcessors = getPriceProcessors();

        Function<Order, Order> mergedProcessors = priceProcessors.stream()
                .reduce(Function.identity(), Function::andThen); // (priceProcessors1, priceProcessors2) -> priceProcessors1.andThen(priceProcessors2));

        Order processedOrder = mergedProcessors.apply(unprocessedOrder);
        System.out.println("processedOrder = " + processedOrder);
    }

    public static List<Function<Order, Order>> getPriceProcessors() {
        return Arrays.asList(new OrderLineAggregationPriceProcessor(),
                             new TaxPriceProcessor(new BigDecimal("9.375")));
    }
}
