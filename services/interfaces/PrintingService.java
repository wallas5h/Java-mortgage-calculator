package projekt2.services.interfaces;

import projekt2.models.InputData;
import projekt2.models.Rate;
import projekt2.models.Summary;

import java.util.List;

public interface PrintingService {

  String INTEREST_SUM= "SUMA ODSETEK: ";
  String RATE_NUMBER= "NR: ";
  String OVERPAYMENT_PROVISION= "PROWIZJA ZA NADPŁATY: ";
  String INTEREST_AND_PROVISION_SUM = "SUMA ODSETEK I PROWZJI OD NADPŁAT: ";
  String TOTAL_MORTGAGE_LOAN="CAŁKOWITY KOSZT KREDYTU: ";
  String OVERPAYMENT_REDUCE_RATE= "NADPŁATA, ZMNIEJSZENIE RATY: ";
  String OVERPAYMENT_REDUCE_PERIOD= "NADPŁATA, SKRÓCENIE KRETYDU: ";
  String OVERPAYMENT_FREQUENCY= "SCHEMAT DOKONYWANIA NADPŁAT: ";
  String OVERPAYMENT= "NADPŁATA: ";
  String YEAR= "ROK: ";
  String MONTH= "MIESIĄC: ";
  String DATE= "DATA: ";
  String MONTHS= " MIESIĘCY, ";
  String RATE= "RATA: ";
  String INTEREST= "ODSETKI: ";
  String CAPITAL= "KAPITAŁ: ";
  String LEFT_AMOUNT= "POZOSTAŁA KWOTA: ";
  String LEFT_MONTHS= "POZOSTAŁO MIESIĘCY: ";
  String MORTGAGE_AMOUNT= "KWOTA KREDYTU: ";
  String MORTGAGE_PERIOD= "OKRES KREDYTOWANIA: ";

  String CURRENCY= " ZL ";
  String PERCENT= "% ";
  String NEW_LINE= "\n";


  void printInputDataInfo(final InputData inputData);

  void printRates(List<Rate> rates);

  void printSummary(Summary summary);
}
