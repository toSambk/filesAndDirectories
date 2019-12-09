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
                filesLoaded(response);
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
    var table = document.createElement("table");
    var mainRow = document.createElement("tr");
    var tdFiles = document.createElement("td");
    var tdSize = document.createElement("td");
    tdFiles.textContent = "Файлы";
    tdSize.textContent = "Размер";
    mainRow.appendChild(tdFiles);
    mainRow.appendChild(tdSize);
    table.appendChild(mainRow);
    for (var index = 0; index < files.length; index++) {
        var file = files[index];
        var name = file.filename;
        var size = file.size;
        var tr = createTransactionRow(name, size);
        table.appendChild(tr);
    }
    div.appendChild(table);
}



function createTransactionRow(name, size) {
    var tr = document.createElement("tr");
    var nameCell = document.createElement("td");
    var sizeCell = document.createElement("td");
    nameCell.textContent = name;
    sizeCell.textContent = size;
    tr.appendChild(nameCell);
    tr.appendChild(sizeCell);
    return tr;
}

function filesLoadFailed() {
        var div = document.getElementById("modal-content");
        clearNode(div);
        var errorMessageCell = document.createElement("P");
        errorMessageCell.appendChild(document.createTextNode("Ошибка загрузки списка файлов"));
        div.appendChild(errorMessageCell);
}

