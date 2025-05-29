import { Client } from "@stomp/stompjs";

const ws_endpoint = 'ws://localhost:8080/ws';

const stompClient = new Client(
    {
        brokerURL: ws_endpoint
    }
)

// stompClient.onConnect = (frame) => {
//     // setConnected(true);

//     console.log('Connected: ' + frame);

//     // Upon a successful connection, the client subscribes to 
//     // the /topic/greetings destination, where the server will 
//     // publish greeting messages.
//     stompClient
//         .subscribe(
//             '/topic/greetings',
//             (greeting) => {
//                 // When a greeting is received on that destination, 
//                 // it will append a paragraph element to the DOM to 
//                 // display the greeting message.

//                 // TODO add message to list
//             }
//         );
// }


// ON WEB-SOCKET ERROR
stompClient.onWebSocketError = (error) => {
    console.error(
        'Error with websocket', error
    );
};

// ON STOMP ERROR
stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

// FUNCTIONS

// connect to web socket
// export function connect() {
//     stompClient.activate();
// }

// // disconnect from web socket
// export function disconnect() {
//     stompClient.deactivate();
//     // setConnected(false);
//     console.log("Disconnected");
// }rt function connect() {
//     stompClient.activate();
// }

// // disconnect from web socket
// export function disconnect() {
//     stompClient.deactivate();
//     // setConnected(false);
//     console.log("Disconnected");
// }

export default stompClient;