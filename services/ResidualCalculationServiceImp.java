package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.MortgageResidual;
import projekt2.models.Rate;
import projekt2.models.RateAmounts;
import projekt2.services.interfaces.ResidualCalculationService;

import java.math.BigDecimal;

public class ResidualCalculationServiceImp implements ResidualCalculationService {
  @Override
  public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData) {
//    BigDecimal residualAmount = calculateResidualAmount(inputData.getAmout(), rateAmounts.getCapitalAmount());
//    BigDecimal residualDuration = calculateResidualAmount(inputData.getMonthsDuration(), BigDecimal.ONE);

    BigDecimal residualAmount = calculateResidualAmount(inputData.getAmout(), rateAmounts);
    BigDecimal residualDuration = inputData.getMonthsDuration().subtract(BigDecimal.ONE);

    return new MortgageResidual(residualAmount, residualDuration);
  }

  public static BigDecimal calculateResidualAmount(BigDecimal inputData, RateAmounts rateAmounts) {
    return inputData
        .subtract(rateAmounts.getCapitalAmount())
        .subtract(rateAmounts.getOverpayment().getAmount())
        .max(BigDecimal.ZERO);
  }

  @Override
  public MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate) {

    BigDecimal residualAmount = calculateResidualAmount(previousRate.getMortgageResidual().getAmount(), rateAmounts);
    BigDecimal residualDuration = previousRate.getMortgageResidual().getDuration().subtract(BigDecimal.ONE);

    return new MortgageResidual(residualAmount, residualDuration);

  }
}
