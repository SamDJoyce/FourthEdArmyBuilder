package units.options;

import java.util.List;

import units.options.requirements.RequirementResult;

public interface OptionOwner {
    List<SelectedOption> getSelectedOptions();

    RequirementResult addSelection(OptionChoice option);

    void removeSelection(OptionChoice option);
}
