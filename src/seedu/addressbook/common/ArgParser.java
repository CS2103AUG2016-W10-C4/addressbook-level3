package seedu.addressbook.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dead simple argument parser
 */
public class ArgParser {
    private Map<String, String> namedArgs = new HashMap<>();
    private List<String> positionalArgs = new ArrayList<>();
    
    public ArgParser(String[] args) {
        for (String arg : args) {
            if (this.isNamed(arg)) {
                String[] parts = arg.split("=", 2);
                this.namedArgs.put(parts[0].substring(2), parts[1]);
            } else {
                this.positionalArgs.add(arg);
            }
        }
    }
    
    public String get(int position) {
        return this.get(position, null);
    }
    
    public String get(int position, String defaultValue) {
        if (position < this.positionalArgs.size()) {
            return this.positionalArgs.get(position);
        }
        
        return defaultValue;
    }
    
    public String get(String name) {
        return this.get(name, null);
    }
    
    public String get(String name, String defaultValue) {
        return this.namedArgs.getOrDefault(name, defaultValue);
    }
    
    private boolean isNamed(String arg) {
        return arg.startsWith("--") && arg.contains("=");
    }
}
