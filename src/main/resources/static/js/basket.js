function saveItem(productId, name, quantity, price) {
    let item = {id: productId, name: name, quantity: quantity, price: parseFloat(price)};
    localStorage.setItem(productId, JSON.stringify(item));
}

function showAll() {
    if (checkBrowser()) {
        let tbody = document.getElementById("list");
        while (tbody.firstChild) tbody.removeChild(tbody.firstChild);

        for (let i = 0; i < localStorage.length; i++) {
            let key = localStorage.key(i);
            let item = JSON.parse(localStorage.getItem(key));

            let row = document.createElement('tr');
            row.setAttribute("id", key);

            row = addColumn(row, `${i + 1}.`, 'numberColumn');
            row = addColumn(row, item.name, null);
            row = addColumn(row, item.quantity, 'numberColumn');
            row = addColumn(row, item.price, 'numberColumn');
            row = addButtonColumn(row, "+", `addPiece(${key})`, 'buttonColumn', 'btn btn-success btn-sm');
            row = addButtonColumn(row, "-", `subtractPiece(${key})`, 'buttonColumn', 'btn btn-warning btn-sm');
            row = addButtonColumn(row, "x", `removeItem(${key})`, 'buttonColumn', 'btn btn-danger btn-sm');
            tbody.appendChild(row);
        }

    } else {
        alert('Cannot save shopping list as your browser does not support HTML 5');
    }
}

function checkBrowser() {
    return 'localStorage' in window && window['localStorage'] !== null;
}

function addColumn(row, innerText, className) {
    let column = document.createElement('td');
    column.innerText = innerText;
    if (className !== null) column.className = className;
    row.appendChild(column);
    return row;
}

function addButtonColumn(row, value, action, columnClassName, buttonClassName) {
    let column = document.createElement('td');
    column.className = columnClassName;

    let button = document.createElement('input');
    button.setAttribute('type', 'button');
    button.setAttribute('onclick', action);
    button.setAttribute('value', value);
    button.setAttribute('class', buttonClassName);

    column.appendChild(button);
    row.appendChild(column);
    return row;
}

function addPiece(key) {
    let item = JSON.parse(localStorage.getItem(key));

    if (item !== null) {
        let quantity = item.quantity;
        item.quantity = quantity + 1;
        localStorage.setItem(key, JSON.stringify(item));
    }

    showAll();
}

function subtractPiece(key) {
    let item = JSON.parse(localStorage.getItem(key));

    if (item !== null) {
        let quantity = item.quantity;
        if (quantity !== 0) {
            item.quantity = quantity - 1;
            localStorage.setItem(key, JSON.stringify(item));
        }
    }

    showAll();
}

function removeItem(key) {
    localStorage.removeItem(key);
    showAll();
}

function clearAll() {
    localStorage.clear();
    showAll();
}
