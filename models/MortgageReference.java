package projekt2.models;

import java.math.BigDecimal;

// referencja przymająca wartość jaka była ostatnia wysokość kredytu przed nadpłatą- kredyt trzeba przeliczyć od tego miejsca na nowo
public class MortgageReference {
  private final BigDecimal referenceAmount;
  private final BigDecimal referenceDuration;

  public MortgageReference(BigDecimal referenceAmount, BigDecimal referenceDuration) {
    this.referenceAmount = referenceAmount;
    this.referenceDuration = referenceDuration;
  }

  public BigDecimal getReferenceAmount() {
    return referenceAmount;
  }

  public BigDecimal getReferenceDuration() {
    return referenceDuration;
  }
}
