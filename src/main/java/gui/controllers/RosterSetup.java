package gui.controllers;

import forceOrg.ForceOrgChart;
import roster.Codex;

public record RosterSetup(
        String name,
        int pointsLimit,
        Codex codex,
        ForceOrgChart forceOrgChart
        ) {
}
