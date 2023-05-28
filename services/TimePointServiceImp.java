package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.TimePoint;
import projekt2.services.interfaces.TimePointService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimePointServiceImp implements TimePointService {

  private static final BigDecimal YEAR= BigDecimal.valueOf(12);


  @Override
  public TimePoint calculate(BigDecimal rateNumber, InputData inputData) {
    LocalDate date= calculateDate(rateNumber, inputData);
    BigDecimal year= calculateYear(rateNumber);
    BigDecimal month= calculateMonths(rateNumber);
    return new TimePoint(date,year,month);
  }

  private static LocalDate calculateDate(BigDecimal rateNumber, InputData inputData) {
    return inputData.getRepaymentStartData()
        .plus(rateNumber.subtract(BigDecimal.ONE).intValue(), ChronoUnit.MONTHS);
  }

  private BigDecimal calculateYear(final BigDecimal rateNumber){
    return rateNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
  }

  private BigDecimal calculateMonths(final BigDecimal rateNumber){
    return BigDecimal.ZERO.equals(rateNumber.remainder(YEAR))? YEAR: rateNumber.remainder(YEAR);
  }
}
