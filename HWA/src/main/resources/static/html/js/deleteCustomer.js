const REQ = new XMLHttpRequest();
let deleteCustomer = document.querySelector("#logout");

function userToDelete() {

    const idToDelete = document.getElementById("logout").value;

    axios({
        method: 'delete',
        headers: {"Access-Control-Allow-Origin": "*"},
        headers: {'content-Type': 'application/json'},
        url: 'http://localhost:8181/customer/deleteCustomer/${idToDelete}',
        data: {}
    })
        .then(function (response) {
            console.log(response);
            window.alert("logged out");
        })
        .catch(function (response) {
            console.log(response);
        })
}
let buttDeleteUser = document.querySelector('#logout');
buttDeleteUser.addEventListener('click', userToDelete);