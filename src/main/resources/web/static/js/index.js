const tableId = "historyTable";
const dataUrl = "/api/history/short-info"

let table = document.getElementById(tableId);
init();

function sort() {
    let sortSelect = document.getElementById("sortSelect");
    let sortCol = sortSelect.value;
    let emitentTitle = document.getElementById("emitentTitle").value;
    let beginDate = document.getElementById("beginDate").value;
    let endDate = document.getElementById("endDate").value;

    let xhr = new XMLHttpRequest();
    let url = dataUrl.concat(`?sortCol=${sortCol}&emitentTitle=${emitentTitle}&beginDate=${beginDate}&endDate=${endDate}`);
    xhr.open('get', url);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = xhrStateChange;
}

function init() {
    let xhr = new XMLHttpRequest();
    xhr.open('get', dataUrl);
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    xhr.onreadystatechange = xhrStateChange;
}

function xhrStateChange() {
    if (this.readyState != 4) {
        return;
    }
    let data = JSON.parse(this.responseText);
    initForm(data);
    initTable(data);
}

function initForm(data) {
    let sortSelect = document.getElementById("sortSelect");
    let keys = Object.keys(data[0]);
    for (let key of keys) {
        let option = document.createElement("option");
        option.setAttribute('label', key);
        option.setAttribute('value', key);
        sortSelect.appendChild(option);
    }
}

function initTable(data) {
    table.innerHTML = "";
    table.appendChild(initTHead(data));
    table.appendChild(initTBody(data));
}

function initTHead(data) {
    let keys = Object.keys(data[0]);

    let thead = document.createElement("thead");
    let header = document.createElement("tr");
    thead.appendChild(header);

    let th = document.createElement("th");
    th.setAttribute('scope', 'col');
    th.appendChild(document.createTextNode("#"));
    header.appendChild(th);

    for (let key of keys) {
        let th = document.createElement("th");
        th.setAttribute('scope', 'col');
        th.appendChild(document.createTextNode(key));
        header.appendChild(th);
    }
    return thead;
}

function initTBody(data) {
    let keys = Object.keys(data[0]);

    let tbody = document.createElement("tbody");
    for(let row of data) {
        let tr = document.createElement("tr");
        tbody.appendChild(tr);

        let th = document.createElement("th");
        tr.appendChild(th);
        th.appendChild(document.createTextNode(data.indexOf(row)));
        th.setAttribute('scope', 'row');
        
        for(let key of keys) {
            let td = document.createElement("td");
            let content = row[key];
            td.appendChild(document.createTextNode(content));
            tr.appendChild(td);
        }
    }
    return tbody;
}