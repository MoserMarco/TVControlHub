function sendWake() {
    fetch('/wake', { method: 'POST' })
        .then(r => r.json())

}

function sendKey(key) {
    fetch(`/key/${key}`, { method: 'POST' })
        .then(r => r.json())

}

function mouse(){
    window.location.href = "/mouse";
}
function remote(){
    window.location.href = "/";
}