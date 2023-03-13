package org.example;

import akka.actor.AbstractActor;
import akka.actor.UntypedAbstractActor;

public class Consumer extends AbstractActor {
    // this method specifies how actor behaves as a response to the received message
    // match method tells the actor that it should only accetp Integer.class type messages
    // and discard others. Then it will explain to the actor what should the actor do with that message
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, msg -> System.out.println("<<< Receiving & printing " + msg))
                .build();
    }
}
