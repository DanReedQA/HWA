
const customerToDelete = () => {

        let id = document.getElementById("customerId").value;

    axios ({
        method: 'delete',
        headers: {"Access-Control-Allow-Origin": "*"},
        headers: {'content-Type': 'application/json'},
        url: `http://localhost:8181/customer/deleteCustomer/${id}`,
        data: {},
    })
        .then(function (response) {
            console.log(response);
            modal.style.display = "none";
            window.alert("logged out");
        })
        .catch(function (response) {
            console.log(response);
            modal.style.display = "none";
        })
}

let deleteCustomer = document.querySelector("#id");
deleteCustomer.addEventListener('click', customerToDelete);