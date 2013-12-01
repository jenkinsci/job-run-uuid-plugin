package com.spedge.jenkins.jru;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hudson.model.Cause;

import java.util.UUID;

import org.junit.Test;

public class TestBuildIdCause 
{
    @Test
    public void testConstructors()
    {
        // No-arg constructor makes a UUID.
        BuildIdCause build = new BuildIdCause();
        assertNotNull(build.getUuid());
        
        // Arg constructor uses passed in UUID.
        UUID uuid = UUID.randomUUID();
        BuildIdCause build2 = new BuildIdCause(uuid);
        assertEquals(uuid, build2.getUuid());
        
        // Equals is based on UUID in constructor.
        BuildIdCause build3 = new BuildIdCause(uuid);
        assertEquals(build2, build3);
        assertNotEquals(build, build3);
        
        // Equals is based on there being the same type of cause
        Cause cause = new Cause.UserIdCause();
        assertNotEquals(cause, build3);
    }
    
    @Test
    public void testToString()
    {
        UUID uuid = UUID.randomUUID();
        BuildIdCause build = new BuildIdCause(uuid);
        assertEquals("Invoked via job-run-uuid API, UUID : " + uuid, build.getShortDescription());
    }
    
    @Test
    public void testOverride()
    {
        UUID uuid = UUID.randomUUID();
        BuildIdCause build = new BuildIdCause(uuid);
        BuildIdCause build2 = new BuildIdCause(uuid);
        BuildIdCause buildDiff = new BuildIdCause();
        BuildIdCause buildNull = new BuildIdCause(null);
        
        // Confirm HashCode and Equals methods are overriden as expected.
        assertTrue(buildNull.hashCode() != build.hashCode());
        assertTrue(build.equals(build2) && build2.equals(build));
        assertTrue(build.hashCode() == build2.hashCode());
        
        assertFalse(build.equals(buildDiff));
        assertFalse(build.hashCode() == buildDiff.hashCode());
    }
}
