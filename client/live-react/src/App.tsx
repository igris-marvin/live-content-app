import { CompatClient, Stomp } from "@stomp/stompjs";
import { useEffect, useState } from "react"
import SockJS from "sockjs-client"
import { checkWebSocket } from "./services/check_websocket_service";

interface MessageProps {
  nickname: string;
  content: string;
  timestamp: string;
}

function App() {

  // STATES
  const [messages, setMessages] = useState<MessageProps[]>([]);
  const [message, setMessage] = useState('hhbh');
  const [nickname, setNickname] = useState('kpkp');
  const [stompClient, setStompClient] = useState<CompatClient | null>(null);

  // EFFECTS
  useEffect(() => {
    checkWebSocket(); // Ensure WebSocket service is checked before initializing

    const WS_EP = "http://localhost:8080/ws";
    const client = Stomp.over(() => new SockJS(WS_EP, null, { transports: ["websocket", "xhr-streaming", "xhr-polling"] }));

    client.reconnectDelay = 1000; // Automatically retry connection every 5 sec

    client.onConnect = () => {
        console.log("✅ WebSocket connected!");

        // Subscribe ONLY after successful STOMP connection
        client.subscribe("/topic/messages", (message) => {
            const receivedMessage = JSON.parse(message.body);
            setMessages((prevMessages) => [...prevMessages, receivedMessage]);
        });
    };

    client.onWebSocketClose = (event) => {
        console.warn("⚠️ WebSocket closed:", event);

        // Manually trigger reconnect only if inactive
        if (!client.connected) {
            console.log("🔄 Attempting WebSocket reconnect...");
            setTimeout(() => client.activate(), 3000); // Retry after 3 sec
        }
    };

    client.onStompError = (frame) => {
        console.error("❌ STOMP error:", frame);
    };

    client.activate(); // Start WebSocket connection

    setStompClient(client);

    return () => {
        if (client.connected) {
            client.deactivate(); // Ensure proper cleanup on unmount
        }
    };
}, []);




  // FUNCTIONS
  const sendMessage = () => {
    if (!message.trim() || !nickname.trim()) {
      console.warn("Message or nickname is empty");
      return;
    }

    if (stompClient?.connected) {
      const chatMessage = { nickname, content: message };

      stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
      setMessage(""); // Clear input after sending
    } else {
      console.error("STOMP client is not connected!");
    }
  };


  return (
    <>
      <div>
        {
          messages
            .map(
              (m) => {
                return (
                  <>
                    <div>
                      <span>Nickname: {m.nickname}</span>
                      <div>
                        Message: {m.content}
                      </div>
                    </div>
                  </>
                )
              }
            )
        }

        <input
          type="text"
          onChange={(e) => setNickname(e.target.value)}
          value={nickname}
          placeholder="Nickname"
        />
        <input
          type="text"
          onChange={(e) => setMessage(e.target.value)}
          value={message}
          placeholder="Content"
        />
        <button
          onClick={sendMessage}
        >
          Send
        </button>
      </div>
    </>
  )
}

export default App
