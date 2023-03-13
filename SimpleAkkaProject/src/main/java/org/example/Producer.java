package org.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Hello world!
 *
 */
public class Producer
{
    public static void main( String[] args ){
        // First we create an actor system to manage all actors
        ActorSystem system = ActorSystem.create("generate-numbers-one-to-ten");
        // Then we create an actor using actor system's actorOf method
        // Inside actorOf method we use Props.create method to configure
        // how we want to create our actor. Below we want to create an
        // actor that uses Consumer class's createReceive() method.
        // system.actorOf() will return an ActorRef object, not the actor itself
        // ActorRef is used as a proxy between other actors, objects and actor, and it
        // send messages to the actor's mailbox
        ActorRef printNumbersConsumer = system.actorOf(Props.create(Consumer.class));

        for (int i = 1 ; i <= 10; i++){
            System.out.println(">>> Producing & sending a number " + i);
            // ActorRef object of the actor will send numebrs to the actor
            // tell method won't expect any response so 2. parameter is set to ActorRef.noSender()
            // First parameter is the message itself
            printNumbersConsumer.tell(i, ActorRef.noSender());
        }

        // we completed our mission and send the messages to the actor and the actor
        // received the message through ActorRef of that actor and print it.
        // Thus we can shut down the actor system
        system.terminate();
        System.out.println("==== Finished producing & sending a numbers 1 to 10");
    }

}
