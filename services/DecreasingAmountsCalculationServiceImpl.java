package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.Overpayment;
import projekt2.models.Rate;
import projekt2.models.RateAmounts;
import projekt2.services.interfaces.DecreasingAmountsCalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static projekt2.services.AmountCalculationServiceImp.YEAR;

public class DecreasingAmountsCalculationServiceImpl implements DecreasingAmountsCalculationService {
  @Override
  public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
    BigDecimal interestPercent = inputData.getInterestPercent();
    BigDecimal residualAmount = inputData.getAmout();
    BigDecimal referenceDuration = inputData.getMonthsDuration();
    BigDecimal referenceAmount = inputData.getAmout();

    BigDecimal interestAmount = AmountCalculationServiceImp.calculateInterestAmount(residualAmount, interestPercent);
    BigDecimal capitalAmount = calculateDecreasingCapitalAmount(referenceAmount, referenceDuration,residualAmount);
    BigDecimal rateAmount = capitalAmount.add(interestAmount);

    return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
  }

  @Override
  public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
    BigDecimal interestPercent = inputData.getInterestPercent();

    BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
    BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
    BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();

    BigDecimal interestAmount = AmountCalculationServiceImp.calculateInterestAmount(residualAmount, interestPercent);
    BigDecimal capitalAmount = calculateDecreasingCapitalAmount(referenceAmount, referenceDuration,residualAmount);
    BigDecimal rateAmount = capitalAmount.add(interestAmount);

    return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
  }


  private BigDecimal calculateDecreasingCapitalAmount(
      BigDecimal amount, BigDecimal monthsDuration,BigDecimal residualAmount
  ) {
    BigDecimal capitalAmount=amount.divide(monthsDuration, 10, RoundingMode.HALF_UP);

    if(capitalAmount.compareTo(residualAmount)>=0){
      return residualAmount;
    }

    return  capitalAmount;


  }
}
