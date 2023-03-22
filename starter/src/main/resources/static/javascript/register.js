function el(id) {
    return document.getElementById(id);
}

const form = el("register-form");
const username = el("username");
const password = el("password");

const headers = {
    'Content-type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/users';

const handlingSubmit = async (event) => {
    event.preventDefault();

    const bodyObj = {
        username : username.value,
        password : password.value
    }

    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
        }).catch(error => console.error(error.message));
        
    const responseArr = await response.json();

    if(response.status === 200) {
        window.location.replace("http://localhost:8080/login.html");
    }
}

form.addEventListener("submit", handlingSubmit);