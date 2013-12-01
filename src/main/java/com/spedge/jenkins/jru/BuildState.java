package com.spedge.jenkins.jru;

import javax.servlet.http.HttpServletResponse;

/**
 * This is an enumerator that contains as many of the possible scenarios for the
 * build API.
 * 
 * @author Stuart Davidson
 * 
 */
public enum BuildState
{
    // TODO Need to get these out into a i18n message file of their own.
    DATA_OK(0, "OK", HttpServletResponse.SC_OK), 
    DATA_NOT_EXECUTING(1, "Could not retrieve build details - the job is still expecting an executor. Without the build being started, a number is not assigned to it.", HttpServletResponse.SC_EXPECTATION_FAILED), 
    DATA_BAD_UUID(2, "There is no build associated with this UUID. Confirm it is correct.", HttpServletResponse.SC_BAD_REQUEST), 
    DATA_NO_UUID(3, "There is no uuid parameter in the URL - this method requires it.", HttpServletResponse.SC_BAD_REQUEST), 
    NOT_BUILDABLE(4, "This job is not buildable at this time.", HttpServletResponse.SC_INTERNAL_SERVER_ERROR), 
    ALREADY_QUEUED(5, "There is already a job of this type queued. We cannot queue another.", HttpServletResponse.SC_CONFLICT),
    DATA_NOT_CREATED(6, "This submission is still being processed by Jenkins. Please increase the delay and attempts to give Jenkins time to queue this build.", HttpServletResponse.SC_REQUEST_TIMEOUT);

    private String message;
    private int errorCode;
    private int sr;

    BuildState(int errorCode, String message, int sr)
    {
        this.errorCode = errorCode;
        this.message = message;
        this.sr = sr;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getErrorMessage()
    {
        return message;
    }

    public int getServletResponse()
    {
        return sr;
    }
}
