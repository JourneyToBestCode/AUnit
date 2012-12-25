/* 
 * Created : Dec 26, 2012 
 * 
 * Copyright (c) 2012 Ericsson AB, Sweden. 
 * All rights reserved. 
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden. 
 * The program(s) may be used and/or copied with the written permission from Ericsson AB 
 * or in accordance with the terms and conditions stipulated in the agreement/contract 
 * under which the program(s) have been supplied. 
 */
package listener;

import java.util.concurrent.atomic.AtomicInteger;

import event.Event;
import event.Events;

/**
 * @author eyanjsh
 *
 */
public class PrintListener implements Listener
{

    /*
     * (non-Javadoc)
     * 
     * @see listener.Listener#handle(event.Event)
     */
    private AtomicInteger runCases = new AtomicInteger(0);

    private AtomicInteger successCases = new AtomicInteger(0);

    private AtomicInteger failedCases = new AtomicInteger(0);

    @Override
    public void handle(Event e)
    {
        if (Events.START_RUN_CLASS.equals(e.getEventType()))
        {
            System.out.println(e.getMessage());
        }
        else
            if (Events.START_RUN_METHOD.equals(e.getEventType()))
            {
                runCases.incrementAndGet();
                System.out.println(e.getMessage());
            }
            else
                if (Events.SUCCESS_RUN_METHOD.equals(e.getEventType()))
                {
                    successCases.incrementAndGet();
                    System.out.println(e.getMessage());
                }
                else
                    if (Events.FINISH_RUN_CLASS.equals(e.getEventType()))
                    {
                        System.out.println(e.getMessage());
                        String staticsMsg = "Run " + runCases.get() + " cases, " + successCases.get() + " succeed, " + failedCases.get() + " failed";
                        System.out.println(staticsMsg);
                    }
                    else
                        if (Events.FAILED_RUN_METHOD.equals(e.getEventType()))
                        {
                            failedCases.incrementAndGet();
                            System.out.println(e.getMessage());
                        }
                        else
                            if (Events.FAILED_RUN_CLASS.equals(e.getEventType()))
                            {
                                System.out.println(e.getMessage());
                            }

    }

}
