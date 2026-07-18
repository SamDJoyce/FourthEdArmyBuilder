package units.options;

import java.util.List;

public interface OptionOwner {
    List<SelectedOption> getSelectedOptions();

    void addSelection(OptionChoice option);

    void removeSelection(OptionChoice option);
}
