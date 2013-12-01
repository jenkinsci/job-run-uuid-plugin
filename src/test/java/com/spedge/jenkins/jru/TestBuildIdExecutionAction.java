package com.spedge.jenkins.jru;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import hudson.model.AbstractProject;
import hudson.model.Job;

import org.easymock.EasyMock;
import org.junit.Test;

public class TestBuildIdExecutionAction 
{
    @Test
    public void testDefaultJenkinsOverrides()
    {
        @SuppressWarnings("rawtypes")
        AbstractProject p = EasyMock.createMock(AbstractProject.class);
        
        BuildIdExecutionAction action = new BuildIdExecutionAction(p);
        
        assertNull(action.getIconFileName());
        assertNull(action.getDisplayName());
        assertTrue(action.isEnabled());
        assertNull(action.getValue("Scots wae hae!"));
        assertEquals("buildId", action.getUrlName());
        assertEquals(Job.BUILD, action.getPermission());
    }
}
