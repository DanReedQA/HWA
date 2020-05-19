
const updateCustomer = () => {

    let id = document.getElementById("yourId").value;
    let username = document.getElementById("newUname").value;

    axios({
        method: 'put',
        headers: {"Access-Control-Allow-Origin": "*"},
        headers: {'content-Type': 'application/json'},
        url: `http://localhost:8181/customer/updateCustomer/${id}`,
        data: {
            "username" : username
        },

    })
        .then(function (response) {
            console.log(response);
            modal.style.display = "none";
            window.alert("username updated");
        })
        .catch(function (response) {
            console.log(response);
        })
}

let buttUpdateUser = document.querySelector('#account');
buttUpdateUser.addEventListener('click', updateCustomer);