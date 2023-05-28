package projekt2.services.interfaces;

import projekt2.models.InputData;
import projekt2.models.MortgageResidual;
import projekt2.models.Rate;
import projekt2.models.RateAmounts;

public interface ResidualCalculationService {
  MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);
  MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate);
}
