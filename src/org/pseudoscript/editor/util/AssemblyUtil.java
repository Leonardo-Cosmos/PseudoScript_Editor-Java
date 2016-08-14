package org.pseudoscript.editor.util;

import java.util.HashMap;
import java.util.Map;

import org.pseudoscript.assembly.Assembly;

public final class AssemblyUtil {

	private static Map<String, Assembly> assemblyMap = new HashMap<>();
	
	public static void addAssembly(String executorName, Assembly assembly) {
		assemblyMap.put(executorName, assembly);
	}
	
	public static void remove(String executorName) {
		assemblyMap.remove(executorName);
	}
	
}
