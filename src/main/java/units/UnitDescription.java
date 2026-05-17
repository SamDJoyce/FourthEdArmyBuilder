package units;

import java.util.List;
import java.util.Set;

public class UnitDescription {
	
	private String id;
	private String name;
	private Set<UnitType> types;
	private UnitRole 	  role;
	private int basePoints;
	private int minSize;
	private int maxSize;
	private List<OptionGroup> options;
	
	
	
	private UnitDescription() {
	}
	
	public static class Builder {
		private String id;
		private String name;
		private Set<UnitType> types;
		private UnitRole 	  role;
		private int basePoints;
		private int minSize;
		private int maxSize;
		private List<OptionGroup> options;
		
		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setTypes(Set<UnitType> types) {
			this.types = types;
			return this;
		}

		public Builder setRole(UnitRole role) {
			this.role = role;
			return this;
		}

		public Builder setBasePoints(int basePoints) {
			this.basePoints = basePoints;
			return this;
		}

		public Builder setMinSize(int minSize) {
			this.minSize = minSize;
			return this;
		}

		public Builder setMaxSize(int maxSize) {
			this.maxSize = maxSize;
			return this;
		}

		public Builder setOptions(List<OptionGroup> options) {
			this.options = options;
			return this;
		}
		
		public UnitDescription build() {
			UnitDescription u = new UnitDescription();
			
			u.id = this.id;
			u.name = this.name;
			u.types = this.types;
			u.role = this.role;
			
			
			return u;
		}
		
	}

}
