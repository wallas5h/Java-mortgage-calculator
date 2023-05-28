package projekt2;

import projekt2.models.InputData;
import projekt2.models.Overpayment;
import projekt2.models.RateType;
import projekt2.services.*;
import projekt2.services.interfaces.MortgageCalculationService;
import projekt2.services.interfaces.PrintingService;
import projekt2.services.interfaces.RateCalculationService;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Map<Integer, BigDecimal> overpamentSchemaEmpty = Map.of();
    Map<Integer, BigDecimal> overpamentSchema1 = Map.of(
        4, BigDecimal.valueOf(10000),
        8, BigDecimal.valueOf(10000),
        15, BigDecimal.valueOf(10000),
        28, BigDecimal.valueOf(10000)
    );
    Map<Integer, BigDecimal> overpamentSchema2 = Map.of(
        4, BigDecimal.valueOf(10000),
        12, BigDecimal.valueOf(10000)
    );
    InputData inputData = new InputData()
        .withAmout(new BigDecimal("170000"))
        .withMonthsDuration(new BigDecimal("240"))
        .withWiborPercent(new BigDecimal("6.95"))
        .withBankMarginPercent(new BigDecimal("1.84"))
        .withOverpamentSchema(overpamentSchemaEmpty)
//        .withOverpamentSchema(overpamentSchema2)
//        .withRateType(RateType.CONSTANT)
        .withRateType(RateType.DECREASING)
        .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD);

    PrintingService printingService = new PrintingServiceImp();
    RateCalculationService rateCalculationService = new RateCalculationServiceImp(
        new TimePointServiceImp(),
        new AmountCalculationServiceImp(
            new ConstantAmountsCalculationServiceImpl(),
            new DecreasingAmountsCalculationServiceImpl()),
        new OverpaymentCalculationServiceImpl(),
        new ResidualCalculationServiceImp(),
        new ReferencCalculationServiceImpl()

    );

    MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImp(
        printingService,
        rateCalculationService,
        SummaryServiceFactory.create(inputData));
    mortgageCalculationService.calculate(inputData);
  }
}
