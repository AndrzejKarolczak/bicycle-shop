function saveItem(productId, name, quantity, price) {
    let item = {id: productId, name: name, quantity: quantity, price: parseFloat(price)};
    localStorage.setItem(productId, JSON.stringify(item));
    //alert("Produkt dodano do koszyka");
}

function showBasketContents() {
    let tbody = document.getElementById("list");
    while (tbody.firstChild) tbody.removeChild(tbody.firstChild);
    let basketValue = 0;

    for (let i = 0; i < localStorage.length; i++) {
        let key = localStorage.key(i);
        let item = JSON.parse(localStorage.getItem(key));
        basketValue += (item.quantity * item.price);
        let row = document.createElement('tr');
        row.setAttribute("id", key);

        row = addColumn(row, `${i + 1}.`, 'rightAlignedColumn');
        row = addColumn(row, item.name, null);
        row = addColumn(row, item.quantity, 'rightAlignedColumn');
        row = addColumn(row, item.price.toFixed(2), 'rightAlignedColumn');
        row = addButtonColumn(row, "+", `addPiece(${key})`, 'buttonColumn', 'btn btn-success btn-sm');
        row = addButtonColumn(row, "-", `subtractPiece(${key})`, 'buttonColumn', 'btn btn-warning btn-sm');
        row = addButtonColumn(row, "x", `removeItem(${key})`, 'buttonColumn', 'btn btn-danger btn-sm');
        tbody.appendChild(row);
    }

    let isBasketEmpty = !(localStorage.length > 0);
    document.getElementById("clear-basket").hidden = isBasketEmpty;
    document.getElementById("submit-order").hidden = isBasketEmpty;
    document.getElementById("basket-value").value = basketValue.toFixed(2);
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

    showBasketContents();
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

    showBasketContents();
}

function removeItem(key) {
    localStorage.removeItem(key);
    showBasketContents();
}

function clearBasketContents() {
    localStorage.clear();
    showBasketContents();
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

function disableSubmitButtonsOnEmptyBasket() {
    let nodes = document.getElementsByName("submit");
    nodes.forEach(e => e.disabled = !(localStorage.length > 0));
}
