package projekt2.services.interfaces;

import projekt2.models.InputData;
import projekt2.models.Rate;

import java.util.List;

public interface RateCalculationService {

  List<Rate> calculate (final InputData inputData);
}
