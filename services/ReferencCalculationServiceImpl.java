package projekt2.services;

import projekt2.models.*;
import projekt2.services.interfaces.ReferenceCalculationService;
import projekt2.services.utils.MortgageException;

import java.math.BigDecimal;

import static projekt2.services.ResidualCalculationServiceImp.calculateResidualAmount;

public class ReferencCalculationServiceImpl implements ReferenceCalculationService {

  // nadpłata kredytu -> skrócenie czasu trwania kredytu, zamiast zmniejszenia raty
  @Override
  public MortgageReference calculate(InputData inputData) {
    if (BigDecimal.ZERO.equals(inputData.getAmout())) {
      return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
    }
    return new MortgageReference(inputData.getAmout(), inputData.getMonthsDuration());
  }

  @Override
  public MortgageReference calculate(RateAmounts rateAmounts, InputData inputData, Rate previousRate) {
    if (BigDecimal.ZERO.equals(previousRate.getMortgageResidual().getAmount())) {
      return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
    }

    switch (inputData.getOverpaymentReduceWay()) {
      case Overpayment.REDUCE_RATE:
        return reduceRateMortgageReference(rateAmounts, previousRate);
      case Overpayment.REDUCE_PERIOD:
        return new MortgageReference(inputData.getAmout(), inputData.getMonthsDuration());
      default:
        throw new MortgageException();
    }

  }

  private MortgageReference reduceRateMortgageReference(RateAmounts rateAmounts, Rate previousRate) {

    if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
      BigDecimal referenceAmount = calculateResidualAmount(
          previousRate.getMortgageResidual().getAmount(),
          rateAmounts);
      BigDecimal referenceDuration = previousRate.getMortgageResidual().getDuration().subtract(BigDecimal.ONE);
      return new MortgageReference(referenceAmount,referenceDuration);
    }

    return new MortgageReference(
        previousRate.getMortgageReference().getReferenceAmount(),
        previousRate.getMortgageReference().getReferenceDuration()
    );
  }
}
