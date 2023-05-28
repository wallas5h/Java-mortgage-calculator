package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.Rate;
import projekt2.models.Summary;
import projekt2.services.interfaces.Function;
import projekt2.services.interfaces.SummaryService;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {
  public static SummaryService create(InputData inputData) {
    return rates -> {
//      BigDecimal interestSum= calculateInterestSum(rates);
      BigDecimal interestSum = calculate(
          rates,
          rate-> rate.getRateAmounts().getInterestAmount());
      BigDecimal provisions = calculate(
          rates,
          rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount());
      BigDecimal totalLosts= interestSum.add(provisions);
      BigDecimal totalMorgageCosts=totalLosts.add(inputData.getAmout());
      return new Summary(interestSum, provisions, totalLosts, totalMorgageCosts);
    };
  }
  // sposób na skrócenie dwóch funkcji w jedną z użyciem Function
  private static BigDecimal calculate(List<Rate> rates, Function function) {
    BigDecimal sum = BigDecimal.ZERO;
    for (Rate rate : rates) {
      sum = sum.add(function.calculate(rate));
    }
    return sum;
  }

//  private static BigDecimal calculateInterestSum(List<projekt1.models.Rate> rates) {
//    BigDecimal sum= BigDecimal.ZERO;
//    for (projekt1.models.Rate rate: rates){
//      sum= sum.add(rate.getRateAmounts().getInterestAmount());
//    }
//    return sum;
//  }


}
