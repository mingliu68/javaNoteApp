const el = id => {
    document.getElementById(id);
}

const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];


const submitForm = el("note-form")
const noteContainer = el("note-container")


let noteBody = el(`note-body`)
let updateNoteBtn = el('update-note-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/notes/"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        body: el("note-input").value
    }
    await addNote(bodyObj);
    document.el("note-input").value = ''
}

async function addNote(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getNotes(userId);
    }
}

async function getNotes(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createNoteCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(noteId){
    await fetch(`${baseUrl}${noteId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getNotes(userId);
}

async function getNoteById(noteId){
    await fetch(`${baseUrl}${noteId}`, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleNoteEdit(noteId){
    let bodyObj = {
        id: noteId,
        body: noteBody.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getNotes(userId);
}

const createNoteCards = (array) => {
    noteContainer.innerHTML = ''
    array.forEach(obj => {
        let noteCard = document.createElement("div")
        noteCard.classList.add("m-2")
        noteCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getNoteById(${obj.id})" type="button" class="btn btn-primary" 
                        data-bs-toggle="modal" data-bs-target="#note-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        noteContainer.append(noteCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{
    noteBody.innerText = ''
    noteBody.innerText = obj.body
    updateNoteBtn.setAttribute('data-note-id', obj.id)
}

getNotes(userId);

submitForm.addEventListener("submit", handleSubmit)

updateNoteBtn.addEventListener("click", (e)=>{
    let noteId = e.target.getAttribute('data-note-id')
    handleNoteEdit(noteId);
})

// function el(id) {
//     return document.getElementById(id);
// }
// function cdiv() {
//     document.createElement("div");
// }


// const cookieArr = document.cookie.split("=");
// console.log(document.cookie);
// const userId = cookieArr[1];

// console.log("userId: " + userId)
// const form = el("note-form");
// const noteContainer = el("note-container");
// const noteBody = el("note-body");
// const updateBtn = el("update-note-button");

// const headers = {
//     'Content-type':'application/json'
// }

// const baseUrl = 'http://localhost:8080/api/notes';


// const handlingLogout = () => {
//     const c = document.cookie.split(';');
//     for(let i in c) {
//         document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu,01 Jan 1970 00:00:00 GMT";
//     }
// }


// const  handlingSubmit = async (event) => {
//     event.preventDefault;

//     const bodyObj = {
//         body: el("note-input").value
//     }
//     await addNote(bodyObj);
//     el("note-input").value = "";
// }


// async function addNote (obj) {
//     const response = await fetch(`${baseUrl}/user/${userId}`, {
//         method: "POST",
//         body: JSON.stringify(obj),
//         headers: headers
//     }).catch(error => console.error(error.message));

//     if(response.status == 200) {
//         return getNotes(userId);
//     }
// }

// async function getNotes (userId)  {
//     await fetch(`${baseUrl}/user/${userId}`, {
//         method:"GET",
//         headers: headers
//     })
//     .then(response => response.json())
//     .then(data => createNoteCards(data))
//     .catch(error => console.error(error.message));
// }

// const createNoteCards = (notes) => {
//     noteContainer.innerHTML = '';
//     notes.forEach(note => {
//         let noteCard = document.createElement("div");
//         noteCard.classList.add('m-2');
//         noteCard.innerHTML = `
//             <div class="card d-flex" style="width: 18rem;height; 18rem;">
//                 <div class="card-body d-flex flex-column justify-content-between" style="height: available">
//                     <p class="class-text">${note.body}</p>
//                     <div class="d-flex justify-content-between">
//                         <button class="btn btn-danger" onclick="handlingDelete(${note.id})">Delete</button>
//                         <button onclick="getNoteById(${note.id})" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#note-edit-modal">Edit</button>
//                     </div>
//                 </div>
//             </div>
//         `
//         noteContainer.append(noteCard);
//     })
// }

// const getNoteById = async(id) => {
//     await fetch(`${baseUrl}/{$id}`, {
//         method: "GET",
//         headers: headers
//     })
//     .then(res=>res.json())
//     .then(data=>populateModal(data))
//     .catch(error=>console.error(error.message));
// }

// const populateModal = (obj) => {

//     noteBody.innerText = obj.body;
//     updateBtn.setAttribute('data-note-id', obj.id);
// }

// const handlingEdit = async(noteId) => {
//     const bodyObj = {
//         id: noteId,
//         body: noteBody.value
//     }
//     await fetch(baseUrl, {
//         method:'PUT',
//         body:JSON.stringify(bodyObj),
//         headers:headers
//     }).catch(error => console.error(error.message));

//     return getNotes(userId);
// }

// const handlingDelete = async(noteId) => {
//     await fetch(`${baseUrl}/${noteId}`, {
//         method: "DELETE",
//         headers: headers
//     }).catch(error=> console.error(error.message));
//     return getNotes(userId);
// }

// getNotes(userId);

// form.addEventListener("submit", handlingSubmit);
// updateBtn.addEventListener("click",  event => {
//     const noteId = event.target.getAttribute('data-note-id');
//     handleNoteEdit(noteId);
// })