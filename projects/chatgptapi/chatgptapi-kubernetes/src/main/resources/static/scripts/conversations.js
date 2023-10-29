function createNewConversation() {
    window.location.href = "/chat/new";
}
function showConversation(conversationId) {
    window.location.href = "/chat/" + conversationId;
}
function deleteConversation(conversationId) {
    fetch('/chat/delete', {
        method: 'POST',
        body: JSON.stringify({
            conversation_id: conversationId,
        }),
        headers: {
            // TODO: Add authentication from user here
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.text())
    .then(result => {
        window.location.href = "/conversations";
    })
    .catch(error => {
        console.error(error);
    });
}