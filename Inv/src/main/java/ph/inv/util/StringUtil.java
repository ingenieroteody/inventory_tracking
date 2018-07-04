package ph.inv.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.util.MultiValueMap;

public class StringUtil {

	/**
	 * No longer useful because bootgrid has conflict with bootstrap 4
	 * @param params
	 * @return
	 */
	@Deprecated
	public static Map<String,String> parsePaginationParameters(MultiValueMap<String, String> params) {
		Map<String,String> parameters = new HashMap<String,String>();
		Iterator<String> i = params.keySet().iterator();
		    
	    while(i.hasNext()) {
	    	String key = i.next();
	    	if(key.contains("sort[") && key.contains("]")) 
	    		parameters.put("sort", key.substring(key.indexOf("[")+1, key.indexOf("]")) + " " + params.getFirst(key));
	    	 else 
	    		parameters.put(key, params.getFirst(key));
	    }
		return parameters;
	}
	
	public static String parseClassname(Class c) {
		return c.getSimpleName().replace("Controller", "").toLowerCase();
	}
}
