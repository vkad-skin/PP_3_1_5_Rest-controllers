const URLAuthUser = 'http://localhost:8080/user/showAuthUser/';
const navbarUser = document.getElementById('navbarUser');
const tableUser = document.getElementById('tableUser');

function getCurrentUser() {
    fetch(URLAuthUser)
        .then((res) => res.json())
        .then((user) => {

            let rolesStringUser = rolesToStringForUser(user.roles);
            let dataOfUser = '';

            dataOfUser += `<tr class="border-top bg-light">
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${rolesStringUser}</td>
            </tr>`;
            tableUser.innerHTML = dataOfUser;
            navbarUser.innerHTML = `<b><span>${user.email}</span></b>
                             <span>with roles:</span>
                             <span>${rolesStringUser}</span>`;
        });
}

getCurrentUser()

function rolesToStringForUser(roles) {
    let rolesString = '';
    for (let element of roles) {
        rolesString += (element.name.toString() + ', ');
    }
    rolesString = rolesString.substring(0, rolesString.length - 2);
    return rolesString;
}