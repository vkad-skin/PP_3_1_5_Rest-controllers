const URLAuthAdmin = 'http://localhost:8080/admin/showAuthAdmin/';
const navbarAdmin = document.getElementById('navbarAdmin');
const tableUserAdmin = document.getElementById('tableAdmin');

function getCurrentAdmin() {
    fetch(URLAuthAdmin)
        .then((res) => res.json())
        .then((userAdmin) => {

            let rolesStringAdmin = rolesToStringForAdmin(userAdmin.roles);
            let dataOfAdmin = '';

            dataOfAdmin += `<tr class="border-top bg-light table-light">
            <td>${userAdmin.id}</td>
            <td>${userAdmin.firstName}</td>
            <td>${userAdmin.lastName}</td>
            <td>${userAdmin.age}</td>
            <td>${userAdmin.email}</td>
            <td>${rolesStringAdmin}</td>
            </tr>`;
            tableUserAdmin.innerHTML = dataOfAdmin;
            navbarAdmin.innerHTML = `<b><span>${userAdmin.email}</span></b>
                             <span>with roles:</span>
                             <span>${rolesStringAdmin}</span>`;
        });
}

getCurrentAdmin()

function rolesToStringForAdmin(roles) {
    let rolesString = '';

    for (const element of roles) {
        rolesString += (element.name.toString() + ', ');
    }
    rolesString = rolesString.substring(0, rolesString.length - 2);
    return rolesString;
}

async function getUserById(id) {
    let response = await fetch("http://localhost:8080/admin/users/" + id);
    return await response.json();
}

async function open_fill_modal(form, modal, id) {
    modal.show();
    let user = await getUserById(id);
    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.email;
    form.password.value = user.password;
    form.roles.value = user.roles;
}