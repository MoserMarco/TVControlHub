window.selectTile = function(index) {
    const boxes = document.querySelectorAll('.tile');
    boxes.forEach(box => box.classList.remove('selected'));
    if (boxes[index]) {
        boxes[index].classList.add('selected');
    }
};



window.selectTile(0);