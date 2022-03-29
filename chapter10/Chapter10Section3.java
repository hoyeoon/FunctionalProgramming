package chapter10;

import chapter10.model.Price;
import chapter10.service.BasicPriceProcessor;
import chapter10.service.DiscountPriceProcessor;
import chapter10.service.PriceProcessor;
import chapter10.service.TaxPriceProcessor;

/**
 * Decorator Pattern
 * - 구조 패턴의 하나
 * - 용도에 따라 객체에 기능을 계속 추가(decorate)할 수 있게 해준다.
 */
public class Chapter10Section3 {
    public static void main(String[] args) {
        Price unprocessedPrice = new Price("Original Price");

        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

        // 람다 사용 이전
        PriceProcessor decoratedPriceProcessor = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(taxPriceProcessor);
        Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
        System.out.println("processedPrice.getPrice() = " + processedPrice.getPrice());

        // 람다 사용 - PriceProcessor가 Functional interface이기 때문에 클래스를 만들지 않고 바로 구현할 수 있다.
        // But, 재활용이 불가능. 상황에 따라 위의 방식 혹은 아래 방식을 선택하면 된다.
        PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(price -> new Price(price.getPrice() + ", then apply another procedure"));
        Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
        System.out.println("processedPrice2.getPrice() = " + processedPrice2.getPrice());
    }
}
