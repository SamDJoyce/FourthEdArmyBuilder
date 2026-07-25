package test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import loaders.CodexLoader;
import units.descriptions.wargear.WargearDescription;

public class LoadingTest {
	private final static String wargearLoc = "";
	
	public static void main(String[] args) {
		CodexLoader loader = new CodexLoader();
		List<WargearDescription> loadedGear = new ArrayList<>();
		Path wargearFile = Path.of(wargearLoc);
		
		try {
			loadedGear = loader.loadWargear(wargearFile);
			System.out.println("Gear has been loaded.");
		} catch (Exception e) {
			System.out.println("Loading went wrong");
		}
		for (WargearDescription w : loadedGear) {
			System.out.println(w.toString());
		}
	}
}
