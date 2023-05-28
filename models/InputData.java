package projekt2.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {

  private LocalDate repaymentStartData = LocalDate.of(2023, 8, 5);
  private BigDecimal wiborPercent = new BigDecimal("6.9");
  private BigDecimal amout = new BigDecimal("300000");
  private BigDecimal monthsDuration = BigDecimal.valueOf(180);
  private RateType rateType = RateType.CONSTANT;
  private BigDecimal bankMarginPercent = new BigDecimal("1.9");

  // założenie- najpierw spłacasz ratę a dopiero potem nadpłacasz kredyt
  private Map<Integer,BigDecimal> overpamentSchema= Map.of(
      5, BigDecimal.valueOf(10000),
      6, BigDecimal.valueOf(10000),
      7, BigDecimal.valueOf(10000),
      8, BigDecimal.valueOf(10000),
      9, BigDecimal.valueOf(10000)
  );

  // redukowanie kredytu (skracanie kredytu) lub pozostawienie dł. kredytu bez zmian
  // OVERPAYMENT KLASA TRZYMAJACA WARTOŚCI NADPŁAT
  private String overpaymentReduceWay= Overpayment.REDUCE_PERIOD;

  // prowizja przy przedwczesnej nadpłacie kredytu
  private BigDecimal overpaymentProvisionPercent=BigDecimal.valueOf(3);

  // miesiące przy których bank nalicza prowizje od nadpłaty
  private BigDecimal overpaymentProvisionMonths=BigDecimal.valueOf(36);

  private static final BigDecimal PERCENT= BigDecimal.valueOf(100);

  // wefery

  public InputData withOverpamentSchema(Map<Integer,BigDecimal> overpamentSchema){
    this.overpamentSchema=overpamentSchema;
    return this;
  }

  public InputData withOverpaymentReduceWay(String overpaymentReduceWay){
    this.overpaymentReduceWay=overpaymentReduceWay;
    return this;
  }

  public InputData withOverpaymentProvisionPercent(BigDecimal overpaymentProvisionPercent){
    this.overpaymentProvisionPercent=overpaymentProvisionPercent;
    return this;
  }

  public InputData withOverpaymentProvisionMonths( BigDecimal overpaymentProvisionMonths){
    this.overpaymentProvisionMonths=overpaymentProvisionMonths;
    return this;
  }


  public InputData withRepaymentStartData(LocalDate repaymentStartData) {
    this.repaymentStartData = repaymentStartData;
    return this;
  }

  public InputData withWiborPercent(BigDecimal wiborPercent) {
    this.wiborPercent = wiborPercent;
    return this;
  }

  public InputData withAmout(BigDecimal amout) {
    this.amout = amout;
    return this;
  }

  public InputData withMonthsDuration(BigDecimal monthsDuration) {
    this.monthsDuration = monthsDuration;
    return this;
  }

  public InputData withRateType(RateType rateType) {
    this.rateType = rateType;
    return this;
  }

  public InputData withBankMarginPercent(BigDecimal bankMarginPercent) {
    this.bankMarginPercent = bankMarginPercent;
    return this;
  }

  public LocalDate getRepaymentStartData() {
    return repaymentStartData;
  }

  public BigDecimal getWiborPercent() {
    return wiborPercent;
  }

  public BigDecimal getAmout() {
    return amout;
  }

  public BigDecimal getMonthsDuration() {
    return monthsDuration;
  }

  public RateType getRateType() {
    return rateType;
  }

  public BigDecimal getBankMarginPercent() {
    return bankMarginPercent;
  }

  public BigDecimal getInterestPercent(){
    return wiborPercent.add(bankMarginPercent).divide(PERCENT,10, RoundingMode.HALF_UP);
  }
  public BigDecimal getInterestDisplay(){
    return wiborPercent.add(bankMarginPercent).setScale(2,RoundingMode.HALF_UP);
  }

  public Map<Integer, BigDecimal> getOverpamentSchema() {
    return overpamentSchema;
  }

  public String getOverpaymentReduceWay() {
    return overpaymentReduceWay;
  }

  public BigDecimal getOverpaymentProvisionPercent() {
    return overpaymentProvisionPercent;
  }

  public BigDecimal getOverpaymentProvisionMonths() {
    return overpaymentProvisionMonths;
  }
}
