package com.spedge.jenkins.jru.params;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import hudson.model.StringParameterValue;

import org.easymock.EasyMock;
import org.junit.Test;
import org.kohsuke.stapler.StaplerRequest;

public class TestExecuteParams 
{
    @Test
    public void testBlankRequest()
    {
        StaplerRequest sr = EasyMock.createMock(StaplerRequest.class);
        
        EasyMock.expect(sr.getParameter("delay")).andReturn(null);
        EasyMock.expect(sr.getParameter("attempts")).andReturn(null);
        EasyMock.expect(sr.getParameter("format")).andReturn(null);
        EasyMock.expect(sr.getParameter("uuid")).andReturn(null);
        EasyMock.replay(sr);
        
        ExecuteParams params = new ExecuteParams(sr);
        
        assertEquals(1000, params.getDelay());
        assertEquals(3, params.getAttempts());
        assertEquals("json", params.getFormat());
        assertNull(params.getUUID());
        
        assertEquals(4, params.getParameters().size());
        
        assertEquals("delay", ((StringParameterValue) params.getParameters().get(0)).getName());
        assertEquals("attempts", ((StringParameterValue) params.getParameters().get(1)).getName());
        assertEquals("format", ((StringParameterValue) params.getParameters().get(2)).getName());
        assertEquals("uuid", ((StringParameterValue) params.getParameters().get(3)).getName());
        
        assertEquals("1000", ((StringParameterValue) params.getParameters().get(0)).value);
        assertEquals("3", ((StringParameterValue) params.getParameters().get(1)).value);
        assertEquals("json", ((StringParameterValue) params.getParameters().get(2)).value);
        assertEquals("null", ((StringParameterValue) params.getParameters().get(3)).value);
        
        assertEquals("Delay : 1000, Attempts : 3, Format : json, UUID : null", params.toString());
    }
    
    @Test
    public void testBadRequest()
    {
        StaplerRequest sr = EasyMock.createMock(StaplerRequest.class);
        
        EasyMock.expect(sr.getParameter("delay")).andReturn("2a4");
        EasyMock.expect(sr.getParameter("attempts")).andReturn("1f6");
        EasyMock.expect(sr.getParameter("format")).andReturn("");
        EasyMock.expect(sr.getParameter("uuid")).andReturn("1.4").times(2);
        EasyMock.replay(sr);
        
        ExecuteParams params = new ExecuteParams(sr);
        
        assertEquals(1000, params.getDelay());
        assertEquals(3, params.getAttempts());
        assertEquals("json", params.getFormat());
        assertNull(params.getUUID());
        
        assertEquals("1000", ((StringParameterValue) params.getParameters().get(0)).value);
        assertEquals("3", ((StringParameterValue) params.getParameters().get(1)).value);
        assertEquals("json", ((StringParameterValue) params.getParameters().get(2)).value);
        assertEquals("null", ((StringParameterValue) params.getParameters().get(3)).value);
        
        assertEquals("Delay : 1000, Attempts : 3, Format : json, UUID : null", params.toString());
    }
    
    @Test
    public void testGoodRequest()
    {
        StaplerRequest sr = EasyMock.createMock(StaplerRequest.class);
        
        EasyMock.expect(sr.getParameter("delay")).andReturn("24");
        EasyMock.expect(sr.getParameter("attempts")).andReturn("16");
        EasyMock.expect(sr.getParameter("format")).andReturn("yaml");
        EasyMock.expect(sr.getParameter("uuid")).andReturn("12345678-1234-1234-1234-012345678901").times(2);
        EasyMock.replay(sr);
        
        ExecuteParams params = new ExecuteParams(sr);
        
        assertEquals(24, params.getDelay());
        assertEquals(16, params.getAttempts());
        assertEquals("yaml", params.getFormat());
        assertEquals(UUID.fromString("12345678-1234-1234-1234-012345678901"), params.getUUID());
        
        assertEquals("24", ((StringParameterValue) params.getParameters().get(0)).value);
        assertEquals("16", ((StringParameterValue) params.getParameters().get(1)).value);
        assertEquals("yaml", ((StringParameterValue) params.getParameters().get(2)).value);
        assertEquals("12345678-1234-1234-1234-012345678901", ((StringParameterValue) params.getParameters().get(3)).value);
        
        assertEquals("Delay : 24, Attempts : 16, Format : yaml, UUID : 12345678-1234-1234-1234-012345678901", params.toString());
    }
}
