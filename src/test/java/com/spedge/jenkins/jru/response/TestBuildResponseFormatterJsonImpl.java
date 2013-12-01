package com.spedge.jenkins.jru.response;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

public class TestBuildResponseFormatterJsonImpl 
{
    @Test
    public void testConstructor()
    {
        String blankJson = "{\"attempts\":0,\"buildId\":-1,\"building\":false,\"delay\":0,\"errorCode\":0,\"errorDesc\":\"\",\"format\":\"json\",\"responseCode\":0,\"uuid\":\"\"}";
        String goodJson = "{\"attempts\":3,\"buildId\":40,\"building\":true,\"delay\":1000,\"errorCode\":0,\"errorDesc\":\"OK\",\"format\":\"json\",\"responseCode\":200,\"uuid\":\"12345678-1234-1234-1234-012345678901\"}";
                
        BuildResponseFormatter brf = new BuildResponseFormatterJsonImpl();
        assertEquals("application/json;charset=UTF-8", brf.getContentType());
        assertEquals(blankJson, brf.generateOutput(new BuildResponse()));
        
        BuildResponse br = new BuildResponse();
        br.setAttempts(3);
        br.setBuildId(40);
        br.setBuilding(true);
        br.setDelay(1000);
        br.setErrorCode(0);
        br.setErrorDesc("OK");
        br.setResponseCode(200);
        br.setUuid(UUID.fromString("12345678-1234-1234-1234-012345678901"));
        
        assertEquals(goodJson, brf.generateOutput(br));
    }
}
