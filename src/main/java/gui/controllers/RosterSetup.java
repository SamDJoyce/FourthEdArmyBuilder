package gui.controllers;

import forceOrg.ForceOrgChart;

public record RosterSetup(
        String name,
        int pointsLimit,
        ForceOrgChart forceOrgChart) {
}
