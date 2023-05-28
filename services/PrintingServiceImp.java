package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.Overpayment;
import projekt2.models.Rate;
import projekt2.models.Summary;
import projekt2.services.interfaces.PrintingService;
import projekt2.services.utils.MortgageException;

import java.util.List;
import java.util.Optional;

public class PrintingServiceImp implements PrintingService {
  @Override
  public void printInputDataInfo(InputData inputData) {

    StringBuilder msg = new StringBuilder(NEW_LINE);
    msg.append(MORTGAGE_AMOUNT).append(inputData.getAmout()).append(CURRENCY);
    msg.append(NEW_LINE);
    msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
    msg.append(NEW_LINE);
    msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
    msg.append(NEW_LINE);

    Optional.of(inputData.getOverpamentSchema())
        .filter(schema -> schema.size() > 0)
        .ifPresent(schema -> logOverpayment(msg, inputData));

    printMessage(msg);
  }

  private void logOverpayment(StringBuilder msg, InputData inputData) {
    switch (inputData.getOverpaymentReduceWay()){
      case Overpayment.REDUCE_PERIOD:
        msg.append(OVERPAYMENT_REDUCE_PERIOD);
        break;
      case Overpayment.REDUCE_RATE:
        msg.append(OVERPAYMENT_REDUCE_RATE);
        break;
      default:
        throw new MortgageException();
    }
    msg.append(NEW_LINE);
    msg.append(OVERPAYMENT_FREQUENCY).append(inputData.getOverpamentSchema());
    msg.append(NEW_LINE);
  }

  @Override
  public void printRates(List<Rate> rates) {

    // formatowanie tekstu w konsoli
    String format = "%s %s " +
        "%s %s " +
        "%s %s " +
        "%s %s " +
        "%s %s " +
        "%s %s " +
        "%s %s " +
        "%s %s " +
        "%s %s " +
        "%s %s ";

    for (Rate rate : rates) {
      String message = String.format(format,
          RATE_NUMBER, rate.getRateNumber(),
          DATE, rate.getTimePoint().getDate(),
          MONTH, rate.getTimePoint().getMonth(),
          YEAR, rate.getTimePoint().getYear(),
          RATE, rate.getRateAmounts().getRateAmount(),
          INTEREST, rate.getRateAmounts().getInterestAmount(),
          CAPITAL, rate.getRateAmounts().getCapitalAmount(),
          OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
          LEFT_AMOUNT, rate.getMortgageResidual().getAmount(),
          LEFT_MONTHS, rate.getMortgageResidual().getDuration()
      );
      printMessage(new StringBuilder(message));

      if (rate.getRateNumber().intValue() % 12 == 0) {
        System.out.println();
      }
    }
  }

  @Override
  public void printSummary(Summary summary) {
    StringBuilder msg = new StringBuilder(NEW_LINE);
    msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
    msg.append(NEW_LINE);
    msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisions()).append((CURRENCY));
    msg.append(NEW_LINE);
    msg.append(INTEREST_AND_PROVISION_SUM).append(summary.getTotalLosts()).append((CURRENCY));
    msg.append(NEW_LINE);
    msg.append(TOTAL_MORTGAGE_LOAN).append(summary.getTotalMorgageCosts()).append((CURRENCY));
    msg.append(NEW_LINE);
    printMessage(new StringBuilder(msg.toString()));
  }

  private void printMessage(StringBuilder msg) {
    System.out.println(msg.toString());
  }
}
