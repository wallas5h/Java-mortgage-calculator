package projekt2.services.interfaces;

import projekt2.models.Rate;
import projekt2.models.Summary;

import java.util.List;

public interface SummaryService {

  Summary calculate(List<Rate> rates);
}
