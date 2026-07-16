package units.options.effects;

public interface Effect {
	void apply(EffectContext context);
	void remove(EffectContext context);
}
