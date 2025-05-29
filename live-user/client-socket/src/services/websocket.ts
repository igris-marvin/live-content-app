import { Client } from "@stomp/stompjs";

export const createClient = (
    connect: (e: boolean) => void,
    userId: string,
    callback: (e: string) => void
) => {

    const client = new Client(
        {
            brokerURL: "ws://localhost:9090/ws",
            onDisconnect(frame) {
                console.log(
                    "on disconnect: ",
                    frame
                );

            },
            onStompError(frame) {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            },
            onWebSocketError(error) {
                console.error(
                    'Error with websocket', error
                );
            }
        }
    );

    client.onConnect = (frame) => {
        console.log("Connected: " + frame);

        // update connected state
        connect(true);

        // destination
        const destination: string = `/queue/updates/${userId}`;

        console.log("Destination: ", destination);

        // subscribe - app
        client
            .subscribe(
                destination,
                (msg) => {
                    console.log("Data Received: " + msg);
                    
                    callback(msg.body);
                }
            );
    }

    return client;

}
