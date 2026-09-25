package roster;

public class CodexInfo {
	private String id, name, path;
	
	public CodexInfo() {};
	
	public CodexInfo(
	        String id,
	        String name,
	        String path) {
		
		this.id = id;
		this.name = name;
		this.path = path;
	}

    @Override
    public String toString() {
        return name;
    }

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
