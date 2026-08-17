package units.options;

import java.util.Set;

import units.options.requirements.RequirementResult;

public interface OptionOwner {
    Set<SelectedOption> getSelectedOptions();
    
    String getId();
    
    String getName();
    
    boolean isModel();
    
    boolean isUnit();
    
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
