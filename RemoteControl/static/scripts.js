function sendWake() {
    fetch('/wake', { method: 'POST' })
        .then(r => r.json())

}

function sendKey(key) {
    fetch(`/key/${key}`, { method: 'POST' })
        .then(r => r.json())

}
