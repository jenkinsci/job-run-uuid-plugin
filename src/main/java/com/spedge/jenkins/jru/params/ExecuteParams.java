package com.spedge.jenkins.jru.params;

import hudson.model.ParameterValue;
import hudson.model.StringParameterValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.kohsuke.stapler.StaplerRequest;

/**
 * We use this class to parse the StaplerRequest
 * and retrieve the parameters we want from it.
 * 
 * @author Stuart Davidson
 *
 */
public class ExecuteParams 
{
    private static final String DELAY_NAME = "delay";
    private static final String ATTEMPTS_NAME = "attempts";
    private static final String FORMAT_NAME = "format";
    private static final String UUID_NAME = "uuid";
    
    private static final int DELAY_DEFAULT = 1000;
    private static final int ATTEMPTS_DEFAULT = 3;
    private static final String FORMAT_DEFAULT = "json";

    private int attempts = 0;
    private int delay = 0;
    private String format;
    private UUID uuid = null;
    private List<ParameterValue> values;
    
    public ExecuteParams(StaplerRequest req)
    {
        // Sort out the parameters we will work with.
        this.delay = NumberUtils.toInt(req.getParameter(DELAY_NAME), DELAY_DEFAULT);
        this.attempts = NumberUtils.toInt(req.getParameter(ATTEMPTS_NAME), ATTEMPTS_DEFAULT);
        this.format = StringUtils.defaultIfEmpty(req.getParameter(FORMAT_NAME), FORMAT_DEFAULT);
        
        if(req.getParameter(UUID_NAME) != null)
        {
            try
            {
                this.uuid = UUID.fromString(req.getParameter(UUID_NAME));
            }
            catch(IllegalArgumentException iae) { uuid = null; }
        }
        
        // These are the parameters that have been passed through that don't touch the plugin.
        this.values = new ArrayList<ParameterValue>();
        
        @SuppressWarnings("unchecked")
        Map<String, String[]> names = req.getParameterMap();
        Iterator<String> nameIterator = names.keySet().iterator();
        
        while(nameIterator.hasNext())
        {
            String name = nameIterator.next();
            for(String value : names.get(name))
            {
                values.add(new StringParameterValue(name, value));
            }
        }
    }
    
    public int getAttempts()
    {
        return attempts;
    }
    
    public int getDelay()
    {
        return delay;
    }
    
    public String getFormat()
    {
        return format;
    }
    
    public UUID getUUID()
    {
        return uuid;
    }
    
    public void setUUID(UUID uuid)
    {
        this.uuid = uuid;
    }
    
    public List<ParameterValue> getParameters()
    {
        List<ParameterValue> currentParams = new ArrayList<ParameterValue>(values);
        currentParams.add(new StringParameterValue(DELAY_NAME, "" + delay));
        currentParams.add(new StringParameterValue(ATTEMPTS_NAME, "" + attempts));
        currentParams.add(new StringParameterValue(FORMAT_NAME, "" + format));
        currentParams.add(new StringParameterValue(UUID_NAME, "" + uuid));
        
        return currentParams;
    }
    
    @Override
    public String toString()
    {
        return "Delay : " + delay + ", Attempts : " + attempts + ", Format : " + format + ", UUID : " + ((uuid != null) ? uuid.toString() : "null");
    }
}
