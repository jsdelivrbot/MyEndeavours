package com.emerson.bpel.sensor.action;

import com.oracle.bpel.sensor.DataPublisher;
import com.oracle.bpel.sensor.schemas.ITHeaderInfo;
import com.oracle.bpel.sensor.schemas.ITSensorAction;
import com.oracle.bpel.sensor.schemas.ITSensorActionData;

import com.oracle.bpel.sensor.schemas.ITSensorData;

import org.w3c.dom.Element;

public class MySensorAction implements DataPublisher{
    public MySensorAction() {
    }

    public void publish(ITSensorAction itSensorAction, 
                        ITSensorActionData itSensorActionData, 
                        Element element) {
        
        System.out.println("************************* Sensor Custom Action Start *************************");
        ITHeaderInfo header = itSensorActionData.getHeader();
        ITSensorData payload = itSensorActionData.getPayload();
        System.out.println("Instance ID of process in which Logged - "+header.getInstanceId());
        System.out.println("List of variable data - "+payload.getVariableData());
        System.out.println("Sensor Data: "+element.toString());
        
        System.out.println("************************* Sensor Custom Action End *************************");
    }
}
