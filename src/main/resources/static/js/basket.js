function saveItem(productId, name, quantity, price) {
    let item = {id: productId, name: name, quantity: quantity, price: parseFloat(price)};
    localStorage.setItem(productId, JSON.stringify(item));
}

function showAll() {
    let tbody = document.getElementById("list");
    while (tbody.firstChild) tbody.removeChild(tbody.firstChild);
    let basketValue = 0;

    for (let i = 0; i < localStorage.length; i++) {
        let key = localStorage.key(i);
        let item = JSON.parse(localStorage.getItem(key));
        basketValue += (item.quantity * item.price);
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

    document.getElementById("basket-value").value = basketValue;
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

function sendBasketContents() {
    let array = [];

    for (let i = 0; i < localStorage.length; i++) {
        let key = localStorage.key(i);
        let item = JSON.parse(localStorage.getItem(key));
        if (item.quantity > 0) {
            array.push({id: item.id, quantity: item.quantity, price: item.price});
        }
    }

    if (array.length > 0) {

        let basket = JSON.stringify(array)
        let nodes = document.getElementsByName("basketContents");

        for (let i = 0; i < nodes.length; i++) {
            nodes[i].value = basket;
        }
    }
}
