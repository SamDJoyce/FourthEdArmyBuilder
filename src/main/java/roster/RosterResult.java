package roster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RosterResult {

	private final List<RosterIssue> issues;
	
	public RosterResult() {
		issues = new ArrayList<>();
	}
	
	public List<RosterIssue> getIssues() {
		return Collections.unmodifiableList(issues);
	}
	
	public void addIssue(String issue) {
		issues.add(new RosterIssue(issue));
	}
	
	public void addIssues(List<RosterIssue> issues) {
		issues.addAll(issues);
	}
	
	public boolean isValid() {
		return issues.isEmpty();
	}
	
	public boolean hasIssues() {
		return !issues.isEmpty();
	}

	public String getMessage() {

        if (isValid()) {
            return "Roster is valid";
        }

        StringBuilder sb = new StringBuilder();

        for (RosterIssue issue : issues) {
            sb.append(issue.getMessage()).append("\n");
        }

        return sb.toString();
    }
}
