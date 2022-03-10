function showRecord(name1, colour1, price1) {
    let refRow = document.createElement("tr")
    let refTd1 = document.createElement("td")
    let refTd2 = document.createElement("td")
    let refTd3 = document.createElement("td")

    refTd1.innerHTML = name1
    refTd2.innerHTML = colour1
    refTd3.innerHTML = price1


    refRow.appendChild(refTd1);
    refRow.appendChild(refTd2);
    refRow.appendChild(refTd3);

    refTable.appendChild(refRow)
    document.body.appendChild(refTable)
}

function getAll() {
    fetch('http://localhost:8080/getAll')
        .then((response) => {
            if (response.status !== 200) {
                console.log(`getAll does not work.Status Code: ${response.status}`);
                return;
            }
            response.json()
                .then(res => {
                    console.log(res);

                    refTable = document.createElement("table");
                    refTable.classList.add("table", "table-primary");

                    for (let i = 0; i < res.length; i++) {
                        showRecord(res[i].name, res[i].colour, res[i].price)
                    }
                })
                .catch(err => console.error(`Fetch Error :-S ${err}`));
        }
        )
}

function get() {
    let gymid = document.getElementById("id").value
    fetch('http://localhost:8080/get/' +gymid)
        .then((response) => {
            if (response.status !== 200) {
                console.log(`get does not work.Status Code: ${response.status}`);
                return;
            }
            response.json()
                .then(data => {
                    console.log(data);

                    refTable = document.createElement("table");
                    refTable.border = 1
                    refTable.classList.add("table");
                    showRecord(data.name, data.colour, data.price)

                })
                .catch(err => console.error(`Fetch Error :-S ${err}`));

        })
}

document.getElementById("superform").addEventListener("submit",function(e) {
    e.preventDefault()
    fetch("http://localhost:8080/create", {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(
            {
                "name": this.gearname.value,
                "colour": this.colour.value,
                "price": this.price.value
            }
        )
        
    })
    .then(res => res.json())
    .then((data) => console.log(`Request successful with JSON response ${data}`))
    .catch((error) => console.log(`Request failed ${error}`))
});

document.getElementById("updatesuperform").addEventListener("submit",function(e) {
    e.preventDefault()
    let gymid = document.getElementById("id1").value
    fetch("http://localhost:8080/update/"+gymid, {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(
            {
                "name": this.gearname1.value,
                "colour": this.colour1.value,
                "price": this.price1.value
            }
        )
        
    })
    .then(res => res.json())
    .then((data) => console.log(`Request successful with JSON response ${data}`))
    .catch((error) => console.log(`Request failed ${error}`))
});

function remove() {
    let id = document.getElementById("id").value;
    fetch("http://localhost:8080/delete/" +id, {
        method: 'delete'

})
.then((data) => {
    console.log(`Requested succeeded with JSON respone ${data}`);

})
.catch((error) => console.log(`Request failed ${error}`));
}
