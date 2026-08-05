package builder;

import roster.Codex;
import roster.Roster;

public class ArmyBuilder {

    private final Codex codex;
    private final Roster roster;

    public ArmyBuilder(Codex codex) {
        this.codex = codex;
        this.roster = Roster.createEmpty();
    }

    public Codex getCodex() {
        return codex;
    }

    public Roster getRoster() {
        return roster;
    }
}