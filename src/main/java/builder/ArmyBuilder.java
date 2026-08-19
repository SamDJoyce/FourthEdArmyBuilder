package builder;

import java.util.List;

import roster.Codex;
import roster.Roster;
import roster.RosterResult;
import units.UnitRole;
import units.WargearType;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.OptionGroup;
import units.options.OptionOwner;
import units.options.effects.Effect;
import units.options.requirements.Requirement;
import units.options.requirements.RequirementResult;

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
    
    // *******************************
    // ***** Roster Interactions *****
    // *******************************
    public RosterResult addUnit(UnitDescription unit) {
    	return roster.addUnit(unit);
    }
    
    public RosterResult removeUnit(UnitInstance unit) {
    	return roster.removeUnit(unit);
    }
    
    public UnitInstance getUnitInstance(String id) {
    	return roster.getUnitById(id);
    }
    
    public List<UnitInstance> getUnitInstancesByRole(UnitRole role){
    	return roster.getUnitsByRole(role);
    }
    
    public RosterResult addModel(
    		UnitInstance unit, 
			ModelInstance model) {
    	return roster.addModel(unit, model);
    }
    
    public RosterResult removeModel(ModelInstance model) {
    	return roster.removeModel(model);
    }
    
    public RosterResult selectOption(			
    		OptionOwner owner, 
			OptionChoice choice) {
    	RosterResult result = RosterResult.create();
    	RequirementResult req = roster.selectOption(owner, choice);
    	if (!req.isValid()) {
    		result.addIssue(req.getMessage());
    	}
    	return result;
    }
    
    public int getPointsLimit() {
    	return roster.getPointsLimit();
    }
    
    public int getCurrentPoints() {
    	return roster.getCurrentPoints();
    }
   
    // ******************************
    // ***** Codex Interactions *****
    // ******************************
    public UnitDescription getUnitDescription(String unitName) {
    	return codex.getUnit(unitName);
    }
    
    public List<UnitDescription> getUnitDescriptionByRole(UnitRole role){
    	return codex.getUnitsByRole(role);
    }
    
    public ModelDescription getModelDescription(String modelName) {
    	return codex.getModel(modelName);
    }
    
    public WargearDescription getWargearDescription(String gearName) {
    	return codex.getWargear(gearName);
    }
    
    public List<WargearDescription> getWargearDescriptionByType(WargearType type){
    	return codex.getWargearByType(type);
    }
    
    public StatLine getStatLine(String name) {
    	return codex.getStatline(name);
    }
    
    public Effect getEffect(String name) {
    	return codex.getEffect(name);
    }
    
    public Requirement getRequirement(String name) {
    	return codex.getRequirement(name);
    }
    
    public OptionChoice getChoice(String name) {
    	return codex.getChoice(name);
    }
    
    public OptionGroup getOptionGroup(String name) {
    	return codex.getGroup(name);
    }
}