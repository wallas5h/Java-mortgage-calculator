package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.Overpayment;
import projekt2.models.Rate;
import projekt2.models.RateAmounts;
import projekt2.services.interfaces.ConstantAmountsCalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConstantAmountsCalculationServiceImpl implements ConstantAmountsCalculationService {
  @Override
  public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
    BigDecimal interestPercent = inputData.getInterestPercent();
    BigDecimal q = calculateQ(inputData.getInterestPercent());

    BigDecimal residualAmount = inputData.getAmout();

    BigDecimal interestAmount = AmountCalculationServiceImp.calculateInterestAmount(residualAmount, interestPercent);
    BigDecimal referenceDuration = inputData.getMonthsDuration();
    BigDecimal referenceAmount = inputData.getAmout();

    BigDecimal rateAmount = calculateConstantRateAmount(
        q,
        referenceAmount,
        referenceDuration,
        interestAmount,
        residualAmount
    );
    BigDecimal capitalAmount = calculateConstantCapitalAmount(rateAmount, interestAmount,residualAmount);

    return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);

  }
  @Override
  public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
    BigDecimal interestPercent = inputData.getInterestPercent();
    BigDecimal q = calculateQ(interestPercent);

    BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
    BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
    BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();

    BigDecimal interestAmount = AmountCalculationServiceImp.calculateInterestAmount(residualAmount, interestPercent);

    BigDecimal rateAmount = calculateConstantRateAmount(
        q, referenceAmount, referenceDuration,interestAmount,residualAmount);
    BigDecimal capitalAmount = calculateConstantCapitalAmount(rateAmount, interestAmount,residualAmount);

    return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
  }

  private BigDecimal calculateConstantRateAmount(
      BigDecimal q,
      BigDecimal amount,
      BigDecimal monthsDuration,
      BigDecimal interestAmount,
      BigDecimal residualAmount
      ) {
    BigDecimal rateAmount = amount
        .multiply(q.pow(monthsDuration.intValue()))
        .multiply(q.subtract(BigDecimal.ONE))
        .divide(q.pow(monthsDuration.intValue()).subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
    return compareWithResidual(rateAmount,interestAmount,residualAmount);
  }

  private BigDecimal compareWithResidual(BigDecimal rateAmount, BigDecimal interestAmount, BigDecimal residualAmount) {
    if (rateAmount.subtract(interestAmount).compareTo(residualAmount) >= 0) {
      return residualAmount.add(interestAmount);
    }
    return rateAmount;
  }

  private BigDecimal calculateConstantCapitalAmount(
      BigDecimal rateAmount, BigDecimal interestAmount, BigDecimal residualAmount
  ) {
    BigDecimal capitalAmount= rateAmount.subtract(interestAmount);

    if(capitalAmount.compareTo(residualAmount)>=0){
      return residualAmount;
    }

    return  capitalAmount;
  }

  private BigDecimal calculateQ(BigDecimal interestPercent) {
    return interestPercent.divide(AmountCalculationServiceImp.YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
  }

}
