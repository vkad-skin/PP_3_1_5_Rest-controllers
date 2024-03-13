let formDelete = document.forms["formDelete"]
deleteUser();

async function deleteModal(id) {
    const modalDelete = new bootstrap.Modal(document.querySelector('#deleteModal'));
    await open_fill_modal(formDelete, modalDelete, id);
    loadRolesForDelete();
}

function deleteUser() {
    formDelete.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/admin/users/" + formDelete.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#deleteClose').click();
            getAllUsers();
        });
    });
}
function loadRolesForDelete() {
    let selectDelete = document.getElementById("delete-roles");
    selectDelete.innerHTML = "";

    fetch("http://localhost:8080/admin/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.name.toString();
                selectDelete.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesForDelete);