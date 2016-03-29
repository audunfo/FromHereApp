package no.audunfo.fromhereapp.common.events;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Simple singleton holder for Otto {@link Bus}.
 *
 * @author Audun Follegg (audunfo@gmail.com)
 */
public class EventBus {

    public static Bus eventBus;

    public static Bus getEventBus() {
        if(eventBus == null){
            eventBus = new Bus(ThreadEnforcer.ANY);
        }
        return eventBus;
    }
}
