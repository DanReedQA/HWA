
const REQ = new XMLHttpRequest();

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
            console.dir(REQ);
            let responseObject = REQ.response;
            sessionStorage.setItem('currentUser', JSON.stringify(responseObject));
            let currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
            sessionStorage.setItem('currentUserId', currentUser.id);
            let currentUserId = sessionStorage.getItem('currentUserId');
            console.log(currentUser);
            console.log(currentUserId)
            modal.style.display = "none";
            window.alert("logged in");
        })
        .catch(function (response) {
            console.log(response);
        })
}

let buttCreateUser = document.querySelector('#login');
buttCreateUser.addEventListener('click', createUser);
