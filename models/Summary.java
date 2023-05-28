package projekt2.models;

import java.math.BigDecimal;

public class Summary {

  public final BigDecimal interestSum;
  private final BigDecimal overpaymentProvisions;
  private final BigDecimal totalLosts;
  private final BigDecimal totalMorgageCosts;

  public Summary(BigDecimal interestSum, BigDecimal overpaymentProvisions, BigDecimal totalLosts, BigDecimal totalMorgageCosts) {
    this.interestSum = interestSum;
    this.overpaymentProvisions = overpaymentProvisions;
    this.totalLosts = totalLosts;
    this.totalMorgageCosts = totalMorgageCosts;
  }

  public BigDecimal getInterestSum() {
    return interestSum;
  }

  public BigDecimal getOverpaymentProvisions() {
    return overpaymentProvisions;
  }

  public BigDecimal getTotalLosts() {
    return totalLosts;
  }

  public BigDecimal getTotalMorgageCosts() {
    return totalMorgageCosts;
  }
}
