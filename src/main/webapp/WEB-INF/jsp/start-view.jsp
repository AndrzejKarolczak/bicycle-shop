<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">

<head>
    <title>Sklep rowerowy</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<jsp:include page="components/header-view.jsp"/>

<br>

<div class="row">
    <div class="container">
        <h1>Strona główna</h1>
        <p>Niestety nie wszystko działa bezbłędnie</p>
        <ul>
            <li>Filtry dla list książek nie uwzględniają wildcards</li>
            <li>Reset filtrów nie zawsze działa - prawdopodobnie trzeba napisać skrypt JavaScript</li>
            <li>W oknie do edyji książek powinny być combo boxy z nazwami autorów i wydawców, a nie pola do podawania id</li>
            <li>Przez powyższy punkt można próbować wstawić id nieistniejącego autora/wydawcy i oczywiście aplikacja padnie</li>
            <li>Nie dodałem filtrów dla autorów i wydawców (jest tylko przycisk Filtruj)</li>
            <li>Nie dodałem strony do wyświetlania błędów</li>
        </ul>

        <p>Mam nadzieję, że to cholerstwo w ogóle ruszy, bo konfigurowanie środowiska deweloperskiego, to był koszmar,
            mimo wykorzystania domyślnych ustawień Eclipse dla projektów WEB. W dodatku aplikację rozwijałem w IntelliJ-u,
            którego lepiej znam. Wysłanie w tym przypadku WAR-a byłoby sensowniejsze, bo wiem, że ruszy na Tomcat-cie</p>
    </div>
</div>
</body>

</html>
