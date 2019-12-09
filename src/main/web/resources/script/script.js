window.onclick = function(event) {
    var modal = document.getElementById('customModal');
    if (event.target === modal) {
        modal.style.display = "none";
    }
}
function closeModal() {
    var modal = document.getElementById('customModal');
    modal.style.display = "none";
}

function loadFiles(dirId) {
    var modal = document.getElementById('customModal');
    modal.style.display = "block";
    var request = new XMLHttpRequest();
    request.open("get", "api/files/find?rootDirId=" + dirId, true);
    request.onreadystatechange = function (ev) {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var response = JSON.parse(request.responseText);
                filesLoaded(response.content);
            } else {
                filesLoadFailed();
            }
        }
    };
    request.send();
}

function clearNode(node) {
    while (node.hasChildNodes()) {
        node.removeChild(node.firstChild);
    }
}

function printHeader(headerText) {
    var header = document.getElementById('header');
    clearNode(header);
    var h2 = document.createElement("H2");
    h2.appendChild(document.createTextNode(headerText));
    header.appendChild(h2);
}

function filesLoaded(files) {
    var div = document.getElementById("modal-content");
    clearNode(div);



}

function filesLoadFailed() {
        var div = document.getElementById("modal-content");
        clearNode(div);
        var errorMessageCell = document.createElement("P");
        errorMessageCell.appendChild(document.createTextNode("Ошибка загрузки списка файлов"));
        div.appendChild(errorMessageCell);
}

