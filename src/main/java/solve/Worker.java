package solve;

import akka.actor.UntypedActor;

public class Worker extends UntypedActor {
    @Override
    public void onReceive(Object msg) {
        Integer num = (Integer) msg;
        if (num % 3 == 0 || num % 5 == 0) {
            getSender().tell(num, getSelf());
        } else {
            getSender().tell(new Integer(0), getSelf());
        }
    }
}
