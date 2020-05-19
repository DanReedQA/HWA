
const createUser = () => {
    let username = document.getElementById("uname").value;

    axios({
        method: 'post',
        headers: {"Access-Control-Allow-Origin": "*"},
        headers: {'content-Type': 'application/json'},
        url: 'http://localhost:8181/customer/createCustomer',
        data: {
            "username" : username
        },
    })
        .then(function (response) {
            console.log(response);
            modal.style.display = "none";
            window.alert("logged in");
        })
        .catch(function (response) {
            console.log(response);
        })
}

let buttCreateUser = document.querySelector('#login');
buttCreateUser.addEventListener('click', createUser);
