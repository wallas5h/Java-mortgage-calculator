package projekt2.services.interfaces;

import projekt2.models.InputData;
import projekt2.models.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculationService {
  Overpayment calculate (BigDecimal rateNumber, InputData inputData);
}
