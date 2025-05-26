const stompClient = new StompJs.Client(
    {
        // stompClient is initialized with brokerURL referring to 
        // path /gs-guide-websocket, which is where our websockets 
        // server waits for connections.
        brokerURL: 'ws://localhost:8080/gs-guide-websocket'
    }
);

// STOMP - ON CONNECT
stompClient.onConnect = (frame) => {

    setConnected(true);

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
                showGreeting(JSON.parse(greeting.body).content);
            }
        );
};

// WEB SOCKET ERROR
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

// ON STOMP ERROR
stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

// HANDLERS
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

// send name
function sendName() {
    
    // The sendName() function retrieves the name entered by the user and 
    // uses the STOMP client to send it to the /app/hello destination 
    // (where GreetingController.greeting() will receive it).
    stompClient
        .publish(
            {
                destination: "/app/hello",
                body: JSON.stringify({ 'name': $("#name").val() })
            }
        );
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => sendName());
});