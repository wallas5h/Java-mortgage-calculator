package projekt2.services.interfaces;

import projekt2.models.InputData;
import projekt2.models.TimePoint;

import java.math.BigDecimal;

public interface TimePointService {
  TimePoint calculate(BigDecimal rateNumber, InputData inputData);
}
