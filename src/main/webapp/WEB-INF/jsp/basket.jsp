<!DOCTYPE HTML>
<html lang="pl">
<head>
    <title>Koszyk</title>
    <META charset="UTF-8">
    <script src="${pageContext.request.contextPath}/js/basket.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/StorageStyle.css">
</head>
<body onload="showAll()">


<h2>Shopping Cart with HTML5 Storage Project</h2>
<p>Insert items and quantity for your shopping cart. </p>
<form name=ShoppingList>

    <div id="main">
        <table>
            <tr>

                <td><b>Item:</b><input type=text name=name></td>
                <td><b>Quantity:</b><input type=text name=data></td>

            </tr>

            <tr>
                <td>
                    <input type=button value="Save" onclick="SaveItem()">
                    <input type=button value="Update" onclick="ModifyItem()">
                    <input type=button value="Delete" onclick="RemoveItem()">
                </td>
            </tr>
        </table>
    </div>

    <div id="items_table">
        <h3>Shopping List</h3>
        <table id=list></table>
        <p>
            <label><input type=button value="Clear" onclick="ClearAll()">
                <i>* Delete all items</i></label>
        </p>
    </div>
</form>

</body>
</html>
