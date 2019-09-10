package com.nitkkr.techspardha.root.RegisteredEvents;

import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;

public class Registered {
    private RegisteredEvents_Data data;

    private String success;

    public RegisteredEvents_Data getData ()
    {
        return data;
    }

    public void setData (RegisteredEvents_Data data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }
}
