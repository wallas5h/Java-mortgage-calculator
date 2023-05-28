package projekt2.services.interfaces;

import projekt2.models.InputData;
import projekt2.models.MortgageReference;
import projekt2.models.Rate;
import projekt2.models.RateAmounts;

public interface ReferenceCalculationService {
  // nadpłata kredytu -> skrócenie czasu trwania kredytu, zamiast zmniejszenia raty

  MortgageReference calculate(InputData inputData);

  MortgageReference calculate(RateAmounts rateAmounts, InputData inputData, Rate previousRate);
}
