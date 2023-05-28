package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.Rate;
import projekt2.models.Summary;
import projekt2.services.interfaces.MortgageCalculationService;
import projekt2.services.interfaces.PrintingService;
import projekt2.services.interfaces.RateCalculationService;
import projekt2.services.interfaces.SummaryService;

import java.util.List;

public class MortgageCalculationServiceImp implements MortgageCalculationService {

  private final PrintingService printingService;
  private final RateCalculationService rateCalculationService;
  private final SummaryService summaryService;

  public MortgageCalculationServiceImp(PrintingService printingService, RateCalculationService rateCalculationService,
                                       SummaryService summaryService) {
    this.printingService = printingService;
    this.rateCalculationService = rateCalculationService;
    this.summaryService = summaryService;
  }

  @Override
  public void calculate(InputData inputData) {
    printingService.printInputDataInfo(inputData);
    List<Rate> rates= rateCalculationService.calculate(inputData);

    Summary summary=summaryService.calculate(rates);
    printingService.printSummary(summary);
    printingService.printRates(rates);
  }
}
