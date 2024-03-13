let formNewUser = document.forms["formNewUser"];

createNewUser();

function createNewUser() {
    formNewUser.addEventListener("submit", ev => {
        ev.preventDefault();

        let rolesForNewUser = [];
        for (let i = 0; i < formNewUser.roles.options.length; i++) {
            if (formNewUser.roles.options[i].selected)
                rolesForNewUser.push({
                    id: formNewUser.roles.options[i].value,
                    role: "ROLE_" + formNewUser.roles.options[i].text
                });
        }

        fetch("http://localhost:8080/admin/users/", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: formNewUser.firstName.value,
                lastName: formNewUser.lastName.value,
                age: formNewUser.age.value,
                email: formNewUser.email.value,
                password: formNewUser.password.value,
                roles: rolesForNewUser
            })
        }).then(() => {
            formNewUser.reset();
            getAllUsers();
            $('#usersTable').click(); //клик по кнопке Users Table

        });
    });
}

function loadRolesForNewUser() {
    let selectAdd = document.getElementById("create-roles");

    selectAdd.innerHTML = "";

    fetch("http://localhost:8080/admin/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.name.toString();
                selectAdd.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesForNewUser);