function returnToChatPage() {
    window.location.href = "/conversations";
}

function sendMessage() {
    const conversationId = document.getElementById("conversation_id").getAttribute("data-conversation_id");
    const messageInput = document.getElementById("message-input");
    const message = messageInput.value.trim();
    if (!message) {
        return;
    }

    // disabling send button until response from server is received
    const sendButton = document.getElementById("send-button");
    sendButton.disabled = true;

    // displaying user message
    const userMessageDiv = document.createElement("div");
    userMessageDiv.className = "message";
    const userMessageSpan = document.createElement("span");
    userMessageSpan.textContent = message;
    userMessageDiv.appendChild(userMessageSpan);
    document.querySelector(".chat-messages").appendChild(userMessageDiv);

    fetch('/chat/send', {
        method: 'POST',
        body: JSON.stringify({
            conversation_id: conversationId,
            message: message
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.text())
    .then(result => {
        messageInput.value = '';

        // displaying server message
        const serverMessageDiv = document.createElement("div");
        serverMessageDiv.className = "message bot-message";
        const serverMessageSpan = document.createElement("span");
        serverMessageSpan.textContent = result;
        serverMessageDiv.appendChild(serverMessageSpan);
        document.querySelector(".chat-messages").appendChild(serverMessageDiv);

        sendButton.disabled = false;
    })
    .catch(error => {
        console.error(error);
        sendButton.disabled = false;
    });
}