package projekt2.models;

import java.math.BigDecimal;

public class Rate {
  private final TimePoint timePoint;
  private final BigDecimal rateNumber;

  private final RateAmounts rateAmounts;

  // ile miesięcy zostało do spłaty po tej racie
  private final MortgageResidual mortgageResidual;

  private final MortgageReference mortgageReference;

  public Rate(TimePoint timePoint,
              BigDecimal rateNumber,
              RateAmounts rateAmounts,
              MortgageResidual mortgageResidual,
              MortgageReference mortgageReference) {
    this.timePoint = timePoint;
    this.rateNumber = rateNumber;
    this.rateAmounts = rateAmounts;
    this.mortgageResidual = mortgageResidual;
    this.mortgageReference = mortgageReference;
  }

  public TimePoint getTimePoint() {
    return timePoint;
  }

  public BigDecimal getRateNumber() {
    return rateNumber;
  }

  public RateAmounts getRateAmounts() {
    return rateAmounts;
  }

  public MortgageResidual getMortgageResidual() {
    return mortgageResidual;
  }

  public MortgageReference getMortgageReference() {
    return mortgageReference;
  }
}
