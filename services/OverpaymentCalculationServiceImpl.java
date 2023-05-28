package projekt2.services;

import projekt2.models.InputData;
import projekt2.models.Overpayment;
import projekt2.services.interfaces.OverpaymentCalculationService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class OverpaymentCalculationServiceImpl implements OverpaymentCalculationService {
  @Override
  public Overpayment calculate(BigDecimal rateNumber, InputData inputData) {
    BigDecimal overpaymentAmount=calculateAmount(rateNumber, inputData.getOverpamentSchema())
        .orElse(BigDecimal.ZERO);
    BigDecimal overpaymentProvision= calculateProvision(rateNumber, overpaymentAmount, inputData);

    return new Overpayment(overpaymentAmount,overpaymentProvision);
  }

  private Optional <BigDecimal> calculateAmount(BigDecimal rateNumber, Map<Integer, BigDecimal> overpamentSchema) {
    for (Map.Entry<Integer, BigDecimal> entry : overpamentSchema.entrySet()) {
      if(rateNumber.equals(BigDecimal.valueOf(entry.getKey()))){
        return Optional.of(entry.getValue());
      }
    }
    return Optional.empty();
  }

  private BigDecimal calculateProvision(BigDecimal rateNumber, BigDecimal overpaymentAmount, InputData inputData) {
    if(BigDecimal.ZERO.equals(overpaymentAmount)){
      return BigDecimal.ZERO;
    }

    if(rateNumber.compareTo(inputData.getOverpaymentProvisionMonths())>0){
      return BigDecimal.ZERO;
    }

//@TODO błednie liczy prowizje
    return overpaymentAmount.multiply(inputData.getOverpaymentProvisionPercent()).divide(BigDecimal.valueOf(100));
  }
}
