package units.options;

import java.util.List;

import units.options.requirements.RequirementResult;

public interface OptionOwner {
    List<SelectedOption> getSelectedOptions();

    RequirementResult addSelection(OptionChoice option);

    void removeSelection(OptionChoice option);
    
    default int getOptionCount(OptionChoice choice) {

        int count = 0;

        for (SelectedOption selected : getSelectedOptions()) {
            if (selected.getChoice().equals(choice)) {
                count++;
            }
        }

        return count;
    }
}
