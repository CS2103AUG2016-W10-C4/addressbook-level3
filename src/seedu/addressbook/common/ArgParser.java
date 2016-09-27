package seedu.addressbook.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dead simple argument parser
 * 
 * Allows for both positional and named arguments. Positional arguments are 
 * zero-indexed and skip over named arguments, while named arguments are prefixed 
 * with '--'. For example: 
 * 
 *   $ addressbook abc --theme=hotdog def
 *   
 * has two positional arguments - 'abc' at position 0 and 'def' at position 1, and
 * the named argument 'theme' with the value 'hotdog'.  
 */
public class ArgParser {
    private Map<String, String> namedArgs = new HashMap<>();
    private List<String> positionalArgs = new ArrayList<>();
    
    /**
     * Constructs an argparser instance from the args[] passed to main()
     */
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
    
    /**
     * Get the positional argument indexed at position. Position is zero indexed 
     * and skip over named arguments. Null is returned if the argument was not 
     * supplied by the user. 
     */
    public String get(int position) {
        return this.get(position, null);
    }
    
    /**
     * Get the positional argument indexed at position, returning defaultValue
     * if the argument does not exist. Position is zero indexed and skip over 
     * named arguments.  
     */
    public String get(int position, String defaultValue) {
        if (position < this.positionalArgs.size()) {
            return this.positionalArgs.get(position);
        }
        
        return defaultValue;
    }
    
    /**
     * Get the value of a named argument. Null is returned if the argument was not 
     * supplied by the user.
     */
    public String get(String name) {
        return this.get(name, null);
    }

    /**
     * Get the value of a named argument. defaultValue is returned if the argument was not 
     * supplied by the user.
     */
    public String get(String name, String defaultValue) {
        return this.namedArgs.getOrDefault(name, defaultValue);
    }
    
    private boolean isNamed(String arg) {
        return arg.startsWith("--") && arg.contains("=");
    }
}
