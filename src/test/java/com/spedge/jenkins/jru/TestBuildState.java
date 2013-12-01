package com.spedge.jenkins.jru;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class TestBuildState 
{
    @Test
    public void testConstructor()
    {
        assertEquals(0, BuildState.DATA_OK.getErrorCode());
        assertEquals(HttpServletResponse.SC_OK, BuildState.DATA_OK.getServletResponse());
        assertEquals("OK", BuildState.DATA_OK.getErrorMessage());
    }
}
