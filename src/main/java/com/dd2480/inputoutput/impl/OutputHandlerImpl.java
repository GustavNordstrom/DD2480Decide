package com.dd2480.inputoutput.impl;

import com.dd2480.inputoutput.OutputHandler;

public class OutputHandlerImpl implements OutputHandler {
    public OutputHandlerImpl(){}

    @Override
    public OutputHandler print(Object o) {
        System.out.println(o.toString());
        return this;
    }
}
