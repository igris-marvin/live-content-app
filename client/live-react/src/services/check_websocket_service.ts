

export const checkWebSocket = () => {
    
    const socket = new WebSocket("ws://localhost:8080/ws");
    
    socket.onopen = () => console.log("WebSocket connected!");
    socket.onmessage = (msg) => console.log("Received:", msg.data);
    socket.onerror = (err) => console.error("WebSocket Error:", err);
    socket.onclose = () => console.warn("WebSocket closed");
}
