import { useEffect, useState } from "react";

interface MessageProps {
    greeting: string;
}

export const useLive = () => {

    // STATES
    const [name, setName] = useState("");
    const [disBtn, setDisBtn] = useState(false);
    const [messageList, setMessageList] = useState<MessageProps[]>([]);

    // HANDLERS
    const handleNameChange = (e: string) => setName(e)
    const handleDisBtnChange = (e: boolean) => setDisBtn(e)

    // EFFECTS
    useEffect(
        () => {
            
            
        },
        []
    )

    return {
        name, handleNameChange,
        disBtn, handleDisBtnChange,
        messageList
    }
}