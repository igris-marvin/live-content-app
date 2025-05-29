import { useEffect, useState } from "react";
import { createClient } from "../services/websocket";


export const useApp = () => {

    // STATES
    const [data, setData] = useState<string>("Waiting for updates...");
    const userId = "123"; // Replace with actual user ID

    const [conn, setConn] = useState(false);


    // HANDLERS
    const handleDataChange = (e: string) => setData(e)
    const handleConnChange = (e: boolean) => setConn(e)

    // EFFECTS
    useEffect(
        () => {
            const client = createClient(
                handleConnChange,
                userId,
                handleDataChange
            );

            client.activate();

            return () => {
                client.deactivate();
            }
        },
        []
    )

    useEffect(
        () => {
            console.log("Connected: ", conn);

        },
        [conn]
    )

    return {
        data,
    }

}
// create function to create client
// create client
// send user id and pass to on connect
// update connected state on on connect
// activate client
