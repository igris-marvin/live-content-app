import type { Client } from "@stomp/stompjs";
import { useEffect, useState } from "react";
import stompClient from "../services/stomp";

interface GreetingProps {
    content: string;
    ts: string;
}

export const useLive = () => {

    // STATES
    const [client, setClient] = useState<Client | null>(null);

    const [name, setName] = useState("");
    const [disBtn, setDisBtn] = useState(false);
    // const [connBtn, setConnBtn] = useState(false);
    const [greetList, setGreetList] = useState<GreetingProps[]>([]);

    const [connected, setConnected] = useState(false);

    // HANDLERS
    const handleNameChange = (e: string) => setName(e)
    // const handleDisBtnChange = (e: boolean) => setDisBtn(e)
    // const handleConnBtnChange = (e: boolean) => setConnBtn(e)

    // EFFECTS
    useEffect(
        () => {

            stompClient
                .onConnect = (frame) => {

                    console.log('Connected: ' + frame);

                    // Upon a successful connection, the client subscribes to 
                    // the /topic/greetings destination, where the server will 
                    // publish greeting messages.
                    stompClient
                        .subscribe(
                            '/topic/greetings',
                            (greeting) => {
                                // When a greeting is received on that destination, 
                                // it will append a paragraph element to the DOM to 
                                // display the greeting message.

                                greetList
                                    .push(
                                        JSON
                                            .parse(
                                                greeting.body
                                            ) as GreetingProps
                                    );

                                setGreetList([...greetList]);
                            }
                        );

                    stompClient
                        .subscribe(
                            "/topic/greeting/history",
                            (history) => {

                                const list = JSON
                                    .parse(
                                        history.body
                                    ) as GreetingProps[]

                                setGreetList([...list]);
                            }
                        )

                    setConnected(true);
                }

            setClient(stompClient);

            return () => {
                setClient(null);
                stompClient.deactivate();
            }
        },
        []
    )

    // on client creation connect to web socket
    // useEffect(
    //     () => {

    //         if (client) {
    //             client.activate();
    //         }

    //     },
    //     [client]
    // )

    // fetch history on client activated
    useEffect(
        () => {

            if (connected && client) {
                client
                    .publish(
                        {
                            destination: "/app/history"
                        }
                    )
            }
        },
        [connected, client]
    )

    // print out result
    useEffect(
        () => {
            console.log("Greet List: ", greetList);
        },
        [greetList]
    )

    // FUNCTIONS

    // connect to the web socket
    function connect() {
        if (client) {
            client.activate();
            setDisBtn(true);
        } else {
            console.error("Client not connected to the web socket");
        }
    }

    // disconnect from web socket
    function disconnect() {
        if (client) {
            client.deactivate();
            setDisBtn(false);
            console.log("Disconnected");
        } else {
            console.error("Client not connected to the web socket");
        }
    }

    // send name
    function sendName() {

        // The sendName() function retrieves the name entered by the user and 
        // uses the STOMP client to send it to the /app/hello destination 
        // (where GreetingController.greeting() will receive it).

        if (client?.active) {
            client
                .publish(
                    {
                        destination: "/app/hello",
                        body: JSON
                            .stringify(
                                {
                                    name
                                }
                            )
                    }
                );
        } else {
            console.error("Not connected to the web socket");

        }
    }

    return {
        name, handleNameChange,
        disBtn,
        // connBtn,
        greetList,
        connect, disconnect, sendName
    }
}