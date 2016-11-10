package solve;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;

public class Master extends UntypedActor {
    public static final int N = 1000;

    private static int actorsLeft;
    private static int sum = 0;

    @Override
    public void preStart() {
        actorsLeft = 0;
        for (int i = 1; i < N; i++) {
            final ActorRef worker = getContext().actorOf(Props.create(Worker.class), "worker" + i);
            worker.tell(i, getSelf());
            actorsLeft++;
        }
    }

    @Override
    public void onReceive(Object num) {
        actorsLeft--;
        sum += (Integer) num;
        if (actorsLeft <= 0) {
            System.out.println(sum);
            getContext().stop(getSelf());
        }
    }
}
