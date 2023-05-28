package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.Overpayment;
import projekt2.models.Rate;
import projekt2.models.RateAmounts;
import projekt2.services.interfaces.AmountCalculationService;
import projekt2.services.interfaces.ConstantAmountsCalculationService;
import projekt2.services.utils.MortgageException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountCalculationServiceImp implements AmountCalculationService {
  public static final BigDecimal YEAR = BigDecimal.valueOf(12);

  public final ConstantAmountsCalculationService constantAmountsCalculationService;
  public final DecreasingAmountsCalculationServiceImpl decreasingAmountsCalculationService;

  public AmountCalculationServiceImp(ConstantAmountsCalculationService constantAmountsCalculationService,
                                     DecreasingAmountsCalculationServiceImpl decreasingAmountsCalculationService) {
    this.constantAmountsCalculationService = constantAmountsCalculationService;
    this.decreasingAmountsCalculationService = decreasingAmountsCalculationService;
  }

  public static BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
    return residualAmount.multiply(interestPercent).divide(YEAR, 10, RoundingMode.HALF_UP);
  }

  @Override
  public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
    switch (inputData.getRateType()) {
      case CONSTANT:
        return constantAmountsCalculationService.calculate(inputData, overpayment);
      case DECREASING:
        return decreasingAmountsCalculationService.calculate(inputData, overpayment);
      default:
        throw new MortgageException();
    }
  }

  @Override
  public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
    switch (inputData.getRateType()) {
      case CONSTANT:
        return constantAmountsCalculationService.calculate(inputData, overpayment,previousRate);
      case DECREASING:
        return decreasingAmountsCalculationService.calculate(inputData, overpayment,previousRate);
      default:
        throw new MortgageException();
    }
  }
}
