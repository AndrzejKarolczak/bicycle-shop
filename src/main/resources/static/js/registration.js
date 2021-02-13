function enablePasswords(suffix) {
    let checkBox = document.getElementById(`register-me-${suffix}`);
    let passwordFirst = document.getElementById(`password-first-${suffix}`);
    let passwordSecond = document.getElementById(`password-second-${suffix}`);

    if (checkBox.checked === true) {
        passwordFirst.disabled = false;
        passwordFirst.required = true;
        passwordSecond.disabled = false;
        passwordSecond.required = true;
    } else {
        passwordFirst.disabled = true;
        passwordFirst.required = false;
        passwordSecond.disabled = true;
        passwordSecond.required = false;
    }
}

function showShippingAddress(suffix) {
    let checkBox = document.getElementById(`include-shipping-${suffix}`);
    let shippingAddressPanel = document.getElementById(`shipping-address-group-${suffix}`);

    if (checkBox.checked === true) {
        shippingAddressPanel.style.display = "block";
        changeState(true, suffix);
    } else {
        shippingAddressPanel.style.display = "none";
        changeState(false, suffix);
    }
}

function changeState(value, suffix) {
    document.getElementById(`shipping-first-name-${suffix}`).required = value;
    document.getElementById(`shipping-last-name-${suffix}`).required = value;
    document.getElementById(`shipping-street-${suffix}`).required = value;
    document.getElementById(`shipping-building-number-${suffix}`).required = value;
    document.getElementById(`shipping-city-${suffix}`).required = value;
    document.getElementById(`shipping-postal-code-${suffix}`).required = value;
    document.getElementById(`shipping-country-${suffix}`).required = value;
}
