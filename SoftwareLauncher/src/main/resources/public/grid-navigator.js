window.selectTile = function(index) {
    const boxes = document.querySelectorAll('.tile');
    boxes.forEach(box => box.classList.remove('selected'));
    if (boxes[index]) {
        boxes[index].classList.add('selected');
    }
};

window.activateTile = function(index) {
    fetch('http://localhost:8000/api/azione', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ selected: index })
    })
        .then(response => {
            if (!response.ok) throw new Error("Errore nella chiamata API");
            return response.json();
        })
        .then(data => {
            console.log('Risposta server:', data);
        })
        .catch(err => {
            console.error('Errore:', err);
        });
};

window.selectTile(0);