package projekt2.services.interfaces;

import projekt2.models.InputData;
import projekt2.models.Overpayment;
import projekt2.models.Rate;
import projekt2.models.RateAmounts;

public interface AmountCalculationService {
  RateAmounts calculate(InputData inputData, Overpayment overpayment);

  RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
