function SaveItem() {
    let key = document.forms.ShoppingList.name.value; //TODO
    let value = document.forms.ShoppingList.data.value;
    localStorage.setItem(key, value);
    showAll(); // Wywalić
}

function ModifyItem() {
    let key = document.forms.ShoppingList.name.value;
    let value = document.forms.ShoppingList.data.value;

    if (localStorage.getItem(key) != null) {
        //update
        localStorage.setItem(key, value);
        document.forms.ShoppingList.data.value = localStorage.getItem(key);
    }

    showAll();
}

function RemoveItem() {
    let key = document.forms.ShoppingList.name.value;
    document.forms.ShoppingList.data.value = localStorage.removeItem(key);
    showAll();
}

function ClearAll() {
    localStorage.clear();
    showAll();
}

function showAll() {
    if (checkBrowser()) {
        let key = "";
        const HEADER = "<tr><th>Item</th><th>Value</th></tr>\n";
        let list = HEADER;

        for (let i = 0; i < localStorage.length; i++) {
            key = localStorage.key(i);
            list += "<tr><td>" + key + "</td>\n<td>"
                + localStorage.getItem(key) + "</td></tr>\n";
        }

        if (list === HEADER) {
            list += "<tr><td><i>empty</i></td>\n<td><i>empty</i></td></tr>\n";
        }

        document.getElementById('list').innerHTML = list;
    } else {
        alert('Cannot save shopping list as your browser does not support HTML 5');
    }
}

function checkBrowser() {
    return 'localStorage' in window && window['localStorage'] !== null;
}
