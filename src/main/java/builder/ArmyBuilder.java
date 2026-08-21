package builder;

import java.util.List;

import roster.Codex;
import roster.Roster;
import roster.ValidationResult;
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
    public ValidationResult addUnit(UnitDescription unit) {
    	return roster.addUnit(unit);
    }
    
    public ValidationResult removeUnit(UnitInstance unit) {
    	return roster.removeUnit(unit);
    }
    
    public UnitInstance getUnitInstance(String id) {
    	return roster.getUnitById(id);
    }
    
    public List<UnitInstance> getUnitInstancesByRole(UnitRole role){
    	return roster.getUnitsByRole(role);
    }
    
    public ValidationResult addModel(
    		UnitInstance unit, 
			ModelInstance model) {
    	return roster.addModel(unit, model);
    }
    
    public ValidationResult removeModel(ModelInstance model) {
    	return roster.removeModel(model);
    }
    
    public ValidationResult selectOption(			
    		OptionOwner owner, 
			OptionChoice choice) {
    	return roster.selectOption(owner, choice);
    }
    
    public int getPointsLimit() {
    	return roster.getPointsLimit();
    }
    
    public int getCurrentPoints() {
    	return roster.getCurrentPoints();
    }
    
    public ValidationResult checkRequirements(
    		OptionChoice choice,
    		OptionOwner owner) {
    	return owner.checkRequirements(choice);
    }
    
    public ValidationResult validateRoster() {
    	return roster.validate();
    }
   
    // ******************************
    // ***** Codex Interactions *****
    // ******************************
    public UnitDescription getUnitDescription(String unitName) {
    	return codex.getUnit(unitName);
    }
    
    public List<UnitDescription> getUnitDescriptionsByRole(UnitRole role){
    	return codex.getUnitsByRole(role);
    }
    
    public ModelDescription getModelDescription(String modelName) {
    	return codex.getModel(modelName);
    }
    
    public WargearDescription getWargearDescription(String gearName) {
    	return codex.getWargear(gearName);
    }
    
    public List<WargearDescription> getWargearDescriptionsByType(WargearType type){
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