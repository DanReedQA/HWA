const REQ = new XMLHttpRequest();

const idToDelete = document.getElementById("online").value;

function getCustomers() {
    REQ.onload = () => {
        if (REQ.status === 200) {
            console.dir(REQ);
            let responseObject = REQ.response;
            console.log(responseObject);
        } else {
            console.log(`something went wrong`);
            window.alert(`Oh no! Something went wrong :(`);
        }
    };

    let userOBJ = REQ.open("GET", `http://localhost:8181/getAllCustomers`);
    REQ.setRequestHeader("Content-type", "Application/json");
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.responseType = "json";
    console.log(userOBJ);
    REQ.send();
}

let showCustomersButt = document.querySelector('#online');
showCustomersButt.addEventListener("click", getCustomers);